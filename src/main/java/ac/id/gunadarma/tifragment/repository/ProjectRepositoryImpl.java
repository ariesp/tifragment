package ac.id.gunadarma.tifragment.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ac.id.gunadarma.tifragment.model.Project;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.utility.ListWrapper;
import ch.genidea.geniweb.base.utility.Paging;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

	@PersistenceContext
	private EntityManager em;
		
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(Project project) {
		em.persist(project);
		em.flush();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(Project project) {
		em.merge(project);
		em.flush();
	}

	@Override
	@Transactional(readOnly = true)
	public Project findProjectById(String projectId) {
		Project project = em.find(Project.class, projectId);
		return project;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ListWrapper<Project> findAll(int max, int page) {
		Query query = em.createQuery("from Project p order by p.createdDate desc");
		if (max > 0) {
			query.setMaxResults(max);
		}
		query.setFirstResult((page - 1) * max);
		
 		long rowcount = totalRow();
		ListWrapper<Project> list = new ListWrapper<Project>();
		list.setCurrentPage(page);
		list.setList(query.getResultList());
		list.setLimit(max);
		list.setRowCount(rowcount);
		list.setTotalPage(Paging.getTotalPage(rowcount, max));
		
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> findProjectManagers() {
		Query query = em.createQuery("from User u where u.enabled != 0 and u.position = 'projectManager' and u.username != 'deodorant'");		
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public long totalRow() {
		Query query = em.createQuery("from Project p");
		return query.getResultList().size();
	}
}