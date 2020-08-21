package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DepartmentDAO;
import DAO.MessageDAO;
import VO.CompanyVO;
import VO.DepartmentVo;
import VO.EmployeeVo;
import VO.MessageVo;

/**
 * Servlet implementation class Department
 */
@WebServlet("/Department")
public class Department extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Department() {
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
		HttpSession session = request.getSession();
		if (s1.equals("insert")) {
			int id2 = (Integer.parseInt(request.getParameter("id")));
			session.setAttribute("cid", id2);
			response.sendRedirect("Department_Reg.jsp");
		}
		if (s1.equals("departmentsearch")) {
			int id = (Integer.parseInt(request.getParameter("id")));
			session.setAttribute("cid", id);
			Employee emp = new Employee();
			emp.departmentSearch(request, response);
			session.setAttribute("departmentsearch", "company");
			response.sendRedirect("Dep_search.jsp");
		}
		if (s1.equals("hrdepartmentsearch")) {
			int id = (Integer.parseInt(request.getParameter("id")));
			session.setAttribute("cid", id);
			session.setAttribute("departmentsearch", "hr");
			Employee emp = new Employee();
			emp.departmentSearch(request, response);
			response.sendRedirect("Dep_search.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s1 = request.getParameter("flag");
		if (s1.equals("insert")) {
			insert(request, response);
			response.sendRedirect("Department_Reg.jsp");

		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession(false);
			int id = (Integer) session.getAttribute("cid");
			String department = request.getParameter("department");

			CompanyVO com = new CompanyVO();
			com.setId(id);

			DepartmentVo dep = new DepartmentVo();
			dep.setCom_id(com);
			dep.setDepartment(department);

			DepartmentDAO depdao = new DepartmentDAO();
			String s = depdao.insert(dep);
			if (s.equals("erorr")) {
				session.setAttribute("erorr", "true");
			} else if (s.equals("add")) {
				session.setAttribute("loginResult", "true");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
