package DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.CompanyVO;
import VO.EmpAttendanceVo;
import VO.EmpAttendance_outVo;
import VO.EmployeeVo;
import VO.MessageVo;

public class DeleteDAO {
	public void deleteEmployee(CompanyVO vo){
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(vo);
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(EmployeeVo empvo, EmpAttendance_outVo empoutvo, EmpAttendanceVo empattvo,
			MessageVo messagevo) {
		
		try{
			System.out.println(empoutvo.getEmpattout_id().getId());
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("delete from EmpAttendance_outVo where id= "+empoutvo.getEmpattout_id().getId());
			query.executeUpdate();
			Query query1 = session.createQuery("delete from EmpAttendanceVo where id= "+empattvo.getEmpattendance().getId());
			query1.executeUpdate();
			Query query2 = session.createQuery("delete from MessageVo where id= "+messagevo.getEmployeeid().getId());
			query2.executeUpdate();
			Query query3 = session.createQuery("delete from EmployeeVo where id= "+empvo.getId());
			query3.executeUpdate();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
