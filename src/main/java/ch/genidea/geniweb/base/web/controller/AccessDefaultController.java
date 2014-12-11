package ch.genidea.geniweb.base.web.controller;

import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;
import static ch.genidea.geniweb.base.config.ConstApp.MENU_ACTIVE;
import static ch.genidea.geniweb.base.config.ConstApp.USER;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.repository.UserRepository;
import ch.genidea.geniweb.base.web.form.UserForm;

@Controller
public class AccessDefaultController {
	
    @Autowired
    UserRepository userRepository;
    
    private Md5PasswordEncoder encoder = new Md5PasswordEncoder(); 	
    
    @RequestMapping(value = "/base/page/user/manage/{id}", method = RequestMethod.GET)
    public ModelAndView manageUser(Model model, @PathVariable("id") String userId) {    	
    	User user = userRepository.findUserById(userId);
    	return new ModelAndView("/base/page/user/manage", "user", user);
    }
    
    @RequestMapping(value = "/base/page/user/changepassword/{id}", method = RequestMethod.GET)
    public ModelAndView changePassword(Model model, @PathVariable("id") String userId) {
    	User user = userRepository.findUserById(userId);
    	UserForm userForm = new UserForm();
    	userForm.setUsername(user.getUsername());
    	userForm.setUuid(user.getUuid());    	
    	return new ModelAndView("/base/page/user/changepasswd", "user", userForm);
    }
    
    @RequestMapping(value = "/base/page/user/processpasswd", method = RequestMethod.POST)
    public ModelAndView processPassword(@ModelAttribute("user") UserForm form, Model model) {
    	User user = userRepository.findUserById(form.getUuid());
    	user.setPassword(encoder.encodePassword(form.getPassword(), user.getUsername()));
		user.setUpdateBy(getAuthenticationContext().getName());
		user.setUpdateDate(new Date());
		
		userRepository.update(user);
		model.addAttribute("changedPassword", true);
		
		return new ModelAndView("/base/page/user/manage", "user", user);
    }
    
    @RequestMapping(value = "/base/page/user/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(Model model, @PathVariable("id") String userId) {
    	model.addAttribute(MENU_ACTIVE, USER);
    	User user = userRepository.findUserById(userId);
    	model.addAttribute(MENU_ACTIVE, USER);
    	return new ModelAndView("/base/page/user/edit", "user", user);
    }
    
    @RequestMapping(value = "/base/page/user/updateuser", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") UserForm form) {
    	User user = userRepository.findUserById(form.getUuid());
		user.setName(form.getName());
		user.setFullName(form.getFullName());
		user.setEmail(form.getEmail());
		user.setPhoneNumber(form.getPhoneNumber());
		user.setPosition(form.getPosition());
		user.setPassword(encoder.encodePassword(form.getPassword(), user.getUsername()));
		user.setUpdateBy(getAuthenticationContext().getName());
		user.setUpdateDate(new Date());		
		userRepository.update(user);
		
		return new ModelAndView("/base/page/user/manage", "user", user);
    }
}
