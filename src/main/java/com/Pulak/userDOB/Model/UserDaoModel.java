package com.Pulak.userDOB.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class UserDaoModel {

	@Id
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "date_of_birth")
	private Date dateofBirth;
	

	public UserDaoModel() {
		
	}
	
	public UserDaoModel(String username, Date dateofBirth) {
		super();
		this.username = username;
		this.dateofBirth = dateofBirth;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dob) {
		this.dateofBirth = dob;
	}

}
