package ch.genidea.geniweb.base.web.controller;

import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;
import static ac.id.gunadarma.tifragment.session.SessionAuthentication.hasRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.genidea.geniweb.base.component.UserSessionComponent;
import ch.genidea.geniweb.base.repository.UserRepository;

@Controller
@RequestMapping
public class AccessController {

    @Autowired
    private UserSessionComponent userSessionComponent;
    @Autowired
    private UserRepository userRepository;
    
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String login(ModelMap model, SecurityContextHolderAwareRequestWrapper request) {
		if (getAuthenticationContext().isAuthenticated()) {
			if (hasRole("ROLE_ADMIN")) {
				return "redirect:/base/page/admin";	   	    		
			} 
			if (hasRole("ROLE_USER")) {
				return "redirect:/base/page/user";
			}						
		}
		return "redirect:/login";		
	}

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @RequestParam(required=false) String message){
        model.addAttribute("message", message);
        return "base/access/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model) {
    	model.addAttribute("logoutSucces", true);
    	return "/base/access/login";
    }

    @RequestMapping("/loginfailed")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/base/access/login";
    }
    
    @RequestMapping(value = "/loginsuccess", method = RequestMethod.GET)
    public String loginSuccess(SecurityContextHolderAwareRequestWrapper request) {
    	if (hasRole("ROLE_ADMIN")) {
    		return "redirect:/base/page/admin";	   	    		
    	} 
    	if (hasRole("ROLE_USER")) {
    		return "redirect:/base/page/user";
    	}
    	return "redirect:/login";
    }
}