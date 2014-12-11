package ch.genidea.geniweb.base.repository;

import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.utility.ListWrapper;

public interface UserRepository {

	void save(User user);

	void update(User user);

	User findUserById(String userId);

	User findUserByUsername(String username);

	ListWrapper<User> findAll(int max, int page);
	
	ListWrapper<User> findAllDeveloper(int max, int page);
	
	boolean isUserAlreadyExists(String username);
	
	long totalRow();
	
	long totalRowAllDeveloper();
		
}
