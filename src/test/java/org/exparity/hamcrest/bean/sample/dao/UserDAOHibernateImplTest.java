package org.exparity.hamcrest.bean.sample.dao;

import static org.exparity.hamcrest.BeanMatchers.theSameBeanAs;
import static org.exparity.stub.bean.BeanBuilder.aRandomInstanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

import java.util.Date;

import org.exparity.hamcrest.beans.comparators.IsDateTime;
import org.junit.Test;

public class UserDAOHibernateImplTest {

	@Test
	public void canSaveAUser() {
		User user = aRandomInstanceOf(User.class).build();
		UserDAOHibernateImpl dao = new UserDAOHibernateImpl();
		User saved = dao.save(user);
		User loaded = dao.getUserById(saved.getId());
		assertThat(loaded, not(sameInstance(user)));
		assertThat(loaded, theSameBeanAs(user).compareType(Date.class, new IsDateTime()));
	}
}
