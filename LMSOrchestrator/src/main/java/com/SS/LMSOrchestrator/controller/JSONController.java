package com.SS.LMSOrchestrator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class JSONController {
	@RequestMapping(path = "/admin/**", method= {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<Object> adminRequest(HttpServletRequest request, @RequestBody Object body, @RequestHeader HttpHeaders header){
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpMethod meth = null;
		if(request.getMethod().equalsIgnoreCase("PUT"))
			meth = HttpMethod.PUT;
		if(request.getMethod().equalsIgnoreCase("POST"))
			meth = HttpMethod.POST;
		RestTemplate restTemplate =new RestTemplate();
		String newPath = "http://localhost:8080/LMSAdmin" +request.getRequestURI().substring(8);
		
		return restTemplate.exchange(newPath, meth, new HttpEntity<Object>(body,header),Object.class);
	}
	@RequestMapping(path = "/admin/**", method= {RequestMethod.GET, RequestMethod.DELETE})
	public ResponseEntity<Object> adminRequest(HttpServletRequest request,@RequestHeader HttpHeaders header){
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpMethod meth = null;
		if(request.getMethod().equalsIgnoreCase("GET"))
			meth = HttpMethod.GET;
		if(request.getMethod().equalsIgnoreCase("DELETE"))
			meth = HttpMethod.DELETE;
		RestTemplate restTemplate =new RestTemplate();
		String newPath = "http://localhost:8080/LMSAdmin" +request.getRequestURI().substring(8);
		return restTemplate.exchange(newPath, meth, new HttpEntity<Object>(header),Object.class);
	}
	
	@RequestMapping(path = "/borrower/**", method= {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<Object> borrowerRequest(HttpServletRequest request, @RequestBody Object body, @RequestHeader HttpHeaders header){
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpMethod meth = null;
		if(request.getMethod().equalsIgnoreCase("PUT"))
			meth = HttpMethod.PUT;
		if(request.getMethod().equalsIgnoreCase("POST"))
			meth = HttpMethod.POST;
		RestTemplate restTemplate =new RestTemplate();
		String newPath = "http://localhost:8081/" +request.getRequestURI().substring(8);
		
		return restTemplate.exchange(newPath, meth, new HttpEntity<Object>(body,header),Object.class);
	}
	@RequestMapping(path = "/borrower/**", method= {RequestMethod.GET, RequestMethod.DELETE})
	public ResponseEntity<Object> borrowerRequest(HttpServletRequest request, @RequestHeader HttpHeaders header){
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpMethod meth = null;
		if(request.getMethod().equalsIgnoreCase("GET"))
			meth = HttpMethod.GET;
		if(request.getMethod().equalsIgnoreCase("DELETE"))
			meth = HttpMethod.DELETE;
		
		RestTemplate restTemplate =new RestTemplate();
		String newPath = "http://localhost:8081/" +request.getRequestURI().substring(8);
		
		return restTemplate.exchange(newPath, meth, new HttpEntity<Object>(header),Object.class);
	}
	
	@RequestMapping(path = "/librarian/**", method= {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<Object> librarianRequest(HttpServletRequest request, @RequestBody Object body,@RequestHeader HttpHeaders header){
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpMethod meth = null;
		if(request.getMethod().equalsIgnoreCase("PUT"))
			meth = HttpMethod.PUT;
		if(request.getMethod().equalsIgnoreCase("POST"))
			meth = HttpMethod.POST;
		RestTemplate restTemplate =new RestTemplate();
		String newPath = "http://localhost:8075/" +request.getRequestURI().substring(8);
		
		return restTemplate.exchange(newPath, meth, new HttpEntity<Object>(body,header),Object.class);
	}
	@RequestMapping(path = "/librarian/**", method= {RequestMethod.GET, RequestMethod.DELETE})
	public ResponseEntity<Object> librarianRequest(HttpServletRequest request, @RequestHeader HttpHeaders header){
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpMethod meth = null;
		if(request.getMethod().equalsIgnoreCase("GET"))
			meth = HttpMethod.GET;
		if(request.getMethod().equalsIgnoreCase("DELETE"))
			meth = HttpMethod.DELETE;
		RestTemplate restTemplate =new RestTemplate();
		String newPath = "http://localhost:8075/" +request.getRequestURI().substring(8);
		
		return restTemplate.exchange(newPath, meth, new HttpEntity<Object>(header),Object.class);
	}

}
