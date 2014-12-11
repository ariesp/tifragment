package ch.genidea.geniweb.base.repository;

import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.utility.ListWrapper;
import ch.genidea.geniweb.base.utility.Paging;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(User user) {
		em.persist(user);
		em.flush();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(User user) {
		em.merge(user);
		em.flush();
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public ListWrapper<User> findAll(int max, int page) {
		Query query = em.createQuery("from User u where u.enabled != 0 and u.username != 'deodorant' "
				+ "and u.username != '" + getAuthenticationContext().getName() + "' order by u.createdDate desc");
		if (max > 0) {
			query.setMaxResults(max);
		}
		query.setFirstResult((page - 1) * max);
		
 		long rowcount = totalRow();
		ListWrapper<User> list = new ListWrapper<>();
		list.setCurrentPage(page);
		list.setList(query.getResultList());
		list.setLimit(max);
		list.setRowCount(rowcount);
		list.setTotalPage(Paging.getTotalPage(rowcount, max));
		
		return list;
	}	
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public ListWrapper<User> findAllDeveloper(int max, int page) {
		Query query = em.createQuery("from User u where u.enabled != 0 and u.username != 'deodorant' "
				+ "and u.username != '" + getAuthenticationContext().getName() + "' and u.position != 'projectManager' order by u.createdDate desc");
		if (max > 0) {
			query.setMaxResults(max);
		}
		query.setFirstResult((page - 1) * max);
		
 		long rowcount = totalRowAllDeveloper();
		ListWrapper<User> list = new ListWrapper<>();
		list.setCurrentPage(page);
		list.setList(query.getResultList());
		list.setLimit(max);
		list.setRowCount(rowcount);
		list.setTotalPage(Paging.getTotalPage(rowcount, max));
		
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public long totalRow() {
		Query query = em.createQuery("from User u where u.enabled != 0 and u.username != 'deodorant' "
				+ "and u.username != '" + getAuthenticationContext().getName() + "'");
		return query.getResultList().size();
	}	

	@Override
	@Transactional(readOnly = true)
	public long totalRowAllDeveloper() {
		Query query = em.createQuery("from User u where u.enabled != 0 and u.username != 'deodorant' "
				+ "and u.username != '" + getAuthenticationContext().getName() + "' and u.position != 'projectManager'");
		return query.getResultList().size();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isUserAlreadyExists(String username) {
		Long count = (Long) em.createQuery("select count(u.username) from User u where u.username= :username")
				.setParameter("username", username).getSingleResult();
		if (count.compareTo(0l) > 0)
			return true;
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(String userId) {
		User user = em.find(User.class, userId);
		return user;
	}
	
	@Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username){
        Query query = em.createQuery("select u from User u where u.username = :username");
        query.setParameter("username", username);  
        User user = (User) query.getSingleResult();
        return user;
    }
}