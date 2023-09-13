package com.flyaway.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flyaway.dao.ParametersDAO;
import com.flyaway.entity.Admin;



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

	

}
