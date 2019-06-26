package com.Pulak.userDOB.Model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class userDOBModelRequest {
	
	@NotNull
	@Past
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	Date dateOfBirth;

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
