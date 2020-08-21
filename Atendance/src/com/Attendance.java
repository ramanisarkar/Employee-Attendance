package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.SendFailedException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.cfg.ExtendedMappings;

import DAO.AttDAO;
import DAO.Att_Out_DAO;
import DAO.Employee_DAO;
import DAO.MessageDAO;
import VO.CompanyVO;
import VO.DepartmentVo;
import VO.EmpAttendanceVo;
import VO.EmpAttendance_outVo;
import VO.EmployeeVo;
import VO.MessageVo;

/**
 * Servlet implementation class Attendance
 */
@WebServlet("/Attendance")
public class Attendance extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Attendance() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String s1 = request.getParameter("flag");
		HttpSession h1 = request.getSession();
		if (s1.equals("empatt")) {
			String flag2 = request.getParameter("flag2");
			int id = Integer.parseInt(request.getParameter("id"));
			h1.setAttribute("id", id);
			h1.setAttribute("attendance", flag2);
			empAtted(request, response);

			response.sendRedirect("Empattendance_Lists.jsp");
		}
		if (s1.equals("attendanceEdit")) {
			attendanceEdit(request, response);
			response.sendRedirect("attendanceUpdate.jsp");
		}
		if (s1.equals("companyemployeepresent")) {
			presentEmployee(request, response);
			response.sendRedirect("Present_Attendance_Lists.jsp");
		}
		if (s1.equals("attendancerecord")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String flag2 = request.getParameter("flag2");
			h1.setAttribute("id", id);
			h1.setAttribute("attendancesearch", flag2);
			empAtted(request, response);
			response.sendRedirect("attendance_Lists.jsp");
		}
	}

	private void presentEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String flag3 = request.getParameter("flag3");
		String flag2 = request.getParameter("flag2");
		System.out.println(flag2+"0................000.0.0..00."+flag3);
		session.setAttribute("search", flag2);
		java.sql.Date date = null;
		if (flag3 != null) {
			String from = request.getParameter("search");
			date = java.sql.Date.valueOf(from);
			session.setAttribute("dateprint", date);

		}else{
			
			LocalDateTime localDateTime = LocalDateTime.now();
			date = java.sql.Date.valueOf(localDateTime.toLocalDate());
			session.setAttribute("dateprint", date);
		}

		ArrayList<EmpAttendanceVo> listisempty = null;
		ArrayList<EmpAttendanceVo> list = new ArrayList<>();

		
		if (flag2.equals("company") || flag2.equals("hr")) {
			int id = Integer.parseInt(request.getParameter("id"));

			CompanyVO comvo = new CompanyVO();
			comvo.setId(id);
			EmployeeVo empvo = new EmployeeVo();
			empvo.setCompnayid(comvo);

			Employee_DAO empdao = new Employee_DAO();
			ArrayList<EmployeeVo> emplist = empdao.compnaySearch(empvo);
			for (EmployeeVo employeeVo : emplist) {
				int empid = employeeVo.getId();
				empvo.setId(empid);

				EmpAttendanceVo empattendance = new EmpAttendanceVo();
				empattendance.setDate(date);
				empattendance.setEmpattendance(empvo);

				AttDAO attdao = new AttDAO();
				listisempty = attdao.chakeEntery(empattendance);

				list.addAll(listisempty);
			}
			System.out.println(list);
			System.out.println(list.size());
			session.setAttribute("presentattendance", list);
		} else if (flag2.equals("head")) {
			int id = Integer.parseInt(request.getParameter("id"));
			DepartmentVo depvo = new DepartmentVo();
			depvo.setId(id);
			EmployeeVo empvo = new EmployeeVo();
			empvo.setDep_id(depvo);

			Employee_DAO empdao = new Employee_DAO();
			ArrayList<EmployeeVo> emplist = empdao.departmentSearchEmployee(empvo);
			for (EmployeeVo employeeVo : emplist) {
				int empid = employeeVo.getId();
				empvo.setId(empid);

				EmpAttendanceVo empattendance = new EmpAttendanceVo();
				empattendance.setDate(date);
				empattendance.setEmpattendance(empvo);

				AttDAO attdao = new AttDAO();
				listisempty = attdao.chakeEntery(empattendance);

				list.addAll(listisempty);
			}
			System.out.println(list);
			System.out.println(list.size());
			session.setAttribute("presentattendance", list);
		}

	}

	private void attendanceEdit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		System.out.println(name);
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		String lastedit = t1.toString();

		EmpAttendance_outVo attout = new EmpAttendance_outVo();
		attout.setId(id);

		Att_Out_DAO attdao = new Att_Out_DAO();
		ArrayList<EmpAttendance_outVo> attendanceoutlist = attdao.searchAttendance(attout);

		String status = "green";
		MessageVo messagevo = new MessageVo();
		messagevo.setStatus(status);
		messagevo.setAttendanceid(attout);
		messagevo.setLastedited(lastedit);
		messagevo.setLasteditedby(name);

		MessageDAO messdao = new MessageDAO();
		messdao.statusmodify(messagevo);

		session.setAttribute("Attendance", attendanceoutlist);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s1 = request.getParameter("flag");
		if (s1.equals("attendaceupdate")) {
			employeeAttendanceUpdate(request, response);
			response.sendRedirect("attendanceUpdate.jsp");

		}
		if (s1.equals("searchattendance")) {
			searchAttendance(request, response);
			response.sendRedirect("attendance_Lists.jsp");
		}
		if (s1.equals("searchempaloyee")) {
			presentEmployee(request, response);
			response.sendRedirect("Present_Attendance_Lists.jsp");
		}
	}

	private void searchAttendance(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		int empid = (Integer.parseInt(request.getParameter("empid")));
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		java.sql.Date start = java.sql.Date.valueOf(from);
		java.sql.Date end = java.sql.Date.valueOf(to);
		System.out.println(start + "" + end);

		EmployeeVo empvo = new EmployeeVo();
		empvo.setId(empid);

		EmpAttendance_outVo empattoutvo = new EmpAttendance_outVo();
		empattoutvo.setEmpattout_id(empvo);

		Att_Out_DAO attoutdao = new Att_Out_DAO();
		ArrayList<EmpAttendance_outVo> attlist = attoutdao.searchAttendance(empattoutvo, start, end);
		System.out.println(attlist.size());
		session.setAttribute("empAttendanceout", attlist);
	}

	private void empAtted(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			int id = (Integer) session.getAttribute("id");

			EmployeeVo emp = new EmployeeVo();
			emp.setId(id);

			EmpAttendance_outVo attout = new EmpAttendance_outVo();
			attout.setEmpattout_id(emp);

			Att_Out_DAO attdao = new Att_Out_DAO();
			ArrayList<EmpAttendance_outVo> attendanceoutlist = attdao.empEdit(attout);

			HttpSession h1 = request.getSession();
			h1.setAttribute("empAttendanceout", attendanceoutlist);

		} catch (Exception e) {
			PrintWriter out = new PrintWriter(System.out);
			out.print("Attendance List Is Emty");
		}

	}

	private void employeeAttendanceUpdate(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		int attid = Integer.parseInt(request.getParameter("attid"));
		int attoutid = Integer.parseInt(request.getParameter("attoutid"));

		DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime starttimeformet = LocalTime.parse(start, timeformatter);
		LocalTime endtimeformet = LocalTime.parse(end, timeformatter);

		java.sql.Time starttime = java.sql.Time.valueOf(starttimeformet);
		java.sql.Time endtime = java.sql.Time.valueOf(endtimeformet);

		EmpAttendanceVo attendance = new EmpAttendanceVo();
		attendance.setId(attid);
		attendance.setStart(starttime);

		String status = null;
		LocalTime t1 = starttime.toLocalTime();
		LocalTime t2 = endtime.toLocalTime();

		long Duration = java.time.Duration.between(t1, t2).toMinutes();

		if (Duration <= 90) {
			status = "Leave";
		} else if (90 < Duration && Duration <= 540) {
			status = "Half Leave";
		} else if (540 < Duration) {
			status = "Present";
		}
		EmpAttendance_outVo attendance_out = new EmpAttendance_outVo();
		attendance_out.setId(attoutid);
		attendance_out.setEnd(endtime);
		attendance_out.setStatus(status);

		AttDAO attdao = new AttDAO();
		attdao.employeeAttendanceUpdate(attendance);

		Att_Out_DAO attoutdao = new Att_Out_DAO();
		attoutdao.employeeAttendanceOutUpdate(attendance_out);
		ArrayList<EmpAttendance_outVo> empoutlist = attoutdao.searchAttendance(attendance_out);
		session.setAttribute("Attendance", empoutlist);
		session.setAttribute("attendanceupdate", "true");
	}
}
