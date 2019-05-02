package org.openedusoft.account.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openedusoft.account.vo.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/api")
@RestController
@Api(value="Account Management", description="Operations pertaining to accounts")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value = "/accounts/{accountId}", produces = {"application/JSON"})
    public Account getAccountDetail(@PathVariable(value = "accountId") long accountId) {
    	logger.debug("INSIDE getSchoolDetail");
    	logger.debug("productId:"+accountId);
        return new Account();
    }

    @PostMapping(value = "/accounts")
    public void createAccount(@RequestBody Account account){
    	logger.debug("inside createAccount" );
    	
    	//create entry in auth for the account user
    	//create organization 
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			logger.debug("school:"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }

    @PutMapping(value = "/accounts/{accountId}/billing", consumes = {"application/JSON"})
    public void updateBillingInfo(@PathVariable(value = "accountId") long accountId, @RequestBody Account account) {
    	logger.debug("inside updateSchoolBillingInfo" );
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			logger.debug("accountId:"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountId));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
    
    
    @PutMapping(value = "/accounts/{accountId}/contact", consumes = {"application/JSON"})
    public void updateContactInfo(@PathVariable(value = "accountId") long accountId, @RequestBody Account account) {
    	logger.debug("inside updateSchoolBillingInfo" );
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			logger.debug("product:"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountId));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
    
    
    @DeleteMapping(value = "/accounts/{accountId}")
    public String removeAccount(@PathVariable(value = "accountId") long accountId) {
    	logger.debug("inside removeAccount:"+accountId );

    	//remove all child accounts
    	//remove all schools under the account
    	
    	List list = new ArrayList();
    	list.add(1001);
    	list.add(1002);
    	list.add(1003);
    	logger.debug("calling--->http://schools/api/schools/resources/delete" );
    	logger.debug("restTemplate:"+restTemplate);
    	try {
    		restTemplate.postForObject("http://schools:9002/api/schools/resources/delete",null,String.class);//, String.class);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		logger.error("Error------>",ex);
    		return "Error";
    	}
    	return "success";
    	//remove all users under the account
    	//String response = 
    }
    
    @DeleteMapping(value = "/accounts/{accountId}/purge")
    public void purgeAccount(@PathVariable(value = "accountId") long schoolId) {
        //purge all students as well
    }
    
}