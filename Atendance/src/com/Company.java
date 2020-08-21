package com;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CompanyDAO;
import DAO.LoginDAO;
import VO.CompanyVO;
import VO.LoginVO;

/**
 * Servlet implementation class Company
 */
@WebServlet("/Company")
public class Company extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("Year");
		System.out.println(".....................................");
		System.out.println(userName);
		System.out.println(".....................................");
		String s1 = request.getParameter("flag");
		if (s1.equals("search")) {
			response.sendRedirect("Company_search.jsp");
		}
		if (s1.equals("edit")) {
			Edit(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(".....................................");
		String userName = request.getParameter("Year");
		System.out.println(".....................................");
		System.out.println(userName);
		System.out.println(".....................................");
		String s1 = request.getParameter("flag");
		if (s1.equals("insert")) {
			insert(request, response);
		}
		if (s1.equals("Update")) {
			Update(request, response);
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession h1 = request.getSession();
		String com_name = request.getParameter("companyname");
		String com_add = request.getParameter("companyaddress");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		if (EmailValidation.isValid(email)) {
			System.out.print("Yes");

			CompanyVO lo = new CompanyVO();
			lo.setCompanyname(com_name);
			lo.setAddress(com_add);
			lo.setEmail(email);
			lo.setPassword(pass);

			Timestamp t1 = new Timestamp(System.currentTimeMillis());
			String s111 = t1.toString();

			LoginVO login = new LoginVO();
			login.setEmail(email);
			login.setPassword(pass);
			login.setRoll("Admin");
			login.setLogin(s111);
			login.setCompnayid(lo);

			CompanyDAO d1 = new CompanyDAO();
			d1.insert(lo, login);
		} else {
			h1.setAttribute("wrong", "true");
			response.sendRedirect("Com_Login.jsp");
		}
		System.out.println("...................");
		response.sendRedirect("Com_Login.jsp");

	}

	private void Edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int s1 = (Integer.parseInt(request.getParameter("id")));

		CompanyVO v1 = new CompanyVO();
		v1.setId(s1);
		CompanyDAO d1 = new CompanyDAO();
		List editli = d1.Edit(v1);

		System.out.println(editli.size());

		HttpSession h1 = request.getSession();
		h1.setAttribute("edit", editli);
		response.sendRedirect("Company_Edit.jsp");
	}

	private void Update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String com_name = request.getParameter("companyname");
		String com_add = request.getParameter("companyaddress");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int id1 = (Integer.parseInt(request.getParameter("id")));
		if (EmailValidation.isValid(email)) {
			System.out.print("Yes");
			CompanyVO v1 = new CompanyVO();
			v1.setCompanyname(com_name);
			v1.setAddress(com_add);
			v1.setEmail(email);
			v1.setPassword(password);
			v1.setId(id1);

			LoginVO loginvo = new LoginVO();
			loginvo.setCompnayid(v1);
			loginvo.setEmail(email);
			loginvo.setPassword(password);

			CompanyDAO d1 = new CompanyDAO();
			LoginDAO logdao = new LoginDAO();
			ArrayList<LoginVO> ch = logdao.emailverify(loginvo);
			if (ch.isEmpty() == false) {
				logdao.loginupdate(loginvo);
				d1.update(v1);
				List editli = d1.Edit(v1);
				session.setAttribute("loginResult", "true");
				session.setAttribute("data", editli);
				response.sendRedirect("Company_Edit.jsp");
			} else if (ch.isEmpty() == true) {
				String chack = logdao.loginupdate1(loginvo);
				if (chack.equals("add")) {
					d1.update(v1);
					List editli = d1.Edit(v1);
					session.setAttribute("loginResult", "true");
					session.setAttribute("data", editli);
					response.sendRedirect("Company_Edit.jsp");
				} else {
					List editli = d1.Edit(v1);
					session.setAttribute("data", editli);
					session.setAttribute("not", "true");
					response.sendRedirect("Company_Edit.jsp");
				}
			}
		} else {
			session.setAttribute("wrong", "true");
			response.sendRedirect("Company_Edit.jsp");
		}
	}

}
