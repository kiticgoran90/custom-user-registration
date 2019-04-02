package com.gorankitic.userregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gorankitic.userregistration.entity.User;
import com.gorankitic.userregistration.service.UserService;
import com.gorankitic.userregistration.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForm")
	public String showLoginPage(Model model) {
		model.addAttribute("crmUser", new CrmUser());
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser, BindingResult bindingResult, Model model) {
		String userName = crmUser.getUserName();
		
		if(bindingResult.hasErrors()) {
			return "registration-form";
		}
		
		User existing = userService.findByUserName(userName);
		if(existing != null) {
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User name already exists.");
			return "registration-form";
		}
		userService.save(crmUser);
		return "registration-confirmation";
	}
}
