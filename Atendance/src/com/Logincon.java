package com;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginDAO;
import VO.CompanyVO;
import VO.DepartmentVo;
import VO.EmployeeVo;
import VO.LoginVO;

/**
 * Servlet implementation class Logincon
 */
@WebServlet("/Logincon")
public class Logincon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Logincon() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if (flag.equals("verify")) {
			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String pass = request.getParameter("pwd");
			session.setAttribute("email", email);
			session.setAttribute("pass", pass);
			Verify(request, response);
		} else if (flag.equals("forgetpassword")) {
			forgotepassword(request, response);
		} else if (flag.equals("updatepassword")) {
			updatepassword(request, response);
		}
	}

	private void updatepassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			String userroll = (String) session.getAttribute("roll");
			String password = request.getParameter("password");
			String conformpassword = request.getParameter("cpassword");
			if (EmailValidation.isValid(email)) {
				if (password.equals(conformpassword)) {
					if (userroll.equals("Admin")) {
						CompanyVO compny = new CompanyVO();
						compny.setEmail(email);
						compny.setPassword(conformpassword);

						LoginVO login = new LoginVO();
						login.setEmail(email);
						login.setPassword(conformpassword);

						LoginDAO logindao = new LoginDAO();
						logindao.forgetpasswordcompany(compny, login);
					} else if (userroll.equals("Emp")) {
						EmployeeVo emp = new EmployeeVo();
						emp.setEmail(email);
						emp.setPassword(conformpassword);

						LoginVO login = new LoginVO();
						login.setEmail(email);
						login.setPassword(conformpassword);

						LoginDAO logindao = new LoginDAO();
						logindao.forgetpasswordemp(emp, login);
					}
					response.sendRedirect("Com_Login.jsp");
				} else {
					session.setAttribute("error", "true");
					response.sendRedirect("forgetPasswordupdate.jsp");
				}

			} else {
				session.setAttribute("wrong", "true");
				response.sendRedirect("forgetPasswordupdate.jsp");
			}
		} catch (Exception e) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "true");
			response.sendRedirect("forgetPasswordupdate.jsp");
		}

	}

	private void forgotepassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String email = request.getParameter("email");
			HttpSession session = request.getSession();
			if (EmailValidation.isValid(email)) {
				LoginVO login = new LoginVO();
				login.setEmail(email);

				LoginDAO logindao = new LoginDAO();
				ArrayList<LoginVO> forgetpass = logindao.emailverify(login);
				String roll = forgetpass.get(0).getRoll();
				String forgetemail = forgetpass.get(0).getEmail();

				session.setAttribute("email", forgetemail);
				session.setAttribute("roll", roll);
				response.sendRedirect("forgetPasswordupdate.jsp");

			} else {
				session.setAttribute("wrong", "true");
				response.sendRedirect("forgetPasswordupdate.jsp");
			}
		} catch (Exception e) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "true");
			response.sendRedirect("forgetpassword.jsp");
		}

	}

	private void Verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			String pass = (String) session.getAttribute("pass");
			if (EmailValidation.isValid(email)) {
				LoginVO v1 = new LoginVO();
				v1.setEmail(email);
				v1.setPassword(pass);

				LoginDAO d1 = new LoginDAO();
				ArrayList<LoginVO> l1 = d1.verify(v1);
				int logintime = l1.get(0).getId();
				String t11 = l1.get(0).getLogin();
				session.setAttribute("time", t11);

				System.out.println(l1.get(0).getRoll());
				String s1 = l1.get(0).getRoll();
				if (s1.equals("Admin")) {
					CompanyVO v2 = new CompanyVO();
					v2.setEmail(email);
					v2.setPassword(pass);
					LoginDAO dsearch = new LoginDAO();
					ArrayList<CompanyVO> admin = dsearch.cverify(v2);
					HttpSession h1 = request.getSession();
					h1.setAttribute("data", admin);

					Timestamp t1 = new Timestamp(System.currentTimeMillis());
					String s111 = t1.toString();
					LoginVO login = new LoginVO();
					login.setLogin(s111);
					login.setId(logintime);

					LoginDAO logindao = new LoginDAO();
					logindao.logintime(login);

					response.sendRedirect("Company_Login.jsp");

				} else if (s1.equals("Emp")) {
					System.out.println("");
					EmployeeVo v3 = new EmployeeVo();
					v3.setEmail(email);
					v3.setPassword(pass);

					LoginDAO dsearch = new LoginDAO();
					ArrayList<EmployeeVo> devloper = dsearch.everify(v3);
					String roll = devloper.get(0).getRoll();
					String department = devloper.get(0).getDep_id().getDepartment();
					if (roll.equals("Emp")) {
						Timestamp t1 = new Timestamp(System.currentTimeMillis());
						String s111 = t1.toString();
						LoginVO login = new LoginVO();
						login.setLogin(s111);
						login.setId(logintime);

						LoginDAO logindao = new LoginDAO();
						logindao.logintime(login);
						HttpSession h1 = request.getSession();
						h1.setAttribute("data", devloper);
						response.sendRedirect("Emp_Login.jsp");
					} else if (roll.equals("Head") && department.equals("HR") == false) {

						Timestamp t1 = new Timestamp(System.currentTimeMillis());
						String s111 = t1.toString();
						LoginVO login = new LoginVO();
						login.setLogin(s111);
						login.setId(logintime);

						LoginDAO logindao = new LoginDAO();
						logindao.logintime(login);
						HttpSession h1 = request.getSession();
						h1.setAttribute("data", devloper);
						response.sendRedirect("Emp_Admin_Login.jsp");
					} else if (roll.equals("Head") && department.equals("HR")) {

						Timestamp t1 = new Timestamp(System.currentTimeMillis());
						String s111 = t1.toString();
						LoginVO login = new LoginVO();
						login.setLogin(s111);
						login.setId(logintime);

						LoginDAO logindao = new LoginDAO();
						logindao.logintime(login);
						HttpSession h1 = request.getSession();
						h1.setAttribute("data", devloper);
						response.sendRedirect("Emp_HR_Login.jsp");
					}
				}
			} else {
				session.setAttribute("wrong", "true");
				response.sendRedirect("Com_Login.jsp");
			}

		} catch (Exception e) {
			HttpSession session = request.getSession();
			session.setAttribute("loginResult", "true");
			response.sendRedirect("Com_Login.jsp");
		}

	}
}
