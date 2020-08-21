package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.MessageVo;

public class MessageDAO {
	public String inaert(MessageVo message) {
		String chack = null;
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(message);
			transaction.commit();
			session.close();
		}

		catch (Exception e) {
			return chack = "erorr";
		}
		return chack = "add";
	}

	public ArrayList<MessageVo> departmentSearch(MessageVo vo) {
		List<MessageVo> li = new ArrayList<MessageVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from MessageVo AS d where d.dep_id =:id1");
			q.setParameter("id1", vo.getDep_id());
			li = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<MessageVo>) li;
	}

	public ArrayList<MessageVo> companySearch(MessageVo vo) {
		List<MessageVo> li = new ArrayList<MessageVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from MessageVo AS d where d.companyid =:id1");
			q.setParameter("id1", vo.getCompanyid());
			li = q.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<MessageVo>) li;
	}

	public void notificationdelete(MessageVo messvo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(messvo);
			transaction.commit();
			session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void statusmodify(MessageVo messagevo) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String log = "UPDATE MessageVo set status =:status1 , lasteditedby =:lasteditedby1 , lastedited =:lastedited1 "+
					"WHERE attendanceid =:attid";		
			Query query1 = session.createQuery(log);
			query1.setParameter("status1", messagevo.getStatus());
			query1.setParameter("attid", messagevo.getAttendanceid());
			query1.setParameter("lasteditedby1", messagevo.getLasteditedby());
			query1.setParameter("lastedited1", messagevo.getLastedited());
			int result1 = query1.executeUpdate();
			
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
