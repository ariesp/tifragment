package ac.id.gunadarma.tifragment.controller;

import static ac.id.gunadarma.tifragment.FormatHelper.formatDate;
import static ac.id.gunadarma.tifragment.FormatHelper.parseDate;
import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;
import static ch.genidea.geniweb.base.config.ConstApp.MAX_ROW;
import static ch.genidea.geniweb.base.config.ConstApp.MENU_ACTIVE;
import static ch.genidea.geniweb.base.config.ConstApp.PAGE;
import static ch.genidea.geniweb.base.config.ConstApp.TIME_FRAME;
import static ac.id.gunadarma.tifragment.StatusEnum.ACTIVE;
import static ac.id.gunadarma.tifragment.StatusEnum.NOT_ACTIVE;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ac.id.gunadarma.tifragment.FormatHelper;
import ac.id.gunadarma.tifragment.form.TimeFrameForm;
import ac.id.gunadarma.tifragment.form.ViewBar;
import ac.id.gunadarma.tifragment.form.ViewBar.Series;
import ac.id.gunadarma.tifragment.form.ViewBar.Series.Range;
import ac.id.gunadarma.tifragment.model.DetailTimeFrame;
import ac.id.gunadarma.tifragment.model.Project;
import ac.id.gunadarma.tifragment.model.TimeFrame;
import ac.id.gunadarma.tifragment.repository.ProjectRepository;
import ac.id.gunadarma.tifragment.repository.TimeFrameRepository;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.repository.UserRepository;
import ch.genidea.geniweb.base.utility.ListWrapper;

@Controller
public class TimeFrameController {

	@Autowired
	private TimeFrameRepository timeFrameRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/base/page/admin/timeframe/create", method = RequestMethod.GET)
	public ModelAndView create(Model model) {
		TimeFrameForm form = new TimeFrameForm();
		form.setStartDate(formatDate(new Date()));
		form.setEndDate(formatDate(new Date()));
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		model.addAttribute("timeframe", form);
		model.addAttribute("projects", getProjects());
		return new ModelAndView("base/page/admin/timeframe/create");
	}
	
    @RequestMapping(value = "/base/page/admin/timeframe/createdetail{id}", method = RequestMethod.GET)
    public ModelAndView createDetail(Model model, HttpServletRequest request) {
    	String uuid = request.getParameter("timeframeId");
    	TimeFrameForm form = new TimeFrameForm();
    	TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(uuid);
    	String startDate = formatDate(timeFrame.getStartDate());
    	String endDate = formatDate(timeFrame.getEndDate());
    	
    	form.setTimeFrameUuid(uuid);
		form.setStartDate(startDate);
		form.setEndDate(endDate);
		
    	model.addAttribute(MENU_ACTIVE, TIME_FRAME);
    	model.addAttribute("timeframe", form);
    	model.addAttribute("startDate", startDate);
    	model.addAttribute("endDate", endDate);
        model.addAttribute("users", getDevelopers());
        return new ModelAndView("base/page/admin/timeframe/createdetail");
    }
    
    @RequestMapping(value = "/base/page/admin/timeframe/detail{id}", method = RequestMethod.GET)
    public ModelAndView view(Model model, HttpServletRequest request) {
    	TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(request.getParameter("timeframeId"));
    	List<DetailTimeFrame> tasks = timeFrameRepository.findTask(request.getParameter("timeframeId"));
    	model.addAttribute("tasks", tasks);
    	model.addAttribute(MENU_ACTIVE, TIME_FRAME);
        return new ModelAndView("base/page/admin/timeframe/detail", "timeframe", timeFrame);
    }
	
	@RequestMapping(value = "/base/page/admin/timeframe/createtimeframe", method = RequestMethod.POST)
	public ModelAndView createTimeFrame(HttpServletRequest request, @ModelAttribute("timeframe") @Valid TimeFrameForm form, BindingResult result) throws ParseException {
		if (!result.hasErrors()) {
			Project project = projectRepository.findProjectById(form.getProjectUuid());			
					
			TimeFrame timeFrame = new TimeFrame();			
			timeFrame.setTimeFrameName(form.getName());
			timeFrame.setDescription(form.getDescription());
			timeFrame.setStartDate(parseDate(form.getStartDate()));
			timeFrame.setEndDate(parseDate(form.getEndDate()));
			timeFrame.setCreatedBy(getAuthenticationContext().getName());
			timeFrame.setCreatedDate(new Date());
			timeFrame.setStatus(ACTIVE);
							
			timeFrameRepository.save(timeFrame);
			
			project.setTimeFrame(timeFrame);
			project.setUpdateBy(getAuthenticationContext().getName());
			project.setUpdateDate(new Date());
			projectRepository.update(project);			
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/timeframe/createdetail"), "timeframeId", timeFrame.getUuid());
		} else {
			return new ModelAndView("/base/page/admin/timeframe/create");			
		}
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/createtask", method = RequestMethod.POST)
	public ModelAndView createTask(HttpServletRequest request, @ModelAttribute("timeframe") @Valid TimeFrameForm form, BindingResult result) throws ParseException {
		if (!result.hasErrors()) {
			TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(form.getTimeFrameUuid());
			User user = userRepository.findUserById(form.getUserUuid());
			
			DetailTimeFrame detailTimeFrame = new DetailTimeFrame();
			detailTimeFrame.setTaskName(form.getTaskName());
			detailTimeFrame.setDescription(form.getDescription());
			detailTimeFrame.setUser(user);
			detailTimeFrame.setStartDate(parseDate(form.getStartDate()));
			detailTimeFrame.setEndDate(parseDate(form.getEndDate()));
			detailTimeFrame.setStatus(ACTIVE);
			detailTimeFrame.setTimeFrame(timeFrame);
			detailTimeFrame.setCreatedBy(getAuthenticationContext().getName());
			detailTimeFrame.setCreatedDate(new Date());
			
			timeFrameRepository.save(detailTimeFrame);
			
			if (form.getMore() == null || form.getMore().isEmpty()) 
				return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/timeframe/detail"), "timeframeId", timeFrame.getUuid());
			return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/timeframe/createdetail"), "timeframeId", timeFrame.getUuid());
		} else {
			return new ModelAndView("base/page/admin/timeframe/createdetail");
		}
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/edit/{id}", method =  RequestMethod.GET)
	public ModelAndView edit(Model model, @PathVariable("id") String timeFrameId) {
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);		
		
		TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(timeFrameId);
		TimeFrameForm timeFrameForm = new TimeFrameForm();
		timeFrameForm.setUuid(timeFrame.getUuid());
    	timeFrameForm.setName(timeFrame.getTimeFrameName());
    	timeFrameForm.setDescription(timeFrame.getDescription());
    	timeFrameForm.setStartDate(formatDate(timeFrame.getStartDate()));
    	timeFrameForm.setEndDate(formatDate(timeFrame.getEndDate()));
		return new ModelAndView("/base/page/admin/timeframe/edit", "timeframe", timeFrameForm);
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/detail/edit/{id}", method =  RequestMethod.GET)
	public ModelAndView editTask(Model model, @PathVariable("id") String taskId) {
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		model.addAttribute("users", getDevelopers());
		DetailTimeFrame detail = timeFrameRepository.findTaskById(taskId);
		TimeFrameForm task = new TimeFrameForm();
		task.setTaskName(detail.getTaskName());
		task.setDescription(detail.getDescription());
		task.setStartDate(formatDate(detail.getStartDate()));
		task.setEndDate(formatDate(detail.getEndDate()));
		task.setUuid(detail.getUuid());
		task.setUserUuid(detail.getUser().getUuid());
		
		return new ModelAndView("/base/page/admin/timeframe/editdetail", "task", task);
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/updatetask", method = RequestMethod.POST)
	public ModelAndView updateTask(HttpServletRequest request, @Valid TimeFrameForm form) throws ParseException {
		DetailTimeFrame detailTimeFrame = timeFrameRepository.findTaskById(form.getUuid());
		User user = userRepository.findUserById(form.getUserUuid());
		
		detailTimeFrame.setTaskName(form.getTaskName());
		detailTimeFrame.setDescription(form.getDescription());
		detailTimeFrame.setUser(user);
		detailTimeFrame.setStartDate(parseDate(form.getStartDate()));
		detailTimeFrame.setEndDate(parseDate(form.getEndDate()));
		detailTimeFrame.setUpdateBy(getAuthenticationContext().getName());
		detailTimeFrame.setUpdateDate(new Date());
		
		timeFrameRepository.update(detailTimeFrame);
		return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/timeframe/detail"), "timeframeId", detailTimeFrame.getTimeFrame().getUuid());
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/deletetimeframe/{id}", method = RequestMethod.GET)
	public ModelAndView deleteTimeFrame(HttpServletRequest request, @PathVariable("id") String timeFrameId) {
		TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(timeFrameId);
		timeFrame.setStatus(NOT_ACTIVE);
		timeFrame.setUpdateBy(getAuthenticationContext().getName());
		timeFrame.setUpdateDate(new Date());
		timeFrameRepository.update(timeFrame);
		return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/timeframe/list"));
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/deletetask/{id}/{idParent}", method = RequestMethod.GET)
	public ModelAndView deleteTask(HttpServletRequest request, @PathVariable("id") String taskId, @PathVariable("idParent") String timeFrameId) {
		DetailTimeFrame detail = timeFrameRepository.findTaskById(taskId);
		detail.setStatus(NOT_ACTIVE);
		detail.setUpdateBy(getAuthenticationContext().getName());
		detail.setUpdateDate(new Date());
		timeFrameRepository.update(detail);
		return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/timeframe/detail"), "timeframeId", timeFrameId);
	}
	
    @RequestMapping(value = "/base/page/admin/timeframe/updatetimeframe", method = RequestMethod.POST)
    public ModelAndView updateTimeFrame(HttpServletRequest request, @ModelAttribute("timeframe") @Valid TimeFrameForm form) throws ParseException {
    	TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(form.getUuid());    	
    	
    	timeFrame.setTimeFrameName(form.getName());
    	timeFrame.setDescription(form.getDescription());
		timeFrame.setStartDate(parseDate(form.getStartDate()));
		timeFrame.setEndDate(parseDate(form.getEndDate()));		
		timeFrame.setUpdateBy(getAuthenticationContext().getName());
		timeFrame.setUpdateDate(new Date());
    	
    	timeFrameRepository.update(timeFrame);
		return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/timeframe/detail"), "timeframeId", timeFrame.getUuid());
    }
	
	@RequestMapping(value = "/base/page/admin/timeframe/list", method = RequestMethod.GET)
	public ModelAndView listTimeFrame(Model model) {
		ListWrapper<TimeFrame> timeframes = timeFrameRepository.findAll(MAX_ROW, PAGE);
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		model.addAttribute("totalPage", timeframes.getTotalPage());
		model.addAttribute("max", timeframes.getLimit());
		model.addAttribute("page", timeframes.getCurrentPage());
		model.addAttribute("currentDate", formatDate(new Date()));
		return new ModelAndView("/base/page/admin/timeframe/list", "timeframes", timeframes);
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/list/{max}/{page}", method = RequestMethod.GET)
	public ModelAndView listTimeFrame(@PathVariable("max") int max, @PathVariable("page") int page, Model model) {
		ListWrapper<TimeFrame> timeframes = timeFrameRepository.findAll(max, page);
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		model.addAttribute("totalPage", timeframes.getTotalPage());
		model.addAttribute("max", timeframes.getLimit());
		model.addAttribute("page", timeframes.getCurrentPage());
		model.addAttribute("currentDate", formatDate(new Date()));
		return new ModelAndView("/base/page/admin/timeframe/list", "timeframes", timeframes);
	}
	
	@RequestMapping(value = "/base/page/admin/timeframe/bar{id}", method = RequestMethod.GET)
	public ModelAndView showBar(HttpServletRequest request, Model model) {
		TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(request.getParameter("timeframeId"));
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		return new ModelAndView("/base/page/admin/timeframe/bar", "timeframe", timeFrame);
	}
	
	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/base/page/admin/timeframe/data/{id}", method = RequestMethod.GET)
	public @ResponseBody ViewBar callData(@PathVariable("id") String timeFrameId) {		
		TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(timeFrameId);
		List<DetailTimeFrame> taskList = timeFrameRepository.findTask(timeFrame.getUuid());
		ViewBar viewBar = new ViewBar();
		if (timeFrame != null) {
			viewBar.setTitle(timeFrame.getProject().getProjectName());
			viewBar.setStartDate(FormatHelper.formatDate(timeFrame.getStartDate()));
			viewBar.setEndDate(FormatHelper.formatDate(timeFrame.getEndDate()));
			// TODO: use java.util.Calendar instead of java.util.Date
			viewBar.setMinRange(String.valueOf(timeFrame.getStartDate().getDate()));
			
			List<String> tasks = new ArrayList<String>(); 
			List<Series> series = new ArrayList<Series>();
			
			Set<User> developers = new HashSet<User>();
			for (DetailTimeFrame dt : taskList) {
				developers.add(dt.getUser());
				tasks.add("Task-" + String.valueOf(dt.getId()));
			}
			
			for (User user : developers) {				
				Series seri = new Series();				
				List<Range> ranges = new ArrayList<Range>();
				for (DetailTimeFrame detail : taskList) {
					Range range = new Range();	
					// TODO: use java.util.Calendar instead of java.util.Date
					range.setX(user.getUuid().equals(detail.getUser().getUuid()) ? detail.getStartDate().getDate() : 0);
					range.setY(user.getUuid().equals(detail.getUser().getUuid()) ? detail.getEndDate().getDate() : 0);				
					ranges.add(range);													
				}							
				seri.setData(ranges);							
				seri.setName(user.getName());
				series.add(seri);				
			}
			viewBar.setTasks(tasks);	
			viewBar.setSeries(series);
		}
		
		return viewBar; 
	}
		
	private List<Project> getProjects() {
		return timeFrameRepository.findProjects();
	}
	
	private List<User> getDevelopers() {
		return timeFrameRepository.findDevelopers();
	}
}