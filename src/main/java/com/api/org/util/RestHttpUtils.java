package com.api.org.util;


import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


@Slf4j
@Service
public class RestHttpUtils {
	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	public Object httpGetRestData(String url,String serviceName,String uid)
	{
		Object result = null;
		try{
	    	RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForObject(url, Object.class);
			
		}catch(Exception e){
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
		}
	    return result ;
	}
	
	public List<Object> httpGetRestDataList(String url,String serviceName,String uid)
	{
		List<Object> result = null;
		try{
			
			RestTemplate restTemplate = new RestTemplate();
			result = Arrays.asList(restTemplate.getForObject(url, Object[].class));
			
		}catch(Exception e){
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
		}
	    return result ;
	}
	public ResponseEntity<String> httpGetRestData(String url,MultiValueMap<String, String> headers,String serviceName,String uid)
	{
		
		ResponseEntity<String> responseEntity  =null;		
		//Instant start = Instant.now();
		String body ="";
		try{	
			ObjectMapper mapper = new ObjectMapper();
			
			CommonFunctions.log(serviceName,uid+" Url :"+url,logger);
			CommonFunctions.log(serviceName,uid+" Header :"+mapper.writeValueAsString(headers),logger);
			
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, headers);
			RestTemplate restTemplate = new RestTemplate();
			
				responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
				if(responseEntity.getBody()!=null)
				{
				     body = responseEntity.getBody();
				     body=body.trim();
				}
				CommonFunctions.log(serviceName,uid+" Response :"+responseEntity.getStatusCodeValue()+" : "+body,logger);
			
			//return token;			
		}
		catch (Exception e){
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
			responseEntity  =null;
		}
		
		return responseEntity;
	}
	
	public ResponseEntity<String> httpGetRestData(String url, Object obj,MultiValueMap<String, String> headers,String serviceName,String uid)
	{
		
		ResponseEntity<String> responseEntity  =null;
		String body ="";
		try{	
			ObjectMapper mapper = new ObjectMapper();
			
			CommonFunctions.log(serviceName,uid+" Url :"+url+" :: Request :"+mapper.writeValueAsString(obj),logger);
			CommonFunctions.log(serviceName,uid+" Header :"+mapper.writeValueAsString(headers),logger);
			
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(obj, headers);
			RestTemplate restTemplate = new RestTemplate(new CustomHttpComponentsClientHttpRequestFactory());
		
				responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
				if(responseEntity.getBody()!=null)
				{
				     body = responseEntity.getBody();
				     body=body.trim();
				}
				CommonFunctions.log(serviceName,uid+" Response :"+responseEntity.getStatusCodeValue()+" : "+body,logger);
				
		}
		catch (Exception e){
			responseEntity  =null; 
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
		}
		
		return responseEntity;
	}
	
	public ResponseEntity<String> httpPostRestData(String url, Object obj,MultiValueMap<String, String> headers,String serviceName,String uid)
	{
		
		ResponseEntity<String> responseEntity  =null;
		String body ="";
		try{	
			ObjectMapper mapper = new ObjectMapper();
			
			CommonFunctions.log(serviceName,uid+" Url :"+url+" :: Request :"+mapper.writeValueAsString(obj),logger);
			CommonFunctions.log(serviceName,uid+" Header :"+mapper.writeValueAsString(headers),logger);
			
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(obj, headers);
			RestTemplate restTemplate = new RestTemplate();
		
				responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
				if(responseEntity.getBody()!=null)
				{
				     body = responseEntity.getBody();
				     body=body.trim();
				}
				CommonFunctions.log(serviceName,uid+" Response :"+responseEntity.getStatusCodeValue()+" : "+body,logger);
				
		}
		catch (Exception e){
			e.printStackTrace();
			responseEntity  =null;
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
		}
		return responseEntity;
	}
	
	public ResponseEntity<String> httpDeleteRestData(String url, Object obj,MultiValueMap<String, String> headers,String serviceName,String uid)
	{
		
		ResponseEntity<String> responseEntity  =null;
		String body ="";
		try{	
			ObjectMapper mapper = new ObjectMapper();
			
			CommonFunctions.log(serviceName,uid+" Url :"+url+" :: Request :"+mapper.writeValueAsString(obj),logger);
			CommonFunctions.log(serviceName,uid+" Header :"+mapper.writeValueAsString(headers),logger);
			
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(obj, headers);
			RestTemplate restTemplate = new RestTemplate(new CustomClientHttpRequestFactory());
		
				responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
				if(responseEntity.getBody()!=null)
				{
				     body = responseEntity.getBody();
				     body=body.trim();
				}
				CommonFunctions.log(serviceName,uid+" Response :"+responseEntity.getStatusCodeValue()+" : "+body,logger);
				
		}
		catch (Exception e){
			//e.printStackTrace();
			responseEntity  =null;
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
		}
		return responseEntity;
	}
	
	public ResponseEntity<String> httpDeleteRestData(String url,MultiValueMap<String, String> headers,String serviceName,String uid)
	{
		
		ResponseEntity<String> responseEntity  =null;		
		//Instant start = Instant.now();
		String body ="";
		try{	
			ObjectMapper mapper = new ObjectMapper();
			
			CommonFunctions.log(serviceName,uid+" Url :"+url,logger);
			CommonFunctions.log(serviceName,uid+" Header :"+mapper.writeValueAsString(headers),logger);
			
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, headers);
			RestTemplate restTemplate = new RestTemplate();
		
				responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
				if(responseEntity.getBody()!=null)
				{
				     body = responseEntity.getBody();
				     body=body.trim();
				}
				CommonFunctions.log(serviceName,uid+" Response :"+responseEntity.getStatusCodeValue()+" : "+body,logger);
				
		}
		catch (Exception e){
			responseEntity  =null;
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
		}
		
		return responseEntity;
	}
	
	public ResponseEntity<String> httpPostRestData(String url, Object obj,String serviceName,String uid)
	{
		// Set the Content-Type header
	
		ResponseEntity<String> responseEntity = null;
		ObjectMapper mapper = new ObjectMapper();
		
		
		Instant start = Instant.now();
		try{
			
			CommonFunctions.log(serviceName,uid+" Url :"+url+" :: Request :"+mapper.writeValueAsString(obj),logger);			
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(new MediaType("application","json"));
			
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(obj, requestHeaders);
			
			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
	
			// Add the Jackson and String message converters
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
	
			// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
			responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
			
			String body = responseEntity.getBody();
			body=body.trim();
			CommonFunctions.log(serviceName,uid+" Response :"+responseEntity.getStatusCodeValue()+" : "+body,logger);
			
			
		}
		catch (Exception e){
			//e.printStackTrace();
			CommonFunctions.log(serviceName,uid+" Error in Api Hit : "+e.getMessage(),logger);
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		//System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
		//response.setResponseTime(timeElapsed.toMillis());
		return responseEntity;
	}
	
	public ResponseEntity<String> httpPostRestData(String url, String[] key, String[] value,String[] headerName, String[] headerValue,String uid)
	{
		Object result = null;
		//Response response = new Response();
		ResponseEntity<String> responseEntity  =null;
		
		Instant start = Instant.now();
		try{	
			logger.debug("Url :"+url);
			//logger.debug("Header:"+);
			//logger.debug("Request:"+mapper.writeValueAsString(obj));


			//HttpHeaders headers = new HttpHeaders();
			//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			for(int i=0;i<headerName.length;i++) {
				headers.add(headerName[i], headerValue[i]);
				logger.debug(headerName[i]+"="+headerValue[i]);
			}
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			for(int i=0;i<key.length;i++) {
				map.add(key[i], value[i]);
				//logger.debug(key[i]+"="+value[i]);
			}
			logger.debug("postDate::"+map.toString());
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<MultiValueMap<String, String>> requestEntity= 
					new HttpEntity<MultiValueMap<String, String>>(map, headers);
			//String token = "";
			try{
				responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
				String body = responseEntity.getBody();
				body=body.trim();
				logger.debug("Resp:"+responseEntity.getStatusCodeValue()+"|"+body+"|");
				//response.setResponseStatus(responseEntity.getStatusCodeValue());
				/*if(responseEntity.getStatusCodeValue()==CommonConstants.REQUEST_SUCCESS){
					response.setResponseStatus(responseEntity.getStatusCodeValue());
					response.setResponseString(CommonConstants.REQUEST_SUCCESS_STR);
					//beanUser = mapper.readValue(body, BeanUser.class);
					response.setBody(body);
					//response = mapper.readValue(body, Response.class);
				}
				else {
					
					response.setResponseStatus(responseEntity.getStatusCodeValue());
					response.setResponseString(CommonConstants.REQUEST_PROCESSING_FAILED);
				}*/
			}
			catch(Exception e){
				e.printStackTrace();
			}
			//return token;			
		}
		catch (Exception e){
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		//System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
		
		return responseEntity;
	}



	public Object httpGetRestData(String url, String headerName, String headerValue,String uid) {
		Object result = null;
		try{
			/*RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set(headerName, headerValue);*/

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add(headerName, headerValue);

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			
			HttpEntity<String> request = new HttpEntity<String>(headers);

			ResponseEntity<Object> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
			
			if(responseEntity != null)
				result = responseEntity.getBody();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	    return result ;
	}
	

	private static final class CustomHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
	
		@Override
		protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
	
			if (HttpMethod.GET.equals(httpMethod)) {
				return new HttpEntityEnclosingGetRequestBase(uri);
			}
			return super.createHttpUriRequest(httpMethod, uri);
		}
	}
	
	private static final class HttpEntityEnclosingGetRequestBase extends HttpEntityEnclosingRequestBase {
	
		public HttpEntityEnclosingGetRequestBase(final URI uri) {
			super.setURI(uri);
		}
	
		@Override
		public String getMethod() {
			return HttpMethod.GET.name();
		}
	}	
	public class CustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

	    @Override
	    protected void prepareConnection(HttpURLConnection connection, 
	            String httpMethod) throws IOException {

	        super.prepareConnection(connection, httpMethod);
	        if("DELETE".equals(httpMethod)) {
	            connection.setDoOutput(true);
	        }
	    }
	}
}

