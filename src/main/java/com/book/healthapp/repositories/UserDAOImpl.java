package com.book.healthapp.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.healthapp.domain.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
	public List<User> findByEmail(String email) {
		return this.sessionFactory.getCurrentSession()
                .createQuery("from User u where u.email = :email")
                .setString("email", email)
                .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByEmailAndPassword(String email, String password) {
		return this.sessionFactory.getCurrentSession()
                .createQuery("from User u where u.email= :email and u.password = :password")
                .setString("email", email)
                .setString("password", password)
                .list();
	}

	@Override
	public User save(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
		return user;
	}
}
