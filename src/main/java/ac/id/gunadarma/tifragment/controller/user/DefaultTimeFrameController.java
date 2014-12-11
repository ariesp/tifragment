package ac.id.gunadarma.tifragment.controller.user;

import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;
import static ac.id.gunadarma.tifragment.FormatHelper.formatDate;
import static ch.genidea.geniweb.base.config.ConstApp.MAX_ROW;
import static ch.genidea.geniweb.base.config.ConstApp.MENU_ACTIVE;
import static ch.genidea.geniweb.base.config.ConstApp.PAGE;
import static ch.genidea.geniweb.base.config.ConstApp.TIME_FRAME;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ac.id.gunadarma.tifragment.FormatHelper;
import ac.id.gunadarma.tifragment.form.ViewBar;
import ac.id.gunadarma.tifragment.form.ViewBar.Series;
import ac.id.gunadarma.tifragment.form.ViewBar.Series.Range;
import ac.id.gunadarma.tifragment.model.DetailTimeFrame;
import ac.id.gunadarma.tifragment.model.TimeFrame;
import ac.id.gunadarma.tifragment.repository.TimeFrameRepository;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.repository.UserRepository;
import ch.genidea.geniweb.base.utility.ListWrapper;

@Controller
public class DefaultTimeFrameController {

	@Autowired
	private TimeFrameRepository timeFrameRepository;
	
	@Autowired
	private UserRepository userRepository;
		    
    @RequestMapping(value = "/base/page/user/detail{id}", method = RequestMethod.GET)
    public ModelAndView view(Model model, HttpServletRequest request) {
    	TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(request.getParameter("timeframeId"));
    	User user = userRepository.findUserByUsername(getAuthenticationContext().getName());			
    	List<DetailTimeFrame> tasks = timeFrameRepository.findTaskDefault(user.getUuid(), request.getParameter("timeframeId"));
    	model.addAttribute("tasks", tasks);
    	model.addAttribute(MENU_ACTIVE, TIME_FRAME);
        return new ModelAndView("base/page/user/detail", "timeframe", timeFrame);
    }
    
    @RequestMapping(value = "/base/page/user/tasklist", method = RequestMethod.GET)
    public ModelAndView viewAllTask(Model model, HttpServletRequest request) {    	
    	User user = userRepository.findUserByUsername(getAuthenticationContext().getName());			
    	List<DetailTimeFrame> tasks = timeFrameRepository.findAllTaskDefault(user.getUuid());
    	model.addAttribute("tasks", tasks);
    	model.addAttribute(MENU_ACTIVE, "home");
        return new ModelAndView("base/page/user/tasklist", "tasks", tasks);
    }
	
	@RequestMapping(value = "/base/page/user/list", method = RequestMethod.GET)
	public ModelAndView listTimeFrame(Model model) {
		ListWrapper<TimeFrame> timeframes = timeFrameRepository.findAll(MAX_ROW, PAGE);
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		model.addAttribute("totalPage", timeframes.getTotalPage());
		model.addAttribute("max", timeframes.getLimit());
		model.addAttribute("page", timeframes.getCurrentPage());
		model.addAttribute("currentDate", formatDate(new Date()));
		return new ModelAndView("/base/page/user/list", "timeframes", timeframes);
	}
	
	@RequestMapping(value = "/base/page/user/list/{max}/{page}", method = RequestMethod.GET)
	public ModelAndView listTimeFrame(@PathVariable("max") int max, @PathVariable("page") int page, Model model) {
		ListWrapper<TimeFrame> timeframes = timeFrameRepository.findAll(max, page);
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		model.addAttribute("totalPage", timeframes.getTotalPage());
		model.addAttribute("max", timeframes.getLimit());
		model.addAttribute("page", timeframes.getCurrentPage());
		model.addAttribute("currentDate", formatDate(new Date()));
		return new ModelAndView("/base/page/user/list", "timeframes", timeframes);
	}
	
	@RequestMapping(value = "/base/page/user/bar{id}", method = RequestMethod.GET)
	public ModelAndView showBar(HttpServletRequest request, Model model) {
		TimeFrame timeFrame = timeFrameRepository.findTimeFrameById(request.getParameter("timeframeId"));
		model.addAttribute(MENU_ACTIVE, TIME_FRAME);
		return new ModelAndView("/base/page/user/bar", "timeframe", timeFrame);
	}
	
	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/base/page/user/data/{id}", method = RequestMethod.GET)
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
}