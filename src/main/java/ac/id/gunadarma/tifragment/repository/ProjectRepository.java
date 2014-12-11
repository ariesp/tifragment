package ac.id.gunadarma.tifragment.repository;

import java.util.List;

import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.utility.ListWrapper;
import ac.id.gunadarma.tifragment.model.Project;

public interface ProjectRepository {
	
	void save(Project project);
	
	void update(Project project);
	
	Project findProjectById(String projectId);

	ListWrapper<Project> findAll(int max, int page);
	
	List<User> findProjectManagers();
	
	long totalRow();
}
