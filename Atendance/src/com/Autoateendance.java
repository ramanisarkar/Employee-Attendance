package com;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

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

public class Autoateendance implements Runnable {

	@Override
	public void run() {
		Calendar myDate = Calendar.getInstance(); // set this up however you need it.
		int dow = myDate.get(Calendar.DAY_OF_WEEK);
		if (dow >= Calendar.MONDAY && dow <= Calendar.FRIDAY) {

			int id = 1;
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
			String start = "00:00:00";
			String end = "00:00:00";
			String end2 = "18:00:00";
			java.sql.Time time1 = java.sql.Time.valueOf(start);
			java.sql.Time time2 = java.sql.Time.valueOf(end);
			java.sql.Time time3 = java.sql.Time.valueOf(end2);
			CompanyVO comvo = new CompanyVO();
			comvo.setId(id);
			EmployeeVo empvo = new EmployeeVo();
			empvo.setCompnayid(comvo);

			Employee_DAO empdao = new Employee_DAO();
			ArrayList<EmployeeVo> emplist = empdao.compnaySearch(empvo);
			for (EmployeeVo employeeVo : emplist) {
				int empid = employeeVo.getId();
				empvo.setId(empid);

				EmpAttendanceVo empattvo = new EmpAttendanceVo();
				empattvo.setEmpattendance(empvo);
				empattvo.setDate(date);

				AttDAO attdao = new AttDAO();
				Att_Out_DAO attout = new Att_Out_DAO();
				ArrayList<EmpAttendanceVo> attlist = attdao.chakeEntery(empattvo);
				if (attlist.isEmpty() == true) {
					empattvo.setEmpattendance(empvo);
					empattvo.setDate(date);
					empattvo.setStart(time1);
					attdao.insertattendance(empattvo);

					ArrayList<EmpAttendanceVo> getattendance = attout.chackEnteryattenddance(empattvo);
					String status = "Leave";
					int atendanceid = getattendance.get(0).getId();
					empattvo.setId(atendanceid);
					EmpAttendance_outVo attendance_out = new EmpAttendance_outVo();
					attendance_out.setAttout_id(empattvo);
					attendance_out.setDate(date);
					attendance_out.setEnd(time2);
					attendance_out.setStatus(status);
					attendance_out.setEmpattout_id(empvo);
					attout.employeeinsertendtime(attendance_out);

					ArrayList<EmpAttendance_outVo> listisempty = attout.chakeEntery(attendance_out);
					int depid = listisempty.get(0).getEmpattout_id().getDep_id().getId();
					int outid = listisempty.get(0).getId();
					attendance_out.setId(outid);

					DepartmentVo depvo = new DepartmentVo();
					depvo.setId(depid);
					String messageatt = "Auto generated attendance";

					MessageVo message = new MessageVo();
					message.setDep_id(depvo);
					message.setEmployeeid(empvo);
					message.setAttendanceid(attendance_out);
					message.setMessage(messageatt);
					message.setCompanyid(comvo);
					message.setStatus("auto");

					MessageDAO medao = new MessageDAO();
					medao.inaert(message);

				} else if (attlist.isEmpty() == false) {
					EmpAttendance_outVo empattendanceout = new EmpAttendance_outVo();
					empattendanceout.setDate(date);
					empattendanceout.setEmpattout_id(empvo);
					ArrayList<EmpAttendance_outVo> listisempty = attout.chakeEntery(empattendanceout);
					if (listisempty.isEmpty() == true) {
						ArrayList<EmpAttendanceVo> getattendance = attout.chackEnteryattenddance(empattvo);
						if (getattendance.isEmpty() == false) {
							String status = null;
							java.sql.Time starttime = getattendance.get(0).getStart();
							int atendanceid = getattendance.get(0).getId();

							LocalTime t1 = starttime.toLocalTime();
							LocalTime t2 = time3.toLocalTime();

							long Duration = java.time.Duration.between(t1, t2).toMinutes();

							if (Duration <= 90) {
								status = "Leave";
							} else if (90 < Duration && Duration <= 540) {
								status = "Half Leave";
							} else if (540 < Duration) {
								status = "Present";
							}

							empattvo.setId(atendanceid);
							EmpAttendance_outVo attendance_out = new EmpAttendance_outVo();
							attendance_out.setAttout_id(empattvo);
							attendance_out.setDate(date);
							attendance_out.setEnd(time3);
							attendance_out.setStatus(status);
							attendance_out.setEmpattout_id(empvo);

							attout.employeeinsertendtime(attendance_out);

							ArrayList<EmpAttendance_outVo> listisempty1 = attout.chakeEntery(attendance_out);
							int depid = listisempty1.get(0).getEmpattout_id().getDep_id().getId();
							int outid = listisempty1.get(0).getId();
							attendance_out.setId(outid);

							DepartmentVo depvo = new DepartmentVo();
							depvo.setId(depid);
							String messageatt = "Auto generated  out attendance";

							MessageVo message = new MessageVo();
							message.setDep_id(depvo);
							message.setEmployeeid(empvo);
							message.setAttendanceid(attendance_out);
							message.setMessage(messageatt);
							message.setCompanyid(comvo);
							message.setStatus("auto");

							MessageDAO medao = new MessageDAO();
							medao.inaert(message);
						}
					}
				} else {
					System.out.println("All employee present");
				}
			}
		} else {
			System.out.println("this day was company has closed");
		}
	}
}
