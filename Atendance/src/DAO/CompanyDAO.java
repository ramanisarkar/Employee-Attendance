package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import VO.CompanyVO;
import VO.LoginVO;

public class CompanyDAO {

	public void insert(CompanyVO lo, LoginVO login) {
		try {
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(lo);
			session.save(login);
			transaction.commit();
			session.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public List<CompanyVO> search(){
		List<CompanyVO> li = new ArrayList<CompanyVO>();
		try{
			SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
			Session session = sessionfactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from CompanyVO ");
			li = q.list();
			transaction.commit();
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}
	
		public void delete(CompanyVO vo){
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

		public List<CompanyVO> Edit(CompanyVO v1) {

			List<CompanyVO> li1 = new ArrayList<CompanyVO>();
			try {
				SessionFactory sessionfactory = new AnnotationConfiguration().configure().buildSessionFactory();
				Session session = sessionfactory.openSession();
				Transaction transaction = session.beginTransaction();
				Query q = session.createQuery("from CompanyVO where id="+v1.getId());
				li1 = q.list();
				transaction.commit();
				session.close();
				}
			catch (Exception e) {
				return li1;
			}
			return li1;
		}
		
		
		public void update( CompanyVO v1) {

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

}
