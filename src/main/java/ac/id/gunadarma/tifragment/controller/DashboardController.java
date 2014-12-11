package ac.id.gunadarma.tifragment.controller;

import static ch.genidea.geniweb.base.config.ConstApp.MENU_ACTIVE;
import static ch.genidea.geniweb.base.config.ConstApp.DASHBOARD;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
	
	@RequestMapping(value = "/base/page/admin/dashboard/show", method = RequestMethod.GET)
	public ModelAndView show(Model model) {
		model.addAttribute(MENU_ACTIVE, DASHBOARD);
		return new ModelAndView("base/page/admin/dashboard/chart");
	}
	
	@RequestMapping(value = "/base/page/user/dashboard/show", method = RequestMethod.GET)
	public ModelAndView showDefault(Model model) {
		model.addAttribute(MENU_ACTIVE, DASHBOARD);
		return new ModelAndView("base/page/user/chart");
	}
}
