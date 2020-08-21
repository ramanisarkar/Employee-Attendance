package DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.EmpAttendanceVo;
import VO.EmpAttendance_outVo;

public class Att_Out_DAO {

	public ArrayList<EmpAttendance_outVo> chakeEntery(EmpAttendance_outVo empattendanceout) {
		List<EmpAttendance_outVo> li1 = new ArrayList<EmpAttendance_outVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmpAttendance_outVo As a where a.date= :date and a.empattout_id= :empid ");
			q.setParameter("date", empattendanceout.getDate());
			q.setParameter("empid", empattendanceout.getEmpattout_id());
			li1 = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (ArrayList<EmpAttendance_outVo>) li1;
	}

	public ArrayList<EmpAttendanceVo> chackEnteryattenddance(EmpAttendanceVo empattendance) {
		List<EmpAttendanceVo> li1 = new ArrayList<EmpAttendanceVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmpAttendanceVo As a where a.date= :date and a.empattendance= :empid");
			q.setParameter("date", empattendance.getDate());
			q.setParameter("empid", empattendance.getEmpattendance());
			li1 = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (ArrayList<EmpAttendanceVo>) li1;
	}

	public void employeeinsertendtime(EmpAttendance_outVo attendance_out) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(attendance_out);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<EmpAttendance_outVo > empEdit(EmpAttendance_outVo attout) {
		List<EmpAttendance_outVo > li1 = new ArrayList<EmpAttendance_outVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmpAttendance_outVo where empattout_id= :emp");
			q.setParameter("emp", attout.getEmpattout_id());
			li1 = q.list();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<EmpAttendance_outVo>) li1;
	}
	
	public ArrayList<EmpAttendanceVo > empEdit(EmpAttendanceVo attout) {
		List<EmpAttendanceVo> li1 = new ArrayList<EmpAttendanceVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmpAttendanceVo where  empattendance = :emp");
			q.setParameter("emp", attout.getEmpattendance());
			li1 = q.list();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<EmpAttendanceVo>) li1;
	}

	public ArrayList<EmpAttendance_outVo> searchAttendance(EmpAttendance_outVo attout) {
		List<EmpAttendance_outVo> li1 = new ArrayList<EmpAttendance_outVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmpAttendance_outVo where  id = :id");
			q.setParameter("id", attout.getId());
			li1 = q.list();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<EmpAttendance_outVo>) li1;
	}

	public void employeeAttendanceOutUpdate(EmpAttendance_outVo attendance_out) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("update EmpAttendance_outVo as a set a.end =:end1 , a.status =:status1 where a.id =:id1");
			q.setParameter("id1", attendance_out.getId());
			q.setParameter("end1", attendance_out.getEnd());
			q.setParameter("status1", attendance_out.getStatus());
			q.executeUpdate();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<EmpAttendance_outVo> searchAttendance(EmpAttendance_outVo empattoutvo, java.sql.Date start, java.sql.Date end) {
		List<EmpAttendance_outVo> li1 = new ArrayList<EmpAttendance_outVo>();
		try {
			System.out.println(empattoutvo.getEmpattout_id());
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
//			SELECT* FROM   
//			table_name WHERE cast (datediff (day, 0, yourdate) as datetime) = '2012-12-12'  
//			Query q = session.createQuery("from EmpAttendance_outVo as a where a.date BETWEEN CAST()");
			String hql= "from EmpAttendance_outVo As e where e.empattout_id =:empattout_id and date BETWEEN :frmdate and :todate";
			Query query =session.createQuery(hql);
			query.setParameter("empattout_id", empattoutvo.getEmpattout_id());
            query.setParameter("frmdate", start ,Hibernate.DATE);
            query.setParameter("todate", end ,Hibernate.DATE );
			li1 = query.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return (ArrayList<EmpAttendance_outVo>) li1;
		
	}

}
