package com.appsdeveloperblog.photoapp.api.users.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6193622452631861395L;
	private String userId;
	private String encryptedPassword;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
}
