package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.DepartmentVo;

public class DepartmentDAO {
	public void delete(DepartmentVo vo ){
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String dep = "UPDATE DepartmentVo set status =:status1 "+
					"WHERE id =:id1";
			Query query = session.createQuery(dep);
//			query.setParameter("status1", vo.getStatus());
			query.setParameter("id1", vo.getId());
			int result = query.executeUpdate();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<DepartmentVo> Edit(DepartmentVo v1) {

		List<DepartmentVo> li1 = new ArrayList<DepartmentVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentVo where id="+v1.getId());
			li1 = q.list();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}

		return li1;
	}
	
	public void update( DepartmentVo v1) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(v1);
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public String insert(DepartmentVo v1){
		String s=null;
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(v1);
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			s="erorr";
			return s;
		}
		return s="add";
	}

	public ArrayList<DepartmentVo>search(DepartmentVo vo){
		List<DepartmentVo> li = new ArrayList<DepartmentVo>();
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from DepartmentVo AS d where d.com_id =:id1");
			q.setParameter("id1", vo.getCom_id());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<DepartmentVo>) li;
	}

}
