package org.exparity.hamcrest.bean.sample.dao;

import static org.exparity.hamcrest.BeanMatchers.theSameBeanAs;
import static org.exparity.stub.bean.BeanBuilder.aRandomInstanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class UserDAOHibernateImplTest {

	@Test
	public void canSaveAUser() {
		User user = aRandomInstanceOf(User.class).excludeProperty("Id").build();
		UserDAOHibernateImpl dao = new UserDAOHibernateImpl("hibernate.properties");
		User saved = dao.save(user);
		User loaded = dao.getUserById(saved.getId());
		assertThat(loaded, not(sameInstance(user)));
		assertThat(loaded, theSameBeanAs(user));
	}
}
