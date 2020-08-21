package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.EmployeeVo;
import VO.LoginVO;

public class Employee_DAO {
	public String  insert(EmployeeVo v1 ){
		String chack;
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(v1);
			transaction.commit();
			session.close();
			
		}
		catch (Exception e) {
			return chack="erorr"; 
		}
		return chack="add";
	}
	public void insert(LoginVO v2) {
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(v2);
			transaction.commit();
			session.close();
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<EmployeeVo> compnaySearch(EmployeeVo e1){
		List<EmployeeVo> li = new ArrayList<EmployeeVo>();
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmployeeVo AS e where e.compnayid =:id1");
			q.setParameter("id1",e1.getCompnayid());
//			q.setParameter("status1",e1.getStatus());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<EmployeeVo>) li;
	}
	
	public ArrayList<EmployeeVo> departmentSearch(EmployeeVo e1){
		List<EmployeeVo> li = new ArrayList<EmployeeVo>();
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmployeeVo AS e where e.compnayid =:id1 and e.dep_id =:dep");
			q.setParameter("id1",e1.getCompnayid());
			q.setParameter("dep",e1.getDep_id());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<EmployeeVo>) li;
	}
	
	public ArrayList<EmployeeVo> departmentSearchEmployee(EmployeeVo e1){
		List<EmployeeVo> li = new ArrayList<EmployeeVo>();
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmployeeVo AS e where e.dep_id =:dep");
			q.setParameter("dep",e1.getDep_id());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<EmployeeVo>) li;
	}
	
	
	public void delete(EmployeeVo v1){
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			String dep = "UPDATE EmployeeVo set status =:status1 "+
					"WHERE id =:id1";
			
			Query query = session.createQuery(dep);
			query.setParameter("status1", v1.getStatus());
			query.setParameter("id1", v1.getId());
			int r1 = query.executeUpdate();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EmployeeVo> employeeSearch(EmployeeVo v1) {

		List<EmployeeVo> li1 = new ArrayList<EmployeeVo>();
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmployeeVo where id="+v1.getId());
			li1 = q.list();
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}

		return li1;
	}
	
	public void Update(EmployeeVo v1) {
		
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
	
	public ArrayList<EmployeeVo> employeeAdminSearch(EmployeeVo e1){
		List<EmployeeVo> li = new ArrayList<EmployeeVo>();
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from EmployeeVo AS e where e.dep_id =:dep And e.roll =:roll1");
			q.setParameter("dep",e1.getDep_id());
			q.setParameter("roll1",e1.getRoll());
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<EmployeeVo>) li;
	}

	

}
