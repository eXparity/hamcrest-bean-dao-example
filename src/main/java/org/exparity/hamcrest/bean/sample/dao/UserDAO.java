package org.exparity.hamcrest.bean.sample.dao;

public interface UserDAO {
	public User save(final User user);
	public User getUserById(final Long userId);
}
