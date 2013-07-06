package com.mtg.web.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.ChangePasswordForm;
import com.mtg.web.dto.ImageForm;
import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/account")
public interface AccountController {

	@RequestMapping("/dashboard")
	ModelAndView dashboard(Principal principal);

	@ResponseBody
	@RequestMapping(value = "/upload/profilepic")
	JSON uploadProfilePic(Principal principal, ImageForm form) throws IOException;
	
	@ResponseBody
	@RequestMapping(value = "/editcontact")
	JSON editContact(Principal principal, String contact);

	@ResponseBody
	@RequestMapping(value = "/verify/resend", method = RequestMethod.POST)
	JSON resendVerification(Principal principal);
	
	@ResponseBody
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	JSON changePassword(Principal principal, ChangePasswordForm form, BindingResult binding);
	
	/**
	 * If activation is successful, display success message, else error msg
	 * If user is authenticated, display just the msg. otherwise, display the login page
	 */
	@RequestMapping("/activate/{activationCode}")
	ModelAndView activate(Principal principal, String activationCode);

}
