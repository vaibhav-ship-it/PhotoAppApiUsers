/**
 * 
 */
package com.appsdeveloperblog.photoapp.api.users.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -2731425678149216053L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, unique=true)
	private String userId;
	
	@Column(nullable=false, unique=true)
	private String encryptedPassword;
	
	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=120, unique=true)
	private String email;
	
	

}
