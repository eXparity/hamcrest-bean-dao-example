/**
 * 
 */
package org.exparity.hamcrest.bean.sample.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Stewart
 *
 */
public class UserDAOHibernateImpl implements UserDAO {

	private final SessionFactory factory;

	public UserDAOHibernateImpl() {
		this.factory = new Configuration()
				.addAnnotatedClass(User.class)
					.addAnnotatedClass(UserComment.class)
					.buildSessionFactory(
							new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build());

	}

	@Override
	public User save(final User user) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.beginTransaction();
		try {
			session.save(user);
			txn.commit();
		} catch (final Exception e) {
			txn.rollback();
		}
		return user;
	}

	@Override
	public User getUserById(Long userId) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.beginTransaction();
		try {
			return (User) session.get(User.class, userId);
		} finally {
			txn.rollback();
		}
	}

}
