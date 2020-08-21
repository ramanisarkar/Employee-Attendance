package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.EmpAttendanceVo;

public class AttDAO {

	public ArrayList<EmpAttendanceVo> chakeEntery(EmpAttendanceVo empattendance) {
		List<EmpAttendanceVo> li1 = new ArrayList<EmpAttendanceVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmpAttendanceVo As a where a.date= :date and a.empattendance= :empid ");
			q.setParameter("date",empattendance.getDate());
			q.setParameter("empid",empattendance.getEmpattendance());
			li1 = q.list();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			ArrayList<EmpAttendanceVo> l2 = null;
			return l2 ;
		}

		return (ArrayList<EmpAttendanceVo>)li1;
	}

	public void insertattendance(EmpAttendanceVo empattendance) {
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(empattendance);
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void employeeAttendanceUpdate(EmpAttendanceVo attendance) {
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String dep = "UPDATE EmpAttendanceVo set start =:start1 "+
					"WHERE id =:id1";
			Query query = session.createQuery(dep);
			query.setParameter("start1", attendance.getStart());
			query.setParameter("id1", attendance.getId());
			int result = query.executeUpdate();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();		
		}
		
	}

}
