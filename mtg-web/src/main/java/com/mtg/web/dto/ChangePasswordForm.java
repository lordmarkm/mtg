package com.mtg.web.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

public class ChangePasswordForm {

	@NotEmpty(message = "Password can't be empty")
	String password;
	
	@NotEmpty(message = "Password can't be empty")
	@Length(min=6, max=30, message="Password must be between 6 and 30 characters long")
	String newpassword;
	
	@NotEmpty(message = "Password can't be empty")
	String confirmpassword;

	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("pw", password)
			.append("new", newpassword)
			.append("confirm", confirmpassword)
			.toString();
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
}
