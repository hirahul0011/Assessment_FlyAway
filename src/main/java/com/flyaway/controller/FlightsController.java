package com.flyaway.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flyaway.dao.ParametersDAO;
import com.flyaway.entity.Admin;
import com.flyaway.entity.Flights;
import com.flyaway.entity.Passengers;

import jakarta.servlet.http.HttpServletRequest;



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

		@RequestMapping(value="registerPassengerDetails",method=RequestMethod.GET)
		public String registerPassengerDetails(@RequestParam("firstname")String firstname,			
				@RequestParam("ID")Long ID,
				@RequestParam("source")String source,
				@RequestParam("destination")String destination,
				@RequestParam("airline")String airline,
				@RequestParam("price")Double price,
				@RequestParam("noOfPersons")Integer noOfPersons,			
				@RequestParam("travelDate")@DateTimeFormat(pattern="EEEEE MMMMM dd yyyy")Date travelDate,
				ModelMap map) {
			
			String page="register";
			
			map.addAttribute("firstname", firstname);
			map.addAttribute("ID", ID);
			map.addAttribute("source", source);
			map.addAttribute("destination", destination);
			map.addAttribute("airline", airline);
			map.addAttribute("billingAmount", price*noOfPersons);
			map.addAttribute("noOfPersons", noOfPersons);
			map.addAttribute("travelDate", travelDate);
			
			return page;		
			
		}

		// Method I
		//	@RequestMapping(value="bookingDetailsCapture",method=RequestMethod.POST)
		//	public String bookingDetailsCapture(@RequestParam("firstname")String firstname,			
		//			@Validated @NonNull @RequestParam(required = false,name="p1")String p1,
		//			@Validated @NonNull @RequestParam(required = false,name="sex1")String sex1,
		//			@Validated @NonNull @RequestParam(required = false,name="age1")Integer age1,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p2")String p2,
		//			@Validated @NonNull @RequestParam(required = false,name="sex2")String sex2,
		//			@Validated @NonNull @RequestParam(required = false,name="age2")Integer age2,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p3")String p3,
		//			@Validated @NonNull @RequestParam(required = false,name="sex3")String sex3,
		//			@Validated @NonNull @RequestParam(required = false,name="age3")Integer age3,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p4")String p4,
		//			@Validated @NonNull @RequestParam(required = false,name="sex4")String sex4,
		//			@Validated @NonNull @RequestParam(required = false,name="age4")Integer age4,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p5")String p5,
		//			@Validated @NonNull @RequestParam(required = false,name="sex5")String sex5,
		//			@Validated @NonNull @RequestParam(required = false,name="age5")Integer age5,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p6")String p6,
		//			@Validated @NonNull @RequestParam(required = false,name="sex6")String sex6,
		//			@Validated @NonNull @RequestParam(required = false,name="age6")Integer age6,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p7")String p7,
		//			@Validated @NonNull @RequestParam(required = false,name="sex7")String sex7,
		//			@Validated @NonNull @RequestParam(required = false,name="age7")Integer age7,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p8")String p8,
		//			@Validated @NonNull @RequestParam(required = false,name="sex8")String sex8,
		//			@Validated @NonNull @RequestParam(required = false,name="age8")Integer age8,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p9")String p9,
		//			@Validated @NonNull @RequestParam(required = false,name="sex9")String sex9,
		//			@Validated @NonNull @RequestParam(required = false,name="age9")Integer age9,
		//			
		//			@Validated @NonNull @RequestParam(required = false,name="p10")String p10,
		//			@Validated @NonNull @RequestParam(required = false,name="sex10")String sex10,
		//			@Validated @NonNull @RequestParam(required = false,name="age10")Integer age10,
		//			
		//			@RequestParam("ID")Long ID,
		//			@RequestParam("source")String source,
		//			@RequestParam("destination")String destination,
		//			@RequestParam("airline")String airline,			
		//			@RequestParam("noOfPersons")Integer noOfPersons,
		//			@RequestParam("billingAmount")Double billingAmount,
		//			@RequestParam("travelDate")@DateTimeFormat(pattern="EEEEE MMMMM dd yyyy")Date travelDate,
		//			ModelMap map) {
		//				
		////		for(int i=1;i<=noOfPersons;i++) {
		////			String p=(String)request.getParameter("p"+i);
		////		}
		//		
		//		String page="booking";
		//		
		//		map.addAttribute("firstname", firstname);
		//		map.addAttribute("ID", ID);
		//		map.addAttribute("source", source);
		//		map.addAttribute("destination", destination);
		//		map.addAttribute("airline", airline);
		//		map.addAttribute("billingAmount", billingAmount);
		//		map.addAttribute("noOfPersons", noOfPersons);
		//		map.addAttribute("travelDate", travelDate);
		//		
		//		List<Passengers> passengerList=new ArrayList();
		//		
		//		if(p1!=null) {
		//			Passengers passenger1=new Passengers(p1, sex1, age1);
		//			passengerList.add(passenger1);
		////			map.addAttribute("p1", p1);
		////			map.addAttribute("sex1", sex1);
		////			map.addAttribute("age1", age1);
		//		}
		//		
		//		if(p2!=null) {
		//			Passengers passenger2=new Passengers(p2, sex2, age2);
		//			passengerList.add(passenger2);
		////			map.addAttribute("p2", p2);
		////			map.addAttribute("sex2", sex2);
		////			map.addAttribute("age2", age2);
		//		}
		//		
		//		if(p3!=null) {
		//			Passengers passenger3=new Passengers(p3, sex3, age3);
		//			passengerList.add(passenger3);
		////			map.addAttribute("p3", p3);
		////			map.addAttribute("sex3", sex3);
		////			map.addAttribute("age3", age3);
		//		}
		//		
		//		if(p4!=null) {
		//			Passengers passenger4=new Passengers(p4, sex4, age4);
		//			passengerList.add(passenger4);
		////			map.addAttribute("p4", p4);
		////			map.addAttribute("sex4", sex4);
		////			map.addAttribute("age4", age4);
		//		}
		//		
		//		if(p5!=null) {
		//			Passengers passenger5=new Passengers(p5, sex5, age5);
		//			passengerList.add(passenger5);
		////			map.addAttribute("p5", p5);
		////			map.addAttribute("sex5", sex5);
		////			map.addAttribute("age5", age5);
		//		}
		//		
		//		if(p6!=null) {
		//			Passengers passenger6=new Passengers(p6, sex6, age6);
		//			passengerList.add(passenger6);
		////			map.addAttribute("p6", p6);
		////			map.addAttribute("sex6", sex6);
		////			map.addAttribute("age6", age6);
		//		}
		//		
		//		if(p7!=null) {
		//			Passengers passenger7=new Passengers(p7, sex7, age7);
		//			passengerList.add(passenger7);
		////			map.addAttribute("p7", p7);
		////			map.addAttribute("sex7", sex7);
		////			map.addAttribute("age7", age7);
		//		}
		//		
		//		if(p8!=null) {
		//			Passengers passenger8=new Passengers(p8, sex8, age8);
		//			passengerList.add(passenger8);
		////			map.addAttribute("p8", p8);
		////			map.addAttribute("sex8", sex8);
		////			map.addAttribute("age8", age8);
		//		}
		//		
		//		if(p9!=null) {
		//			Passengers passenger9=new Passengers(p9, sex9, age9);
		//			passengerList.add(passenger9);
		////			map.addAttribute("p9", p9);
		////			map.addAttribute("sex9", sex9);
		////			map.addAttribute("age9", age9);
		//		}
		//		
		//		if(p10!=null) {
		//			Passengers passenger10=new Passengers(p10, sex10, age10);
		//			passengerList.add(passenger10);
		////			map.addAttribute("p10", p10);
		////			map.addAttribute("sex10", sex10);
		////			map.addAttribute("age10", age10);
		//		}
		//		
		//		map.addAttribute("passengerList", passengerList);
		//		
		//		return page;		
		//		
		//	}
			
			// Method II with HttpServletRequest
			@RequestMapping(value="bookingDetailsCapture",method=RequestMethod.POST)
			public String bookingDetailsCapture(@RequestParam("firstname")String firstname,
					@RequestParam("ID")Long ID,
					@RequestParam("source")String source,
					@RequestParam("destination")String destination,
					@RequestParam("airline")String airline,			
					@RequestParam("noOfPersons")Integer noOfPersons,
					@RequestParam("billingAmount")Double billingAmount,
					@RequestParam("travelDate")@DateTimeFormat(pattern="EEEEE MMMMM dd yyyy")Date travelDate,
					ModelMap map) {
				
				String page="booking";
				List<Passengers> passengerList=new ArrayList();
				String p;
				String sex;
				int age;
				String errorMessages="";
				boolean isNumber=true;
				
				//VVIMP
				HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				
				for(int i=1;i<=noOfPersons;i++) {
					if(request.getParameter("p"+i)!=null) {
						if(request.getParameter("p"+i)=="") {
							errorMessages=errorMessages+"<br>Please enter the Name of Passenger "+i+"! ";
						}
						
						try {
							int test=Integer.parseInt(request.getParameter("age"+i))/10;
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							isNumber=false;
						}
						
						if(!isNumber) {
							errorMessages=errorMessages+"<br>Age should be a numeric digit for Passenger "+i+"! ";
						}
						
						if(errorMessages!="") {
							continue;
						}else {
							p=request.getParameter("p"+i);
							sex=request.getParameter("sex"+i);
							age=Integer.parseInt(request.getParameter("age"+i));
							Passengers passenger=new Passengers(p, sex, age);
							passengerList.add(passenger);					
						}								
					}
				}
				
				if(errorMessages!="") {
					map.addAttribute("errorMessages", errorMessages);
					page="register";
				}
				
				map.addAttribute("passengerList", passengerList);
				
				map.addAttribute("firstname", firstname);
				map.addAttribute("ID", ID);
				map.addAttribute("source", source);
				map.addAttribute("destination", destination);
				map.addAttribute("airline", airline);
				map.addAttribute("billingAmount", billingAmount);
				map.addAttribute("noOfPersons", noOfPersons);
				map.addAttribute("travelDate", travelDate);			
				
				return page;		
			}

	

}
