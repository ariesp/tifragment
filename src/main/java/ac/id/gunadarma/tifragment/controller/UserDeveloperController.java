package ac.id.gunadarma.tifragment.controller;

import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.repository.UserRepository;

@Controller
@RequestMapping
public class UserDeveloperController {
	private static final String MENU_ACTIVE = "menuActive";
	private static final String HOME = "home";
	
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping(value = "/base/page/user")
    public String home(Model model) {    	
    	model.addAttribute(MENU_ACTIVE, HOME);
        return "/base/page/user";
    }
    
    @RequestMapping(value = "/base/page/user/account", method = RequestMethod.GET)
    public ModelAndView manageAccount(HttpServletRequest request) {
    	User user = userRepository.findUserByUsername(getAuthenticationContext().getName()); 
    	return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/user/manage/" + user.getUuid()));
    }
}
