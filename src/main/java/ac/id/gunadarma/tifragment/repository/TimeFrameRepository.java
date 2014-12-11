package ac.id.gunadarma.tifragment.repository;

import java.util.List;

import ac.id.gunadarma.tifragment.model.DetailTimeFrame;
import ac.id.gunadarma.tifragment.model.Project;
import ac.id.gunadarma.tifragment.model.TimeFrame;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.utility.ListWrapper;

public interface TimeFrameRepository {

	void save(TimeFrame timeFrame);
	
	void save(DetailTimeFrame detailTimeFrame);
	
	void update(TimeFrame timeFrame);
	
	void update(DetailTimeFrame detailTimeFrame);
	
	TimeFrame findTimeFrameById(String timeFrameId);
	
	DetailTimeFrame findTaskById(String taskId);

	ListWrapper<TimeFrame> findAll(int max, int page);
	
	List<DetailTimeFrame> findTask(String timeFrameId);	
	
	List<DetailTimeFrame> findTaskDefault(String userId, String timeFrameId);
	
	List<DetailTimeFrame> findAllTaskDefault(String userId);
	
	List<Project> findProjects();
	
	List<User> findDevelopers();
	
	long totalRow();
}
