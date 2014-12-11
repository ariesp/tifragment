package ac.id.gunadarma.tifragment.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ac.id.gunadarma.tifragment.model.DetailTimeFrame;
import ac.id.gunadarma.tifragment.model.Project;
import ac.id.gunadarma.tifragment.model.TimeFrame;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.utility.ListWrapper;
import ch.genidea.geniweb.base.utility.Paging;

@Repository
public class TimeFrameRepositoryImpl implements TimeFrameRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(TimeFrame timeFrame) {
		em.persist(timeFrame);
		em.flush();
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(DetailTimeFrame detailTimeFrame) {
		em.persist(detailTimeFrame);
		em.flush();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(TimeFrame timeFrame) {
		em.merge(timeFrame);
		em.flush();
	}	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(DetailTimeFrame detailTimeFrame) {
		em.merge(detailTimeFrame);
		em.flush();		
	}

	@Override
	@Transactional(readOnly = true)
	public TimeFrame findTimeFrameById(String timeFrameId) {
		TimeFrame timeFrame = em.find(TimeFrame.class, timeFrameId);
		return timeFrame;
	}
	
	@Override
	@Transactional(readOnly = true)
	public DetailTimeFrame findTaskById(String taskId) {
		Query query = em.createQuery("from DetailTimeFrame dt where dt.uuid='" + taskId + "' and dt.status != 0");
		DetailTimeFrame detailTimeFrame = (DetailTimeFrame) query.getSingleResult();
		return detailTimeFrame;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public ListWrapper<TimeFrame> findAll(int max, int page) {
		Query query = em.createQuery("from TimeFrame t where t.status != 0 order by t.createdDate desc");
		if (max > 0) {
			query.setMaxResults(max);
		}
		query.setFirstResult((page - 1) * max);
		
 		long rowcount = totalRow();
		ListWrapper<TimeFrame> list = new ListWrapper<TimeFrame>();
		list.setCurrentPage(page);
		list.setList(query.getResultList());
		list.setLimit(max);
		list.setRowCount(rowcount);
		list.setTotalPage(Paging.getTotalPage(rowcount, max));
		
		return list;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DetailTimeFrame> findTaskDefault(String userId, String timeFrameId) {
		Query query = em.createQuery("from DetailTimeFrame dt where dt.timeFrame.uuid = '" + timeFrameId + "' and dt.status != 0 and dt.user.uuid = '" + userId + "' order by dt.createdDate desc");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DetailTimeFrame> findAllTaskDefault(String userId) {
		Query query = em.createQuery("from DetailTimeFrame dt where dt.status != 0 and dt.user.uuid = '" + userId + "' order by dt.createdDate desc");
		return query.getResultList();
	}
			
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DetailTimeFrame> findTask(String timeFrameId) {
		Query query = em.createQuery("from DetailTimeFrame dt where dt.timeFrame.uuid = '" + timeFrameId + "' and dt.status != 0 order by dt.createdDate desc");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Project> findProjects() {
		Query query = em.createQuery("from Project p where p.timeFrame.uuid is null");		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<User> findDevelopers() {
		Query query = em.createQuery("from User u where u.position = 'developer'");	
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public long totalRow() {
		Query query = em.createQuery("from TimeFrame t where t.status != 0");
		return query.getResultList().size();
	}
}
