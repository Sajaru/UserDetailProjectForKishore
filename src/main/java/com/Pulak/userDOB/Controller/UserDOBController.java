package com.Pulak.userDOB.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pulak.userDOB.Dao.UserDao;
import com.Pulak.userDOB.Model.UserDaoModel;
import com.Pulak.userDOB.Model.UserDOBModelRequest;

@RestController
@RequestMapping("/hello")
public class UserDOBController {

	@Autowired
	UserDao userDao;
	

	@PutMapping(path = "/{username}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createOrUpdateUser(@PathVariable  String username,
			@Valid @RequestBody UserDOBModelRequest dob) {
		
		if(!username.matches("^[a-zA-Z]*$")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		Date dateOfBirth = dob.getDateOfBirth();
		UserDaoModel userDaoModel=new UserDaoModel();
		userDaoModel.setUsername(username);
		userDaoModel.setDateofBirth(dateOfBirth);
		userDao.save(userDaoModel);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}

	@GetMapping(path = "/{username}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> sayHelloToUser(@PathVariable String username) throws ParseException {
		
		String greeting;
		if(!username.matches("^[a-zA-Z]*$")) {
			greeting="username contains characters other than letter";
			return new ResponseEntity<>(greeting,HttpStatus.BAD_REQUEST); 
		}
		
		UserDaoModel requestedUser=userDao.findById(username).isPresent()?userDao.findById(username).get():null;
		if (null==requestedUser) {
			greeting="Requested username not found";
			return new ResponseEntity<>(greeting,HttpStatus.NOT_FOUND);
		}
		int daystoBday=daystoBirthday(requestedUser.getDateofBirth());
		if (daystoBday==0) {
			greeting="Hello "+username+" .Happy Birthday :)";
			return new ResponseEntity<>(greeting,HttpStatus.OK);
		}
		else
			greeting="Hello "+username+" .Your Birthday is in "+daystoBday + " days";
		
		return new ResponseEntity<>(greeting,HttpStatus.OK);
	}
	
	public int daystoBirthday(Date dateOfBirth) throws ParseException {
		
		System.out.println("dateOfBirth :"+ dateOfBirth);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String birthdayStr = dateFormat.format(dateOfBirth);
		System.out.println("birthdayStr" + birthdayStr);
		String BirthMonth=birthdayStr.split(" ")[0].split("-")[1];
		System.out.println("BirthMonth" + BirthMonth);
		String BirthDay=birthdayStr.split(" ")[0].split("-")[2];
		System.out.println("BirthDay" + BirthDay);
		Date today = Calendar.getInstance().getTime();
		System.out.println("today :"+ today);
		String todayDateStr = dateFormat.format(today);		
		String currentyear=todayDateStr.split("-")[0];
		
		String thisYearBirthdayStr=currentyear+"-"+BirthMonth+"-"+BirthDay;
		System.out.println("thisYearBirthdayStr" + thisYearBirthdayStr);
		Date thisYearBirthdayDate=new SimpleDateFormat("yyyy-MM-dd").parse(thisYearBirthdayStr); 
		Date presentDate = new SimpleDateFormat("yyyy-MM-dd").parse(todayDateStr);
		long timeToThisYearBirthDay = thisYearBirthdayDate.getTime() - presentDate.getTime();
		if (timeToThisYearBirthDay>=0) {
			 int daysToThisYearBirthDay = (int) (timeToThisYearBirthDay / (1000*60*60*24));
			 System.out.println("daysToThisYearBirthDay" + daysToThisYearBirthDay);
			 return daysToThisYearBirthDay;
		}
	     String nextYear=Integer.toString(Integer.parseInt(currentyear)+1);
	     String nextYearBirthdayDayStr=nextYear+"-"+BirthMonth+"-"+BirthDay;
	     System.out.println("nextYearBirthdayDayStr" + nextYearBirthdayDayStr);
	     Date nextYearBirthdayDay=new SimpleDateFormat("yyyy-MM-dd").parse(nextYearBirthdayDayStr);
	     long timeToNextYearBirthDay = nextYearBirthdayDay.getTime() - presentDate.getTime();
	     int daysToNextYearBirthDay = (int) (timeToNextYearBirthDay / (1000*60*60*24));
		 System.out.println("daysToNextYearBirthDay" + daysToNextYearBirthDay);
	     return daysToNextYearBirthDay;
	}

}
