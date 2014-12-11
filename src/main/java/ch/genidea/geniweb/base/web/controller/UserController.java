package ch.genidea.geniweb.base.web.controller;

import static ac.id.gunadarma.tifragment.session.SessionAuthentication.getAuthenticationContext;
import static ch.genidea.geniweb.base.config.ConstApp.MAX_ROW;
import static ch.genidea.geniweb.base.config.ConstApp.MENU_ACTIVE;
import static ch.genidea.geniweb.base.config.ConstApp.PAGE;
import static ch.genidea.geniweb.base.config.ConstApp.USER;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ch.genidea.geniweb.base.component.UserSessionComponent;
import ch.genidea.geniweb.base.config.ConstApp;
import ch.genidea.geniweb.base.domain.Role;
import ch.genidea.geniweb.base.domain.SecurityCode;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.repository.RoleRepository;
import ch.genidea.geniweb.base.repository.SecurityCodeRepository;
import ch.genidea.geniweb.base.repository.UserRepository;
import ch.genidea.geniweb.base.service.MailSenderService;
import ch.genidea.geniweb.base.service.MyUserDetailsService;
import ch.genidea.geniweb.base.utility.ListWrapper;
import ch.genidea.geniweb.base.utility.SecureUtility;
import ch.genidea.geniweb.base.utility.TypeActivationEnum;
import ch.genidea.geniweb.base.web.form.UserForm;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SecurityCodeRepository securityCodeRepository;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    UserDetailsService myUserDetailsService;
    
    @Autowired
    UserSessionComponent session;
    
    private Md5PasswordEncoder encoder = new Md5PasswordEncoder(); 	    
    
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @RequestMapping(value = "/base/page/admin/user/create", method = RequestMethod.GET)
    public String create(Model model) {
    	model.addAttribute(MENU_ACTIVE, USER);
        model.addAttribute("user", new UserForm());
        return "base/page/admin/user/create";
    }

	@RequestMapping(value = "/base/page/admin/user/createuser", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") @Valid UserForm form, BindingResult result) {
		if (!result.hasErrors()) {
			
			if (userRepository.isUserAlreadyExists(form.getUsername())) {
				FieldError fieldError = new FieldError("username", form.getUsername(), "User is already existed");
				result.addError(fieldError);
				return "/base/page/admin/user/create";
			}
			if (!form.getPassword().equals(form.getConfirmPassword())) {
				FieldError fieldError = new FieldError("password", form.getPassword(), "Password field should be match with Confirm Password field");
				result.addError(fieldError);
				return "/base/page/admin/user/create";
			}
			User user = new User();
			user.setEnabled(true);
			user.setAccountExpired(false);
			user.setAccountLocked(false);
			user.setCreatedBy(getAuthenticationContext().getName());
			user.setCreatedDate(new Date());
			user.setUsername(form.getUsername());
			user.setName(form.getName());
			user.setFullName(form.getFullName());
			user.setEmail(form.getEmail());
			user.setPhoneNumber(form.getPhoneNumber());
			user.setPosition(form.getPosition());
			user.setPassword(encoder.encodePassword(form.getPassword(), user.getUsername()));

			Role role = new Role();
			role.setUser(user);	
			if (form.getPosition().equals("developer")) {
				role.setRole(ConstApp.ROLE_USER);				
			} else {
				role.setRole(ConstApp.ROLE_ADMIN);
			}

			SecurityCode securityCode = new SecurityCode();
			securityCode.setUser(user);
			securityCode.setTimeRequest(new Date());
			securityCode.setTypeActivationEnum(TypeActivationEnum.NEW_ACCOUNT);
			securityCode.setCode(SecureUtility.generateRandomCode());
			user.setRole(role);
			user.setSecurityCode(securityCode);

			roleRepository.save(role);
			securityCodeRepository.save(securityCode);
			userRepository.save(user);
		} else {
			return "/base/page/admin/user/create";
		}
		return "redirect:/base/page/admin/user/list";
    }
    
    @RequestMapping(value = "/base/page/admin/user/updateuser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") UserForm form) {
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
		
		return "redirect:/base/page/admin/user/list";
    }
    
    @RequestMapping(value = "/base/page/admin/user/deleteuser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request, @PathVariable("id") String userId) {
    	User user = userRepository.findUserById(userId);
    	user.setEnabled(false);
		user.setUpdateBy("admin");
		user.setUpdateDate(new Date());		
		userRepository.update(user);	
		return new ModelAndView(new RedirectView(request.getContextPath() + "/base/page/admin/user/list"));		
    }
    
    @RequestMapping(value = "/base/page/admin/user/changepassword/{id}", method = RequestMethod.GET)
    public ModelAndView changePassword(Model model, @PathVariable("id") String userId) {
    	User user = userRepository.findUserById(userId);
    	UserForm userForm = new UserForm();
    	userForm.setUsername(user.getUsername());
    	userForm.setUuid(user.getUuid());
    	model.addAttribute(MENU_ACTIVE, USER);
    	return new ModelAndView("/base/page/admin/user/changepasswd", "user", userForm);
    }
    
    @RequestMapping(value = "/base/page/admin/user/processpasswd", method = RequestMethod.POST)
    public ModelAndView processPassword(@ModelAttribute("user") UserForm form, Model model) {
    	User user = userRepository.findUserById(form.getUuid());
    	user.setPassword(encoder.encodePassword(form.getPassword(), user.getUsername()));
		user.setUpdateBy("admin");
		user.setUpdateDate(new Date());
		
		userRepository.update(user);
		model.addAttribute("changedPassword", true);
		return new ModelAndView("/base/page/admin/user/detail", "user", user);
    }
    
    @RequestMapping(value = "/base/page/admin/user/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(Model model, @PathVariable("id") String userId) {
    	model.addAttribute(MENU_ACTIVE, USER);
    	User user = userRepository.findUserById(userId);
    	return new ModelAndView("/base/page/admin/user/edit", "user", user);
    }
    
    @RequestMapping(value = "/base/page/admin/user/detail/{id}", method = RequestMethod.GET)
    public ModelAndView view(Model model, @PathVariable("id") String userId) {
    	model.addAttribute(MENU_ACTIVE, USER);
    	User user = userRepository.findUserById(userId);
    	return new ModelAndView("/base/page/admin/user/detail", "user", user);
    }
    
    @RequestMapping(value = "/base/page/admin/user/manage/{id}", method = RequestMethod.GET)
    public ModelAndView manage(Model model, @PathVariable("id") String userId) {
    	model.addAttribute(MENU_ACTIVE, USER);
    	User user = userRepository.findUserById(userId);
    	return new ModelAndView("/base/page/admin/user/manage", "user", user);
    }
    
    @RequestMapping(value = "/base/page/admin/user/list", method =  RequestMethod.GET)
    public ModelAndView listUser(Model model) {
    	ListWrapper<User> users = getAuthenticationContext().getName().equals("deodorant") ? 
    			userRepository.findAll(MAX_ROW, PAGE) : userRepository.findAllDeveloper(MAX_ROW, PAGE);
    	model.addAttribute(MENU_ACTIVE, USER);
    	model.addAttribute("totalPage", users.getTotalPage());
    	model.addAttribute("max", users.getLimit());
    	model.addAttribute("page", users.getCurrentPage());    	
    	return new ModelAndView("/base/page/admin/user/list","users", users);
    }
    
    @RequestMapping(value = "/base/page/admin/user/list/{max}/{page}", method =  RequestMethod.GET)
    public ModelAndView listUserPage(@PathVariable("max") int max, @PathVariable("page") int page, Model model) {
    	ListWrapper<User> users = getAuthenticationContext().getName().equals("deodorant") ? 
    			userRepository.findAll(max, page) : userRepository.findAllDeveloper(max, page);
    	model.addAttribute(MENU_ACTIVE, USER);
    	model.addAttribute("totalPage", users.getTotalPage());
    	model.addAttribute("max", users.getLimit());
    	model.addAttribute("page", users.getCurrentPage());
    	return new ModelAndView("/base/page/admin/user/list","users", users);
    }
}