package com.book.healthapp.repositories;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.book.healthapp.domain.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
	public List<User> findByEmail(String email) {
    	Session session = this.sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("findByEmail");  
        query.setString("email", email); 
        return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByEmailAndPassword(String email, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(User.class)
	     .add(Restrictions.eq("email", email))
	     .add(Restrictions.eq("password", password))
	     .list();
//        Query query = session.getNamedQuery("findByEmailAndPassword");  
//        query.setString("email", email); 
//        query.setString("password", password); 
//        return query.list();
//		return this.sessionFactory.getCurrentSession()
//                .createQuery("from User u where u.email= :email and u.password = :password")
//                .setString("email", email)
//                .setString("password", password)
//                .list();
	}

	@Override
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User save(User user) {
//		Session session = this.sessionFactory.openSession();
//		session.save(user);
//		session.flush();
		Session session = this.sessionFactory.openSession();
//		session.beginTransaction();
//		Serializable userId = session.save(user);
		session.persist(user);
//		user.setFirstName("dummyName");
//		session.getTransaction().commit();
		session.close();
		return user;
	}
	
	@Override
	public void update(User user) {
//		Session session = this.sessionFactory.openSession();
//		User persistentUser = (User) session.load(User.class, new Integer(user.getId()));
//		Transaction tx = session.beginTransaction();
//		persistentUser.setFirstName(user.getFirstname());
//		persistentUser.setLastname(user.getLastname());
//		session.update(persistentUser);		
//		tx.commit();
		
//		Session session = this.sessionFactory.openSession();
//		Transaction tx1 = session.beginTransaction();
//		User persistentUser = (User) session.load(User.class, new Integer(user.getId()));
//		tx1.commit();
//		Transaction tx2 = session.beginTransaction();
//		user.setEmail(persistentUser.getEmail());
//		user.setPassword(persistentUser.getPassword());
//		session.merge(user);		
//		tx2.commit();
		
		
	}
}
