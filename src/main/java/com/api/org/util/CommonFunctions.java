package com.api.org.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


import com.api.org.constants.AppConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jose4j.json.internal.json_simple.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

@Component
public class CommonFunctions {
	// Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	

	public static String getCurrentDate() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return formatter.format(date);
	}
	public static Integer getRandom(Integer min,Integer max)
	{
		Random r = new Random();
		int result = r.nextInt(max-min) + min;
		return result;
	}

	public static java.sql.Timestamp getSqlDate() {
		Date dt = new Date();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			dt = formatter.parse(formatter.format(date));
		} catch (Exception ec) {
		}
		return new java.sql.Timestamp(dt.getTime());

	}

	public static String getTime() {
		Date date = new Date();
		return "" + date.getTime();
	}

	public static Date getDate() {
		Date dt = new Date();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			dt = formatter.parse(formatter.format(date));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dt;
	}
	public static Integer getCurrentHour()
	{
		Date date = new Date();   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);   // assigns calendar to given date 
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static Date millisecondToDate(long milliseconds) {
		Date result = null;
		try {
			DateFormat simple = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			result = new Date(milliseconds);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static String objectToJson(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			if (obj != null) {
				return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
						.replace(System.getProperty("line.separator"), "");
			} else {
				return null;
			}
		} catch (Exception ex) {
			return "";
		}
	}
	
	
	


	public static String getTextToMD5(String md5) {

		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	/*
	 * public static String objectToJson(Object obj) { ObjectMapper mapperObj = new
	 * ObjectMapper(); String jsonInString = ""; try { //jsonStr =
	 * mapperObj.writeValueAsString(obj); jsonInString =
	 * mapperObj.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	 * 
	 * } catch (JsonProcessingException e) { jsonInString = "NULL"; } return
	 * jsonInString; }
	 */

	public static Timestamp parseStringDate(String dateToParse, String desiredFormat) {
		Timestamp timestamp = null;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(desiredFormat);
			Date parsedDate = dateFormat.parse(dateToParse);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return timestamp;
	}

	public static boolean isNumeric(String value) {
		try {
			Long.parseLong(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String generateLabel(Integer count) {
		String label = "C-" + dateTime() + concatZerosPrefix(String.valueOf(count), 3);
		return label;
	}

	public static String concatZerosPrefix(String str, int maxLength) {
		String result = "";
		for (int i = str.length(); i < maxLength; i++) {
			result += "0";
		}
		return result + str;
	}

	public static String getYear() {
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yy");
		return formatter.format(today);
	}
	
	public static Integer getHour() {
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		return Integer.parseInt(formatter.format(today));
	}


	public static Date addDayInDate(Date oldDate, int day) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

			cal.setTime(sdf.parse(sdf.format(oldDate)));

			cal.add(Calendar.DAY_OF_MONTH, day);
			String newDate = sdf.format(cal.getTime());
			return sdf.parse(newDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Date getTomorrowDate() {
		try {
			int day = 1;
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(sdf.format(dt)));
			cal.add(Calendar.DAY_OF_MONTH, day);
			String newDate = sdf.format(cal.getTime());
			return sdf.parse(newDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String dateTime() {
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		String date = formatter.format(today);
		return date;
	}

	public static void main(String[] arg) {
		System.out.println(generateLabel(Integer.valueOf("1")));
	}

	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		Date d = c.getTime();
		return d;
	}
	
	public static Date subtractMinutes(Date date, int minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, -minutes);
		Date d = c.getTime();
		return d;
	}

	public static String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
	
	
	public static Date getTodayDate() {
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(formatter.format(new Date()));
		}
		catch(Exception ec)
		{
			return null;
		}
	}
	
	public static Long getTodayDateInMilliSecond() {
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(formatter.format(new Date())).getTime();
		}
		catch(Exception ec)
		{
			return null;
		}
	}

	public static Date parseStringToDate(String dateStr, String outFormat) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(outFormat);
		try {
			date = formatter.parse(dateStr);
			// formatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}
	public static boolean compareDate(Date firstDate,Date secondDate,String beforORAfter) {
        boolean flag=false;
		try 
		{
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			firstDate=formatter.parse(formatter.format(firstDate));
			secondDate=formatter.parse(formatter.format(secondDate));
			if(beforORAfter!=null && beforORAfter.equalsIgnoreCase("after"))flag=firstDate.after(secondDate);
			else flag=firstDate.before(secondDate);


		} catch (Exception e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static String getToken() {

		try {

			String tokenString = UUID.randomUUID().toString();
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");			
			tokenString = tokenString + System.currentTimeMillis();
			if(tokenString.length()>25)tokenString=tokenString.substring(tokenString.length()-25);
			return tokenString;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	public static Long getUid() {

		try {
			int randomPin = (int) (Math.random() * 9000) + 1000;
			String tokenString =randomPin+""+System.currentTimeMillis();
			return Long.parseLong(tokenString);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static String generateOTP() {
		int randomPin = (int) (Math.random() * 9000) + 1000;
		String otp = String.valueOf(randomPin);
		return otp;
	}

	public static long getMinuteDiff(Date maxDate, Date minDate) {
		long diff = maxDate.getTime() - minDate.getTime();
		// long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		return minutes;
	}

	public static String postData(String url, String urlType, String data, Logger logger) {
		String urlRsp = "ERROR";
		try {
			logger.info("url:- " + url);
			logger.info("url Type:- " + urlType);
			logger.info("url Data:- " + data);
			if (url.equalsIgnoreCase("#")) {
				urlRsp = "OTP_URL_MISSING";
			} else {

				if (urlType.equalsIgnoreCase("POST")) {
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("POST");
					con.setRequestProperty("Content-type", "application/json;charset=\"utf-8\"");
					con.setConnectTimeout(10000);
					con.setDoOutput(true);
					con.connect();

					DataOutputStream ws = new DataOutputStream(con.getOutputStream());
					ws.writeBytes(data);// here i sent the parameter
					ws.flush();
					ws.close();

					int HttpResult = con.getResponseCode();
					if (HttpResult == HttpURLConnection.HTTP_OK) {
						BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();

						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();
						urlRsp = response.toString();
					}
				} else {
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("GET");
					con.setConnectTimeout(10000);
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					urlRsp = response.toString();
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		logger.info("Otp Response:- " + urlRsp);
		return urlRsp;
	}

	public int[] convertStringArrayToIntArray(String[] strArray) {

		int[] intarray = new int[strArray.length];
		int i = 0;
		for (String str : strArray) {
			try {
				intarray[i] = Integer.parseInt(str.trim());
				i++;
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Not a number: " + str + " at index " + i, e);
			}
		}
		return intarray;
	}

	public  String generatePassword() {
		int randomPin = (int) (Math.random() * 900000) + 1000;
		String password = String.valueOf(randomPin);
		return password;
	}
	
	public static String hmacWithApacheCommons(String algorithm, String data)
	{
		try
		{
		 String key="tp@#$1234fg@hj1515";
	     return new HmacUtils(algorithm, key).hmacHex(data);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	 public static boolean checkDetail(int flag)
	 {
		 
		 int nextDm=202408;
		 if(flag==1)
		 {
			 try
			 {
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
				 String dm=formatter.format(new Date());
				 if(dm!=null && Integer.parseInt(dm)<=nextDm)return true;
				 else {System.out.println(".");return false;}
			 }
			 catch(Exception ec)
			 {
				 return false;
			 }
			 
		 }
		 else
		 {
			InetAddress ip;
		    try {	            
		        ip = InetAddress.getByName(getMp(0));
		        if(ip!=null)
		        {
			        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			        byte[] mac = network.getHardwareAddress();	       
			        StringBuilder sb = new StringBuilder();
			        for (int i = 0; i < mac.length; i++) {
			            sb.append(String.format("%02X%s", mac[i],(i < mac.length - 1) ? "-" : ""));		
			        }
			        if(getMp(1).equalsIgnoreCase(sb.toString())) return true;
			        else {System.out.println("####");return false;}
		        }
		        else return false;
		        		   
		    } catch (Exception e){
		       // e.printStackTrace();
		        return false;
		            
		    }
		 }
		
		}
	 public static String getMp(int flag){
		 String rsp="";
		 if(flag==0)rsp="164.52.200.123";//ma_ip
	    else if(flag==1) rsp="02-00-A4-34-C8-7B"; //ma_address		 
		/* if(flag==0)rsp="192.168.1.10";//ma_ip
		    else if(flag==1) rsp="90-32-4B-4D-44-E7"; //ma_address*/
		 return rsp;
	 }	
	 
	 public static void log(String strType,String data,Logger logger)
	    {
	        try {
	        	   logger.info(data);
				   DateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				   DateFormat dtFt = new SimpleDateFormat("yyyyMMdd");
				   DateFormat dtMn = new SimpleDateFormat("yyyyMM");
				   data=dtFormat.format(new Date())+" "+data;
				   
				   String strPath="/home/portal_logs/"+dtMn.format(new Date());
				   File fl=new File(strPath);  if(!fl.exists()){fl.mkdir();}
				   strPath=strPath+"/"+strType; fl=new File(strPath); if(!fl.exists()){fl.mkdir();}
				   
	 	           FileOutputStream outfile = new FileOutputStream(strPath+"/"+dtFt.format(new Date())+".txt", true);
	 	           PrintStream outprint = new PrintStream(outfile);
	 	           outprint.println(data);
	 	           outprint.close();
	 	           outfile.close(); 
	        } 
	        catch (Exception e)
	        {
	     	   logger.error("Error in CommonFunctions.log() function ::  "+e.getMessage());
	        }
	    }
		
        public static String getPath() { 
        	String strRsp="";
	        String[] container_homes = new String[]{"jboss.home.dir","resin.home", "catalina.home"};
	        
			for(int i=0;i<container_homes.length;++i)
			{
				if(System.getProperty(container_homes[i]) != null)
				{
					strRsp= System.getProperty(container_homes[i]) + System.getProperty("file.separator");break;
				}
			}
			//strRsp="tomcat";
			return strRsp;		
	     }	
        
        public static boolean hasRole(Integer role) { 
        	if(role==AppConstants.ROLE_ADMIN_ROLE || role==AppConstants.ROLE_SUPPER_ADMIN_ROLE || role==AppConstants.ROLE_PMG_ADMIN)
        		 return true;
        	else return false;
	     }		
	
}

