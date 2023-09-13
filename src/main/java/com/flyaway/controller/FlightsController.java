package com.flyaway.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flyaway.dao.ParametersDAO;
import com.flyaway.entity.Admin;
import com.flyaway.entity.Flights;



@Controller
public class FlightsController {

	@Autowired
	private ParametersDAO parametersDAO;

	//	@RequestMapping(value="/",method=RequestMethod.GET)
	//    public String listProducts(ModelMap map)
	//    {
	////        map.addAttribute("eproduct", new EProductEntity());
	//        map.addAttribute("places", placesDAO.getAllProducts());
	//        return "index1";
	//    }
		
		@RequestMapping(value="validationCheck",method=RequestMethod.POST)
		public String isValidUser(@RequestParam("username")String username,
				@RequestParam("password")String password, ModelMap map) {
			String page="login";
			if(username!="") {			
				try {
					if(!username.equalsIgnoreCase("Admin")) {
						List<Admin> user=(List<Admin>)parametersDAO.getTheUserDetails(username);
						if(username.equalsIgnoreCase(user.get(0).getFirstName()) && password.equals(user.get(0).getPassword())) {
							map.addAttribute("places", parametersDAO.getAllPlaces());
							map.addAttribute("airlines", parametersDAO.getAllAirlines());
							map.addAttribute("firstname", username);
							page="searchPage";
						}else {
							map.addAttribute("errorMessage", "Invalid login credentials");
						}				
					}else {
						List<Admin> user=(List<Admin>)parametersDAO.getTheUserDetails(username);
						List<Admin> users=(List<Admin>)parametersDAO.getAllUsersDetails();
						if(username.equalsIgnoreCase(user.get(0).getFirstName()) && password.equals(user.get(0).getPassword())) {				
							map.addAttribute("firstname", username);
							map.addAttribute("users", users);
							page="administration";
						}else {
							map.addAttribute("errorMessage", "Invalid login credentials");
						}
					}				
				}catch(IndexOutOfBoundsException e) {
					map.addAttribute("errorMessage", "Please enter correct UserName");
					page="login";
				}									
			}else {
				map.addAttribute("errorMessage", "Please enter the UserName");
			}		
			return page;		
		}

		@RequestMapping(value="searchFormValidation",method=RequestMethod.POST)
			public String isFormValidated(@RequestParam("firstname")String firstname,			
					@RequestParam(required=false, name="travelDate")@DateTimeFormat(pattern="yyyy-MM-dd")Date travelDate,
					@RequestParam("source")String source,
					@RequestParam("destination")String destination,
					@RequestParam("noOfPersons")String noOfPersons,
					@RequestParam("airline")String airline, ModelMap map) {
				
		//		String firstname=(String)map.getAttribute("firstname");
				String page="searchPage";
				
				String errorMessages="";
				
				boolean isNumber=true;
				
				try {
					int test=Integer.parseInt(noOfPersons)/10;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					isNumber=false;
				}	
				
				if(travelDate!=null) {
					if((int)(travelDate.getTime()/1000) <(int)(new Date().getTime()/1000)) {
						errorMessages=errorMessages+"<br>Entered date can not be a date from past! ";
					}			
				}else {
					errorMessages=errorMessages+"<br>Please enter the Travel Date! ";
				}
				
				if(source!="" && destination!="") {
					if(source.equals(destination)) {
						errorMessages=errorMessages+"<br>Source and Destination can not be same! ";
					}			
				}else {
					errorMessages=errorMessages+"<br>Please select Source and Destination! ";
				}
						
				if(!isNumber) {
					errorMessages=errorMessages+"<br>No of Persons should be one numeric digit! ";
				}else {
					if(Integer.parseInt(noOfPersons)>50) {
						errorMessages=errorMessages+"<br>You can not book more than 50 seats in one transaction! ";
					}
				}		
				
				List<Flights> listFlightA=parametersDAO.getTheFlights(source, destination, airline);
				List<Flights> listFlight=parametersDAO.getTheFlightsExAirline(source, destination);
				SimpleDateFormat sdf=new SimpleDateFormat("EEEEE MMMMM dd yyyy");
				
				if(errorMessages=="") {
					map.addAttribute("travelDate", sdf.format(travelDate));
		//			map.addAttribute("source", source);  //Not in use in result.jsp
		//			map.addAttribute("destination", destination);	//Not in use in result.jsp
					map.addAttribute("noOfPersons", noOfPersons);
					map.addAttribute("firstname", firstname);
		//			map.addAttribute("airline", airline);	//Not in use in result.jsp
					if(airline!="") {
						map.addAttribute("flights", listFlightA);
					}
					else {
						map.addAttribute("flights", listFlight);
					}
					page="result";
				}else {
					map.addAttribute("places", parametersDAO.getAllPlaces());
					map.addAttribute("airlines", parametersDAO.getAllAirlines());
					map.addAttribute("firstname", firstname);
					
					map.addAttribute("errorMessage", errorMessages);
				}
				return page;		
			}

	

}
