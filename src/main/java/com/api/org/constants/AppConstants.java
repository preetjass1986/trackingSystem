package com.api.org.constants;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AppConstants {
	
	public static final Boolean TRUE            = true;
	public static final Boolean FALSE           = false;
	public static final String  OK 				="OK";	
	public static final String 	SUCCESS_BILLING 	= "SUCCESS";
	public static final String 	FAILED_BILLING 	    = "FAILED";
	public static final String 	SUBSCRIBE 	        = "SUBSCRIBE";
	public static final String 	UNSUBSCRIBE 	    = "UNSUBSCRIBE";
	public static final String 	TIMEZONE     	    = "GMT+5:30";
	
	/******************* WebService EndPoints ***************************/
	public static final String AUTHORIZATION				= "Authorization";
	public static final String AUTHKEY						= "authkey";
	public static final String ACCESS_CONTROL_ALLOW			= "Access-Control-Allow-Origin";
	public static final String CONTENT_TYPE					= "Content-Type";
	public static final String APPLICATION_JSON				= "application/json";
	public static final String APPLICATION_FORM_URL_ENCODED	= "application/x-www-form-urlencoded";
	

	
	public static final String DATE_FORMAT1 = "dd-MM-yyyy HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";//2019-12-14 19:10:04
	public static final String DATE_ONLY_FORMAT = "dd-MM-yyyy";
	public static final String DATE_ONLY_FORMAT1 = "yyyy-MM-dd";
	public static final String DATE_ONLY_FORMAT3 = "dd-MMMM-yyyy";
	public static final String DATE_ONLY_FORMAT2 = "E,dd-MMMM-yyyy"; // 	Day name in the week, day-Month(July)-Year
																	//Fri, 01-November-2019
	public static final int 	APPLICATION_ERROR = 401;
	public static final String APPLICATION_ERROR_SRT = "Error";
	public static final String API_LOGIN= "/login";

	/******************* Response Codes ***************************/
	public static final int SUCCESS 	   = 200;
	public static final int RECORD_EXISTS  = 201;
	
	public static final int USER_NOT_REGISTERED = 203;
	public static final int NO_CONTENT = 204;
	
	public static final String ACTIVE	   ="Active";
	public static final String INACTIVE    = "Inactive";
	public static final int ERROR = -1;
	public static final String 	FAILED_STR 	= "Failed";

	public static final String 	SUCCESS_STR 	= "Success";
	public static final String 	ERROR_STR 		= "ERROR";
	public static final String 	BEARER     		="Bearer";
	public static final String 	PLAY_LIST_NAME  ="My PlayList#";
	
	public static final String 	TEMP_DAY ="Tuesday,5th Nov 2019";
	
	public static final int 	RECORD_ALREDAY_EXISTS	= 201;
	public static final String 	RECORD_ALREDAY_EXISTS_STR 	= "Record already exist";
	
	public static final int     SUBSCRIBED     = 202;
	public static final String 	SUBSCRIBED_STR 	= "Already Subscribed.";
	
	public static final int     ALREDAY_REGITERED=301;
	public static final int     ALREDAY_REGITERED_TRIAL_USER=302;
	public static final int     OTP_SENT=303;	
	public static final int     NOT_ALLOWED=304;
	public static final String  NOT_ALLOWED_STR="Service access not allowed";

	public static final int 	BAD_REQUEST = 400;
	public static final String 	BAD_REQUEST_STR = "Somthing went wrong.Please try again after some time.";
	
	public static final int 	REQUEST_FAIL = 402;
	public static final String 	REQUEST_FAIL_STR = "Request fail";
	public static final String  REQUIRED_BODY_MISSING = " Required body missing";
	public static final String  LOWBALANCE_STR = "Not enough balance";
	public static final String  CHARGING_INPROCESS_STR = "Charging is in process";
	public static final int 	UNAUTHORIZED = 401;
	
	public static final int 	NOT_FOUND = 404;
	public static final String 	NOT_FOUND_STR = "No record available";
	
	public static final int 	CHARGING_INPROCESS = 202;
	public static final int 	LOWBALANCE = 203;
	public static final int 	NOT_SUBSCRIBE = 204;
	public static final String 	NOT_SUBSCRIBE_STR = "Not subscribed";
	public static final String 	ALREADY_CHARGED = "Already charged for Today";
	public static final String 	NOT_FOUND_SUBID = "SubId not found";
	public static final String 	NOT_SUB = "Dear user you are not a subscriber of service";
	
	public static final String 	OTP_NOT_FOUND_STR = "Invalid OTP";
	public static final int 	SERVICE_UNAVAILABLE = 503;
	public static final int 	Method_Not_Allowed =  405;
	
	public static final String 	INTERNAL_SERVER_ERROR 		= "Internal Server Error";
	public static final String 	REQUIRED_PARAMETER_MISSING	= "Required parameter missing";	
	
	public static final int 	INVALID_ACTION = 503;
	public static final String 	INVALID_ACTION_STR = "Invalid Action";
	
	public static final String 	INVALID_INPUT_OR_VALUE = "Invalid input or value";
	public static final int 	BILLING_REQUEST_FAIL = 504;	

	public static final String 	INVALID_USER = "Not a valid user";	
	public static final String 	VALID_USER = "Valid user";	
	public static final String 	ALREADY_EXIST_USER = "User Already Exists";	
	public static final String 	NEW_USER = "New Sub";	
	public static final String 	INVALID_MSISDN = "Invalid Mobile Number";	
	public static final String 	ISSUE_IN_OTP_PROCESS = "OTP process is not working.Please try after some time.";	
	public static final String 	OTP_TNVALID = "Invalid OTP";
	public static final String 	OTP_LIMIT = "OTP limit exceeded.Pls try again after one hour";
	public static final String 	OTP_TIMOUT = "OTP Time out.Please try again.";
	public static final Integer OTP_TIMOUT_CODE = 101;
	
	public static final String 	INVALID_PASSWORD = "Password and confirm password should be same.";
	public static final String 	WRONGE_AMOUNT = "Request amount cannot be less the 0";	
	
	public static final int MSG_LENGTH = 160;	
	public static final int TOKEN_MAX_TIMEOUT =10; 	

	public static final Integer NOT_AUTHORISED      = 401;
	public static final String  NOT_AUTHORISED_STRING = "You are not authorised";
	
	
	
	//##################### Message ####################
	
	public static final String 	MSG_EMAIL_ALREADY_IN_USE 	= "Email Address already in use!";
	public static final String 	MSG_MOBILE_ALREADY_IN_USE 	= "This number is already registered on service";
	public static final String 	MSG_USERNAME_ALREADY_IN_USE = "Given username already in use!";
	public static final String 	MSG_USER_SECCESS 			= "User registered successfully";
	public static final String 	MSG_UPDATE 					= "Detail updated successfully";
	public static final String  CAMPAIGN_NAME_ALREADY_EXIST = "Campaign name already exist.";
	public static final String  GROUP_NAME_ALREADY_EXIST    = "Group name already exist.";
	public static final String  NAME_ALREADY_EXIST    		= "Name already exist.";
	public static final String  MSG_CLI_ALREADY_IN_USE 		= "CLI alredy in use";
	public static final String  MSG_API_ALREADY_IN_USE 		= "API Name alredy in use";
	public static final String  MSG_INVALID_DETAIL 		    = "Invalid username or password.";
	public static final String  USER_PROFILE_UPDATED        = "User profile updated successfully";
	public static final String  RECORD_NOT_FOUND         	= "Record not found ";	
	public static final String  USER_RECORD_NOT_FOUND       = "User record not found ";	
	public static final String  INVALID_IMG_TYPE         	= "Invalid image type ";
	public static final String  RESOURCE_NOT_FOUND         	= "No Result Found ";
	public static final String  MSG_NOT_FOUND         	    = "Sms not found in sms configuration table";
	public static final String  USER_RIGHTS_UPDATED         = "User rights updated successfully";
	public static final String  PASSWORD_CHANGED_SRT        = "Password update successfully";
	public static final String  ITEM_NOT_FOUND              = "Requested item does not exist";
	public static final String  CANT_USE_DEFULTNAME			= "Please dont use defult playlist name(My PlayList) ";
	public static final String  ROLE_NOT_FOUND_STR          = "Role not provided to this user";
	public static final String  HELP_COUNT_EXCEDED          = "You can't create more than 5 helps in a day";
	public static final String  SOMETING_WENT_WRONG         = "Something went wrong";
	public static final String  USER_NOT_REGISTERED_STR     = "You are not registerd user.Please register first.";
	public static final String  SMS_OTP_SENT                = "OTP sms has been sent on your number.It will receive soon.";
	public static final String  OTP_LIMIT_EXCEED            = "OTP attempts limit is exceed for Today";
	public static final String 	SOMTHING_WENT_WRONG 		= "Somthing went wrong.Please try again";
	public static final String 	ALIAS_NOT_FOUND 		    = "Not a valid user";
	public static final String 	UNABLE_TO_PROCESS 		    = "Sorry! Unable to process your request";
	public static final String 	NOT_ALLOWD 		            = "Not Allowed";
	public static final String 	SERVER_PROBLEM              = "Server Problem!";
	public static final String  QUIZ_NOT_FOUND         	    = "Quiz not found!";
	public static final String  PATICIPENT_NOT_FOUND        = "Participent not found!";
	public static final String  COMPETITION_NOT_FOUND       = "Competition not found!";
	public static final String  COMPETITION     			= "Competition!";
	public static final String  COMPETITION_ADD 			= "Competition added!";
	public static final String  COMPETITION_DELETE			= "Competition deleted!";
	public static final String  POINT_SAVE      			= "Points saved!";	
	public static final String  FUN_FACTS       			= "Fun Facts!";
	public static final String  QUIZZES     			    = "Quizzes!";
	public static final String  QUIZ_QUESTIONS     			= "Quiz Questions!";
  

	
	
	public static final int 	ZERO 		= 0;	
	public static final int 	ONE  		= 1;
	public static final int 	TWO  		= 2;
	public static final int 	THREE  		= 3;
	public static final int 	FOUR  		= 4;
	public static final int 	FIVE  		= 5;
	public static final int 	SIX  		= 6;
	public static final int 	SEVEN  		= 7;
	public static final int 	EIGHT  		= 8;
	public static final int 	NINE  		= 9;
	
	
	public static final int 	ROLE_SUPPER_ADMIN_ROLE  	= 1;
	public static final int 	ROLE_ADMIN_ROLE  	= 2;
	public static final int 	ROLE_PMG_ADMIN		= 3;
	public static final int 	ROLE_FACILITY_MASTER  = 4;
	public static final int 	ROLE_STAGE_USER  	= 5;
	public static final int 	ROLE_TEST_USER_ROLE  = 6;
	public static final int 	ROLE_AGENT_ROLE  	= 7;
	

	
	
	// Controller details
	public static final String CONTROLLER_MAIN_API="/api/auth";
	public static final String CONTROLLER_MAIN_PROTECTED="/api/protected";
	public static final String CONTROLLER_MAIN_ADMIN="/api/admin";
	public static final String CONTROLLER_REGISTER_USER="/register";
	public static final String CONTROLLER_LOGIN="/login";
	
	public static final String CONTROLLER_MANAGE_PROJECT="/manageProject";	
	public static final String CONTROLLER_MANAGE_PROJECT_BOM="/manageProjectBOM";	
	public static final String CONTROLLER_PROJECT="/project";
	public static final String CONTROLLER_PROJECT_BY_ID="/project/{id}";
	
	public static final String CONTROLLER_MANAGE_MODULE="/manageModule";	
	public static final String CONTROLLER_MODULE="/module";
	public static final String CONTROLLER_MODULE_BY_ID="/module/{id}";	
	
	public static final String CONTROLLER_MANAGE_COMPONENT="/manageComponent";		
	public static final String CONTROLLER_COMPONENT="/component";
	public static final String CONTROLLER_COMPONENT_BY_ID="/component/{id}";	
	
	public static final String CONTROLLER_MANAGE_COMPONENT_MASTER="/manageComponentMaster";		
	public static final String CONTROLLER_COMPONENT_MASTER="/componentMaster";
	public static final String CONTROLLER_COMPONENT_MASTER_BY_ID="/componentMaster/{id}";
	
	public static final String CONTROLLER_STAGE="/stage";
	public static final String CONTROLLER_STAGE_BY_ID="/stage/{id}";	
	public static final String CONTROLLER_MANAGE_STAGE="/manageStage";
    public static final String CONTROLLER_STAGE_FLOW="/stageFlow";
	public static final String CONTROLLER_STAGE_FLOW_BY_ID="/stageFlow/{id}";	
	public static final String CONTROLLER_MANAGE_STAGE_FLOW="/manageStageFlow";	

    public static final String CONTROLLER_FACILITY="/facility";
	public static final String CONTROLLER_FACILITY_BY_ID="/facility/{id}";	
	public static final String CONTROLLER_MANAGE_FACILITY="/manageFacility";
	public static final String CONTROLLER_FACILITY_FLOW="/facilityFlow";
	public static final String CONTROLLER_FACILITY_FLOW_BY_ID="/facilityFlow/{id}";	
	public static final String CONTROLLER_MANAGE_FACILITY_FLOW="/manageFacilityFlow";



    public static final String MODE_USSD="USSD";
    public static final String MODE_SMS="SMS";
    public static final String MODE_ICG="ICG";
    public static final String MODE_WAP="WAP";
    public static final String MODE_CC="CC";
    

	public static final String ROLE_ADMIN        ="admin";
	public static final String ROLE_USER         ="user";
    public static final String LANGUAGEID        ="languageId";
   	public static final String USERID            ="userId";
	public static final String ACTION_SUB        ="SUB";
	public static final String ACTION_SUB_CHARGE ="SUB_CHARGE";
	public static final String ACTION_UNSUB      ="UNSUB";
	public static final String ACTION_RENEWAL    ="RENEWAL";
	public static final String SMS_SUB           ="SUB";
	public static final String SMS_SUBTNB        ="SUBTNB";
	public static final Integer DEFAULT_LANG_ID=0;
	
    public static final String FILE_PATH="/quiz-images/user-images/";   
	public static final String QUIZ_MASTER_BASE_URL="https://vtz.quizzzmaster.com";	
	public static final String DEFAULT_IMAGE="/quiz-images/user-images/logo.png?v=1";
	public static final String CONTENT_TYPE_XML="text/xml; charset=UTF-8"; 
	
	// ############################# Vodacom TNZ Billing Api#############################	
	   
	    public static final String OP_SUB_CHANNEL="USSDPush";
		public static final String OP_OPTIN="opt-in";
	    public static final String OP_OPTOUT="opt-out";
	    public static final String OP_OPTOUT_BULK="Bulk Opt-Out";
	    public static final String CHARGE_TYPE="Subscription"; 
		public static final String logFileName="VodaService";
	    
	    public static final String OP_SHORTCODE="102080";
	    public static final String OP_USERNAME="102080";
	    public static final String OP_PASSWORD="mYRJt5~IFs_FG+h";	    
	    public static final String OP_URL_SUB = "https://icg.api.vodacom.co.tz:23000/icg/Enticement/";	
		public static final String OP_URL_UNSUB = "https://icg.api.vodacom.co.tz:23000/icg/unsub/";
		public static final String OP_URL_PAYMENT="https://icg.api.vodacom.co.tz:23000/icg/Charge/";
		public static final String OP_URL_BALANCE="https://icg.api.vodacom.co.tz:23000/icg/query/balance/";
		
		public static final String OP_URL_WAP_SUB="https://myvodacomappdev.vodacom.co.tz/digital-consent-engine/api/v1/subscription/generate-session";
		public static final String OP_URL_WAP_REDIRECT="https://vtz.quizzzmaster.com/?id=";
	    
	    // ############## for testing ###########
		public static final String OP_SHORTCODE_TEST="160162";
		public static final String OP_USERNAME_TEST="160162";
		public static final String OP_PASSWORD_TEST="fvD}]jOym&,m6"; //"Quiz@2025!"
		
		public static final String OP_URL_SUB_TEST = "http://197.250.9.128:23000/icg/Enticement/";	
		public static final String OP_URL_UNSUB_TEST = "http://197.250.9.128:23000/icg/unsub/";
		public static final String OP_URL_PAYMENT_TEST="http://197.250.9.128:23000/icg/Charge/";
		public static final String OP_URL_BALANCE_TEST="http://197.250.9.128:23000/icg/query/balance/";
		
		 // ######################################  //

		public static final String OP_SMS_SENDER="QUIZ MASTER"; //QUIZ MASTER
		public static final String OP_SMS_PPROMO_SENDER="vodacom"; //15503
		public static final String OP_USSD="<ussd>\r\n<type>{TYPE}</type>\r\n<msg>\r\n{MSG}\r\n</msg>\r\n</ussd>";
		
		public static final Map<String, String> OPSMS = new HashMap<String, String>(){
	    {
            put("SMS_SUB_0", "You have subscribed on Quiz Master service. To access service click on this link https://vtz.quizzzmaster.com/register?id=<ID>");
            put("SMS_SUB_4", "Umejisajili kwenye huduma ya Quiz Master. Ili kupata huduma bonyeza kiungo hiki https://vtz.quizzzmaster.com/register?id=<ID>");
           
            put("SMS_LOWBALANCE_0","Dear customer, you have insufficient balance. Please top up your airtime and try again to access this service.");
            put("SMS_LOWBALANCE_4","Mpendwa mteja, hauna salio la kutosha. Tafadhali ongeza salio lako na ujaribu tena kutumia huduma hii.");
    		
            put("SMS_UNSUB_USSD_0","You have successfully unsubscribed from Quiz Master. To resubscribe, dial *149*67# then choose SUBSCRIBE");
            put("SMS_UNSUB_USSD_4","Umefanikiwa kujiondoa kwenye huduma ya Quiz Master. Kujiunga tena, piga *149*67# kisha chagua kujiunga.");
    		
            put("USSD_CONFIRM_0","\r\nDear Customer, you have received a subscription confirmation message. Please confirm your subscription.\r\n");
            put("USSD_CONFIRM_4","\r\nMpendwa Mteja, umepokea ujumbe wa kuthibitisha usajili. Tafadhali thibitisha usajili wako.\r\n");
    		
	     }
	    };
		
		
		
		
		
		
	

	
	
	

	
	
}
