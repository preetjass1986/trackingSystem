package com.api.org.serviceImpl;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.api.org.constants.AppConstants;
import com.api.org.service.MantraService;
import com.api.org.view.Response;

import MFS100.DeviceInfo;
import MFS100.FingerData;
import MFS100.MFS100;
import MFS100.MFS100Event;

@Service("MantraService")
public class MantraServiceImpl implements MantraService, MFS100Event{

	MFS100 mfs100 = null;
	 String FilePath="E:\\Java_Code\\Biometric\\FingerData\\1.iso";
	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Override
	public Response start() {
		
		return null;
	}

	@Override
	public Response stop() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Response autoCapture() {
		Response response =new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
		try {
			mfs100 = new MFS100(this,"");
			ShowMessage(""+mfs100.IsConnected()); 
			int ret=mfs100.Init();
			if(ret == 0)
			{
				ShowMessage("Start");
				DeviceInfo  deviceInfo = mfs100.GetDeviceInfo();
				if (deviceInfo != null) 
				{ 
					String scannerInfo = "SERIAL NO.: "+ deviceInfo.SerialNo() + " MAKE: "+ 
					deviceInfo.Make() + " MODEL: "+ deviceInfo.Model(); 
					ShowMessage(scannerInfo);

					FingerData fingerData = new FingerData();
		 	        ret = mfs100.AutoCapture(fingerData, 1000*60,  true,true);		 	       
					if (ret != 0) 
					{ 
						ShowMessage(mfs100.GetErrorMsg(ret)); 
					} 
					else  if (ret == 0 && fingerData.FingerImage()!=null) 
					{
						 File file = new File(FilePath);
					      if (file.exists())
					      {
								ret = mfs100.MatchISO( fingerData.ISOTemplate(),readFile(FilePath) ); 
								if (ret >= 0) 
								{ 
									if (ret >= 14000)  ShowMessage("Finger matched with score: " + String.valueOf(ret)); 
									else ShowMessage("Finger not matched, score: " +String.valueOf(ret) + " is too low"); 
								} 
								else ShowMessage(mfs100.GetErrorMsg(ret)); 
						  }
					      else 
						  {
					    	  ShowMessage("Write data");
					    	  WriteBytesToFile(FilePath, fingerData.ISOTemplate()); 
						  }
					}
					else ShowMessage("Problem..");
					if(mfs100!=null && mfs100.IsConnected()) {mfs100.StopCapture();mfs100.Uninit();}
				}else {ShowMessage("Not Connected");}
					
			}else {ShowMessage("Not init");}
			
			
		} catch (Exception exp) {
			exp.printStackTrace();
			ShowMessage(exp.getMessage());
		}

		return response;
	}
	
	@Override
	public Response verify() {
		return null;
	}

	@Override
	public Response capture() {
		Response response =new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
		try {
			mfs100 = new MFS100(this,"");
			ShowMessage(""+mfs100.IsConnected()); 
			int ret=mfs100.Init();
			if(ret == 0)
			{
				ShowMessage("Start");
				DeviceInfo  deviceInfo = mfs100.GetDeviceInfo();
				if (deviceInfo != null) 
				{ 
					String scannerInfo = "SERIAL NO.: "+ deviceInfo.SerialNo() + " MAKE: "+ 
					deviceInfo.Make() + " MODEL: "+ deviceInfo.Model(); 
					ShowMessage(scannerInfo);

					ret =mfs100.StartCapture(55, 1000*60, true);
					if (ret != 0) 
					{ 
						ShowMessage(mfs100.GetErrorMsg(ret)); 
					} 
					else ShowMessage("Done..");
				}else {ShowMessage("Not Connected");}
					
			}else {ShowMessage("Not init");}
			
			
		} catch (Exception exp) {
			exp.printStackTrace();
			ShowMessage(exp.getMessage());
		}

		return response;
	}

	@Override
	public Response info() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void OnCaptureCompleted(boolean status, int errorCode, String errorMsg, final FingerData fingerData) {
		  ShowMessage("OnCaptureCompleted "+status+" :: "+errorMsg);
		 // String FilePath = System.getProperty("user.dir");
	     // FilePath += "\\FingerData\\ISOTemplate.iso";
		  if(status)
		  {
			 
		      File file = new File(FilePath);
		      if (file.exists())
		      {
					int ret = mfs100.MatchISO( fingerData.ISOTemplate(),readFile(FilePath) ); 
					if (ret >= 0) 
					{ 
						if (ret >= 14000)  ShowMessage("Finger matched with score: " + String.valueOf(ret)); 
						else ShowMessage("Finger not matched, score: " +String.valueOf(ret) + " is too low"); 
					} 
					else ShowMessage(mfs100.GetErrorMsg(ret)); 
			  }
		      else 
			  {
		    	  ShowMessage("Write data");
		    	  WriteBytesToFile(FilePath, fingerData.ISOTemplate()); 
			  }
		  }
		  if(mfs100!=null && mfs100.IsConnected()) {mfs100.StopCapture();mfs100.Uninit();}
	}

	@Override
	public void OnPreview(FingerData arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public  void ShowMessage(String data)
	  {
		logger.info(data);
	  }
	  public  byte[]  readFile(String filePath) {
		  byte[] data=null;
			 try
			 {
				 File file =new File(filePath);
		         if(file.exists())
		         {	         	
		        	    FileInputStream fileInputStream=new FileInputStream(file);
			    		data = new byte[(int) file.length()];
			    		DataInputStream dis = new DataInputStream(fileInputStream);
			    		dis.readFully(data);       	
		         }
			 }catch(Exception ex)
			 {
				 ex.printStackTrace();
			 }
			 return data;
		  
	      
	  }
	  public  void WriteBytesToFile(String FilePath,byte[]  Bytes) {

		  try {
	          FileOutputStream fos = new FileOutputStream(FilePath);
	          fos.write(Bytes);
	          fos.close();
	      } catch (Exception ex) {
	      }
	  }

}
