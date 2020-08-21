package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Employee_DAO;
import DAO.MessageDAO;
import VO.CompanyVO;
import VO.DepartmentVo;
import VO.EmpAttendance_outVo;
import VO.EmployeeVo;
import VO.MessageVo;

/**
 * Servlet implementation class Notification
 */
@WebServlet("/Notification")
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s1 = request.getParameter("flag");
		HttpSession session = request.getSession(false);
		System.out.println(s1);
		if (s1.equals("messsage")) {
			int id = (Integer.parseInt(request.getParameter("id")));
			String flag2 = request.getParameter("flag2");
			String flag3 = request.getParameter("flag3");
			session.setAttribute("id", id);
			session.setAttribute("flag2", flag2);
			session.setAttribute("search", flag3);
			notificationMessage(request, response);
			response.sendRedirect("NotificationMessage.jsp");
		}
		if (s1.equals("attendancedelete")) {
			notificationDelete(request, response);
			notificationMessage(request, response);
			response.sendRedirect("NotificationMessage.jsp");
		}
		if (s1.equals("sendmesssage")) {
			String flag = request.getParameter("flag2");
			int empid = (Integer.parseInt(request.getParameter("empid")));
			int attoutid = (Integer.parseInt(request.getParameter("attid")));
			session.setAttribute("sendmessage", flag);
			session.setAttribute("empid", empid);
			session.setAttribute("attoutid", attoutid);
			response.sendRedirect("message.jsp");

		}
	}

	private void notificationDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		int id = (Integer.parseInt(request.getParameter("id")));

		MessageVo messvo = new MessageVo();
		messvo.setId(id);

		MessageDAO messdao = new MessageDAO();
		messdao.notificationdelete(messvo);
		session.setAttribute("notificationdelete", "true");
	}

	private void notificationMessage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("id");
		String s1 = (String) session.getAttribute("flag2");

		MessageVo message = new MessageVo();
		MessageDAO depdao = new MessageDAO();
		List<MessageVo> admin;
		if (s1.equals("hr")) {
			CompanyVO comvo = new CompanyVO();
			comvo.setId(id);
			message.setCompanyid(comvo);
			admin = depdao.companySearch(message);
		} else {
			DepartmentVo depvo = new DepartmentVo();
			depvo.setId(id);
			message.setDep_id(depvo);
			admin = depdao.departmentSearch(message);
		}
		System.out.println(admin.size());
		session.setAttribute("message", admin);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s1 = request.getParameter("flag");
		if (s1.equals("empmessage")) {
			employeeMessage(request, response);
			response.sendRedirect("message.jsp");
		}
	}

	private void employeeMessage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		try {
			String empmessage = request.getParameter("message");
			int empid = (int) session.getAttribute("empid");
			int attoutid = (int) session.getAttribute("attoutid");

			EmployeeVo empvo = new EmployeeVo();
			empvo.setId(empid);

			Employee_DAO d1 = new Employee_DAO();
			List<EmployeeVo> editli = d1.employeeSearch(empvo);

			int department = editli.get(0).getDep_id().getId();
			String emproll = editli.get(0).getRoll();
			String departmentroll = editli.get(0).getDep_id().getDepartment();
			int companyid = editli.get(0).getCompnayid().getId();

			CompanyVO comvo = new CompanyVO();
			comvo.setId(companyid);

			DepartmentVo depvo = new DepartmentVo();
			depvo.setId(department);

			EmpAttendance_outVo empout = new EmpAttendance_outVo();
			empout.setId(attoutid);

			MessageVo message = new MessageVo();
			message.setDep_id(depvo);
			message.setEmployeeid(empvo);
			message.setAttendanceid(empout);
			message.setMessage(empmessage);
			message.setCompanyid(comvo);
			message.setStatus("entry");

			MessageDAO medao = new MessageDAO();
			String s = medao.inaert(message);
			if (s.equals("erorr")) {
				session.setAttribute("erorr", "true");
			} else if (s.equals("add")) {
				session.setAttribute("empmessage", "true");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
