/**
 * 
 */
package com.appsdeveloperblog.photoapp.api.users.ui.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class CreateUserRequestModel {

	@NotNull(message="First name cannot be null")
	@Size(min=2, message="First name must not be less than 2 characters")
	private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min=2, message="Last name must not be less than 2 characters")
	private String lastName;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message="Password must be greater than equal to 8 characters and less than equal to 16 characters")
	private String password;
	
	@NotNull(message="Email cannot be null")
	@Email
	private String email;
	
}
