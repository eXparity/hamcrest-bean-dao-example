package org.exparity.hamcrest.bean.sample.dao;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

public class UserDAOHibernateImpl implements UserDAO {

	private final SessionFactory factory;

	public UserDAOHibernateImpl(final String resourceFile) {
		this.factory = new Configuration()
				.addAnnotatedClass(User.class)
					.addAnnotatedClass(UserComment.class)
					.buildSessionFactory(
							new StandardServiceRegistryBuilder().loadProperties(resourceFile).build());

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
