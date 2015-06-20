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
		Transaction txn = factory.getCurrentSession().beginTransaction();
		try {
			factory.getCurrentSession().save(user);
			txn.commit();
		} catch (final Exception e) {
			txn.rollback();
		}
		return user;
	}

	@Override
	public User getUserById(Long userId) {
		Transaction txn = factory.getCurrentSession().beginTransaction();
		try {
			return (User) factory.getCurrentSession().get(User.class, userId);
		} finally {
			txn.rollback();
		}
	}

}
