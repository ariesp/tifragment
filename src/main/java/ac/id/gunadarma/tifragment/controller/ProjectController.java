package ac.id.gunadarma.tifragment.controller;

import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;
import static ch.genidea.geniweb.base.config.ConstApp.MAX_ROW;
import static ch.genidea.geniweb.base.config.ConstApp.MENU_ACTIVE;
import static ch.genidea.geniweb.base.config.ConstApp.PAGE;
import static ch.genidea.geniweb.base.config.ConstApp.PROJECT;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ac.id.gunadarma.tifragment.StatusEnum;
import ac.id.gunadarma.tifragment.form.ProjectForm;
import ac.id.gunadarma.tifragment.model.Project;
import ac.id.gunadarma.tifragment.repository.ProjectRepository;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.repository.UserRepository;
import ch.genidea.geniweb.base.utility.ListWrapper;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping(value = "/base/page/admin/project/create", method = RequestMethod.GET)
    public String create(Model model) {
    	model.addAttribute(MENU_ACTIVE, PROJECT);
        model.addAttribute("project", new ProjectForm());
        model.addAttribute("users", getProjectLeaders());
        return "base/page/admin/project/create";
    }
    
    @RequestMapping(value = "/base/page/admin/project/createsub{id}", method = RequestMethod.GET)
    public String createSub(Model model, HttpServletRequest request) {
    	ProjectForm form = new ProjectForm();
    	form.setParentUuid(request.getParameter("projectId"));
    	model.addAttribute(MENU_ACTIVE, PROJECT);
        model.addAttribute("project", form);
        model.addAttribute("users", getProjectLeaders());
        return "base/page/admin/project/createsub";
    }
    
    @RequestMapping(value = "/base/page/admin/project/detail{id}", method = RequestMethod.GET)
    public ModelAndView view(Model model, HttpServletRequest request) {
    	Project project = projectRepository.findProjectById(request.getParameter("projectId"));
    	model.addAttribute(MENU_ACTIVE, PROJECT);
        return new ModelAndView("base/page/admin/project/detail", "project", project);
    }
    
	@RequestMapping(value = "/base/page/admin/project/createproject", method = RequestMethod.POST)
	public ModelAndView createProject(HttpServletRequest request, @ModelAttribute("project") @Valid ProjectForm form, BindingResult result) {
		if (!result.hasErrors()) {
			Project project = new Project();
			User user = userRepository.findUserById(form.getUserUuid());
			project.setProjectName(form.getName());
			project.setDescription(form.getDescription());
			project.setStatus(StatusEnum.ACTIVE);
			project.setUser(user);
			
			project.setCreatedBy(getAuthenticationContext().getName());
			project.setCreatedDate(new Date());
			
			projectRepository.save(project);
						
			if (form.getSubProject() == null || form.getSubProject().isEmpty()) 
				return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/project/detail"), "projectId", project.getUuid());
			return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/project/createsub"), "projectId", project.getUuid());
		} else {
			return new ModelAndView("/base/page/admin/project/create");
		}
	}
	
	@RequestMapping(value = "/base/page/admin/project/createsubproject", method = RequestMethod.POST)
	public ModelAndView createSubProject(HttpServletRequest request, @ModelAttribute("project") @Valid ProjectForm form, BindingResult result) {
		if (!result.hasErrors()) {
			Project project = projectRepository.findProjectById(form.getParentUuid());			
			Project subProject = new Project();
			User user = userRepository.findUserById(form.getUserUuid());
			
			subProject.setProjectName(form.getName());
			subProject.setDescription(form.getDescription());
			subProject.setStatus(StatusEnum.ACTIVE);
			subProject.setUser(user);
			subProject.setProject(project);
			subProject.setCreatedBy(getAuthenticationContext().getName());
			subProject.setCreatedDate(new Date());
			
			projectRepository.save(subProject);		
			
			if (form.getMore() == null || form.getMore().isEmpty()) 
				return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/project/list"));
			return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/project/createsub"), "projectId", project.getUuid());
		} else {
			return new ModelAndView("/base/page/admin/project/create");
		}
	}
	
    @RequestMapping(value = "/base/page/admin/project/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(Model model, @PathVariable("id") String projectId) {
    	model.addAttribute(MENU_ACTIVE, PROJECT);
    	Project project = projectRepository.findProjectById(projectId);
    	ProjectForm form = new ProjectForm(project.getUuid(), project.getProjectName(), project.getDescription(), project.getUser().getUuid());
    	model.addAttribute("users", getProjectLeaders());
    	return new ModelAndView("/base/page/admin/project/edit", "project", form);
    }
    
    @RequestMapping(value = "/base/page/admin/project/updateproject", method = RequestMethod.POST)
    public ModelAndView updateProject(HttpServletRequest request, @ModelAttribute("project") @Valid ProjectForm form) {
    	Project project = projectRepository.findProjectById(form.getUuid());
    	User user = userRepository.findUserById(form.getUserUuid());
		project.setProjectName(form.getName());
		project.setDescription(form.getDescription());
		project.setStatus(StatusEnum.ACTIVE);
		project.setUser(user);
		project.setUpdateBy(getAuthenticationContext().getName());
		project.setUpdateDate(new Date());
		
		projectRepository.update(project);
		
		return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/project/detail"), "projectId", project.getUuid());
    }
	
	@RequestMapping(value = "/base/page/admin/project/list", method = RequestMethod.GET)
	public ModelAndView listProject(Model model) {
		ListWrapper<Project> projects = projectRepository.findAll(MAX_ROW, PAGE);
		model.addAttribute(MENU_ACTIVE, PROJECT);
		model.addAttribute("totalPage", projects.getTotalPage());
		model.addAttribute("max", projects.getLimit());
		model.addAttribute("page", projects.getCurrentPage());
		return new ModelAndView("/base/page/admin/project/list", "projects", projects);
	}

	@RequestMapping(value = "/base/page/admin/project/list/{max}/{page}", method = RequestMethod.GET)
	public ModelAndView listProjectPage(@PathVariable("max") int max, @PathVariable("page") int page, Model model) {
		ListWrapper<Project> projects = projectRepository.findAll(max, page);
		model.addAttribute(MENU_ACTIVE, PROJECT);
		model.addAttribute("totalPage", projects.getTotalPage());
		model.addAttribute("max", projects.getLimit());
		model.addAttribute("page", projects.getCurrentPage());
		return new ModelAndView("/base/page/admin/project/list", "projects", projects);
	}
    
    private List<User> getProjectLeaders() {
    	return projectRepository.findProjectManagers();
    }
}