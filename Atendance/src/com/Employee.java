package com;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AttDAO;
import DAO.Att_Out_DAO;
import DAO.DeleteDAO;
import DAO.DepartmentDAO;
import DAO.Employee_DAO;
import DAO.LoginDAO;
import VO.CompanyVO;
import VO.DepartmentVo;
import VO.EmpAttendanceVo;
import VO.EmpAttendance_outVo;
import VO.EmployeeVo;
import VO.LoginVO;
import VO.MessageVo;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/Employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Employee() {
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
			Scheduled s = new Scheduled();
			s.sche();
			int id2 = (Integer.parseInt(request.getParameter("id")));
			String flag = request.getParameter("flag2");
			session.setAttribute("autoattendance", id2);
			session.setAttribute("cid", id2);
			session.setAttribute("flag", flag);
			departmentSearch(request, response);
			response.sendRedirect("Emp_Reg.jsp");
		}
		if (s1.equals("companysearch")) {
			String search = request.getParameter("flag2");
			System.out.println("flaghr=" + search);
			session.setAttribute("search", search);
			Compnaysearch(request, response);
			response.sendRedirect("Emp_Search.jsp");
		}
		if (s1.equals("delete")) {
			deleteEmployee(request, response);
			response.sendRedirect("Emp_Search.jsp");
		}
		if (s1.equals("companyEdit")) {
			int comid = Integer.parseInt(request.getParameter("comid"));
			String flag2 = request.getParameter("flag2");
			session.setAttribute("search", flag2);
			session.setAttribute("cid", comid);
			Edit(request, response);
			departmentSearch(request, response);
			response.sendRedirect("CompanyEmp_Edit.jsp");
		}
		if (s1.equals("EmployeeEdit")) {
			Edit(request, response);
			String flag2 = request.getParameter("flag2");
			session.setAttribute("edit", flag2);
			response.sendRedirect("Emp_Edit.jsp");
		}
		if (s1.equals("departmentsearchemployee")) {
			String search = request.getParameter("flag2");
			session.setAttribute("search", search);
			String viewbutton = request.getParameter("flag3");
			session.setAttribute("viewbutton", viewbutton);
			departmentSearchEmployee(request, response);
			response.sendRedirect("Emp_Search.jsp");
		}
		if (s1.equals("Entery")) {
			String flag2 = request.getParameter("flag2");
			Entery(request, response);
			if (flag2.equals("hr")) {
				response.sendRedirect("Emp_HR_Login.jsp");
			} else if (flag2.equals("head")) {
				response.sendRedirect("Emp_Admin_Login.jsp");
			} else if (flag2.equals("emp")) {
				response.sendRedirect("Emp_Login.jsp");
			}
		}
		if (s1.equals("Exite")) {
			String flag2 = request.getParameter("flag2");
			Exit(request, response);
			if (flag2.equals("hr")) {
				response.sendRedirect("Emp_HR_Login.jsp");
			} else if (flag2.equals("head")) {
				response.sendRedirect("Emp_Admin_Login.jsp");
			} else if (flag2.equals("emp")) {
				response.sendRedirect("Emp_Login.jsp");
			}
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		int id = (Integer.parseInt(request.getParameter("id")));

		EmployeeVo empvo = new EmployeeVo();
		empvo.setId(id);

		EmpAttendance_outVo empoutvo = new EmpAttendance_outVo();
		empoutvo.setEmpattout_id(empvo);

		EmpAttendanceVo empattvo = new EmpAttendanceVo();
		empattvo.setEmpattendance(empvo);

		MessageVo messagevo = new MessageVo();
		messagevo.setEmployeeid(empvo);

		DeleteDAO delete = new DeleteDAO();
		delete.deleteEmployee(empvo, empoutvo, empattvo, messagevo);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s1 = request.getParameter("flag");
		if (s1.equals("insert")) {
			insert(request, response);
			response.sendRedirect("Emp_Reg.jsp");
		}
		if (s1.equals("Update")) {
			Update(request, response);
			Edit(request, response);
			response.sendRedirect("CompanyEmp_Edit.jsp");
		}
		if (s1.equals("employeeUpdate")) {
			Update(request, response);
			Edit(request, response);
			response.sendRedirect("Emp_Edit.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	private void insert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);

		try {

			int id = (Integer) session.getAttribute("cid");
			String firstname = request.getParameter("firstName");
			String lastname = request.getParameter("lastName");
			String con_no = request.getParameter("Con_no");
			String designation = request.getParameter("designation");
			String Address = request.getParameter("address");
			String gender = request.getParameter("gender");
			String salary = request.getParameter("salary");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String roll = request.getParameter("roll");
			if (EmailValidation.isValid(email)) {
				LoginVO login = new LoginVO();
				login.setEmail(email);

				LoginDAO logdao = new LoginDAO();
				ArrayList<LoginVO> chackemail = logdao.emailverify(login);
				if (chackemail.isEmpty() == true) {
					if (roll.equals("Select")) {
						session.setAttribute("select", "true");
					} else {
						int depid = (Integer.parseInt(request.getParameter("department")));
						if (roll.equals("Select") == false) {
							Timestamp t1 = new Timestamp(System.currentTimeMillis());
							String time = t1.toString();

							CompanyVO com = new CompanyVO();
							com.setId(id);

							DepartmentVo dep = new DepartmentVo();
							dep.setId(depid);

							EmployeeVo emp = new EmployeeVo();
							emp.setFirstName(firstname);
							emp.setLastName(lastname);
							emp.setCon_no(con_no);
							emp.setCompnayid(com);
							emp.setDesignation(designation);
							emp.setDep_id(dep);
							emp.setAddress(Address);
							emp.setGender(gender);
							emp.setSalary(salary);
							emp.setEmail(email);
							emp.setPassword(pass);
							emp.setRoll(roll);
							emp.setJoiningdate(time);

							login.setEmail(email);
							login.setPassword(pass);
							login.setEmployeeid(emp);
							login.setRoll("Emp");
							login.setLogin(time);

							Employee_DAO empdao = new Employee_DAO();
							if (roll.equals("Head")) {
								ArrayList<EmployeeVo> search = empdao.employeeAdminSearch(emp);
								if (search.isEmpty() == true) {
									String employee = empdao.insert(emp);
									if (employee.equals("erorr")) {
										session.setAttribute("erorr", "true");
									} else if (employee.equals("add")) {
										empdao.insert(login);
										session.setAttribute("loginResult", "true");
									}
								} else {
									session.setAttribute("existdepartment", "true");
								}
							} else if (roll.equals("Emp")) {
								String employee = empdao.insert(emp);
								if (employee.equals("erorr")) {
									session.setAttribute("erorr", "true");
								} else if (employee.equals("add")) {
									empdao.insert(login);
									session.setAttribute("loginResult", "true");
								}
							}
						}
					}
				} else {
					session.setAttribute("erorr", "true");
				}

			} else {
				session.setAttribute("wrong", "true");

			}
		} catch (Exception e) {
			session.setAttribute("department", "true");
		}
	}

	private void Compnaysearch(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		int id = (Integer.parseInt(request.getParameter("id")));

		CompanyVO com = new CompanyVO();
		com.setId(id);

		EmployeeVo emp = new EmployeeVo();
		emp.setCompnayid(com);

		Employee_DAO empdao = new Employee_DAO();
		ArrayList<EmployeeVo> emplist = empdao.compnaySearch(emp);
		System.out.println(emplist.size());
		session.setAttribute("emplist", emplist);

	}

	public void departmentSearch(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		int id = (Integer) session.getAttribute("cid");

		CompanyVO com = new CompanyVO();
		com.setId(id);

		DepartmentVo dep = new DepartmentVo();
		dep.setCom_id(com);

		DepartmentDAO depdao = new DepartmentDAO();
		ArrayList<DepartmentVo> list = depdao.search(dep);
		System.out.println(list.size());
		session.setAttribute("listdepartment", list);
	}

	private void departmentSearchEmployee(HttpServletRequest request, HttpServletResponse response) {
		int id = (Integer.parseInt(request.getParameter("id")));

		DepartmentVo dep = new DepartmentVo();
		dep.setId(id);

		EmployeeVo emp = new EmployeeVo();
		emp.setDep_id(dep);

		Employee_DAO empdao = new Employee_DAO();
		ArrayList<EmployeeVo> emplist = empdao.departmentSearchEmployee(emp);
		HttpSession session = request.getSession(false);
		session.setAttribute("emplist", emplist);
	}

	private void Edit(HttpServletRequest request, HttpServletResponse response) {
		int s1 = Integer.parseInt(request.getParameter("id"));

		EmployeeVo v1 = new EmployeeVo();
		v1.setId(s1);
		Employee_DAO d1 = new Employee_DAO();
		List<EmployeeVo> editli = d1.employeeSearch(v1);

		System.out.println(editli.size());

		HttpSession h1 = request.getSession();
		h1.setAttribute("emplist", editli);
	}

	private void Update(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");

		String con_no = request.getParameter("Con_no");
		String designation = request.getParameter("designation");
		String Address = request.getParameter("address");
		String salary = request.getParameter("salary");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String gender = request.getParameter("gender");
		String roll = request.getParameter("roll");
		int id2 = (Integer.parseInt(request.getParameter("dep_id")));
		int id1 = (Integer.parseInt(request.getParameter("id")));
		int companiid = (Integer.parseInt(request.getParameter("comid")));
		if (EmailValidation.isValid(email)) {
			DepartmentVo d1 = new DepartmentVo();
			d1.setId(id2);
			CompanyVO com = new CompanyVO();
			com.setId(companiid);

			EmployeeVo ev = new EmployeeVo();
			ev.setFirstName(firstname);
			ev.setLastName(lastname);
			ev.setCon_no(con_no);
			ev.setDesignation(designation);
			ev.setAddress(Address);
			ev.setSalary(salary);
			ev.setEmail(email);
			ev.setPassword(pass);
			ev.setGender(gender);
			ev.setCompnayid(com);
			ev.setRoll(roll);
			ev.setId(id1);
			ev.setDep_id(d1);

			LoginVO loginvo = new LoginVO();
			loginvo.setEmployeeid(ev);
			loginvo.setEmail(email);
			loginvo.setPassword(pass);

			LoginDAO logdao = new LoginDAO();
			ArrayList<LoginVO> ch = logdao.emailverify(loginvo);
			if (ch.isEmpty() == true) {
				String chack = logdao.loginupdate2(loginvo);
				if (chack.equals("add")) {
					Employee_DAO empdao = new Employee_DAO();
					if (roll.equals("Head")) {
						ArrayList<EmployeeVo> search = empdao.employeeAdminSearch(ev);
						if (search.isEmpty() == false && search.get(0).getId() == id1) {
							empdao.Update(ev);
							session.setAttribute("loginResult", "true");
						} else if (search.isEmpty() == true) {
							empdao.Update(ev);
							session.setAttribute("loginResult", "true");
						} else {
							session.setAttribute("department", "true");
						}
					} else if (roll.equals("Emp")) {
						empdao.Update(ev);
						session.setAttribute("loginResult", "true");
					}
				} else if (chack.equals("add") == false) {
					session.setAttribute("emailrecord", "true");
				}
			} else if (ch.isEmpty() == false) {
				logdao.loginupdate(loginvo);
				Employee_DAO empdao = new Employee_DAO();
				if (roll.equals("Head")) {
					ArrayList<EmployeeVo> search = empdao.employeeAdminSearch(ev);
					if (search.isEmpty() == false && search.get(0).getId() == id1) {
						empdao.Update(ev);
						session.setAttribute("loginResult", "true");
					} else if (search.isEmpty() == true) {
						empdao.Update(ev);
						session.setAttribute("loginResult", "true");
					} else {
						session.setAttribute("department", "true");
					}
				} else if (roll.equals("Emp")) {
					empdao.Update(ev);
					session.setAttribute("loginResult", "true");
				}
			}
		} else {
			session.setAttribute("wrong", "true");

		}
	}

	private void Entery(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
			java.sql.Time time = java.sql.Time.valueOf(localDateTime.toLocalTime());

			EmployeeVo emp = new EmployeeVo();
			emp.setId(id);

			EmpAttendanceVo empattendance = new EmpAttendanceVo();
			empattendance.setDate(date);
			empattendance.setEmpattendance(emp);

			AttDAO attdao = new AttDAO();
			ArrayList<EmpAttendanceVo> l1 = attdao.chakeEntery(empattendance);
			System.out.println(l1.size());
			if (l1.isEmpty() == true) {
				empattendance.setEmpattendance(emp);
				empattendance.setDate(date);
				empattendance.setStart(time);

				attdao.insertattendance(empattendance);
				session.setAttribute("entry", "true");
			} else if (l1.isEmpty() == false) {
				session.setAttribute("copydate", "true");
			}
		} catch (Exception e) {
			session.setAttribute("copyentry", "true");
			System.out.println(e);
		}
	}

	private void Exit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
			Time endtime = java.sql.Time.valueOf(localDateTime.toLocalTime());

			EmployeeVo emp = new EmployeeVo();
			emp.setId(id);

			EmpAttendance_outVo empattendanceout = new EmpAttendance_outVo();
			empattendanceout.setDate(date);
			empattendanceout.setEmpattout_id(emp);

			Att_Out_DAO attout = new Att_Out_DAO();
			ArrayList<EmpAttendance_outVo> listisempty = attout.chakeEntery(empattendanceout);
			System.out.println(listisempty.size());
			if (listisempty.isEmpty() == true) {
				EmpAttendanceVo empattendance = new EmpAttendanceVo();
				empattendance.setDate(date);
				empattendance.setEmpattendance(emp);

				ArrayList<EmpAttendanceVo> getattendance = attout.chackEnteryattenddance(empattendance);
				if (getattendance.isEmpty() == true) {
					session.setAttribute("checkattendance", "true");
				} else if (getattendance.isEmpty() == false) {
					String status = null;
					java.sql.Time starttime = getattendance.get(0).getStart();
					int atendanceid = getattendance.get(0).getId();

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

					EmpAttendanceVo empvo = new EmpAttendanceVo();
					empvo.setId(atendanceid);
					EmpAttendance_outVo attendance_out = new EmpAttendance_outVo();
					attendance_out.setAttout_id(empvo);
					attendance_out.setDate(date);
					attendance_out.setEnd((Time) endtime);
					attendance_out.setStatus(status);
					attendance_out.setEmpattout_id(emp);

					attout.employeeinsertendtime(attendance_out);
					session.setAttribute("out", "true");
				}
			} else if (listisempty.isEmpty() == false) {
				session.setAttribute("copydate", "true");
			}
		} catch (Exception e) {
			session.setAttribute("copyentry", "true");
		}
	}
}
