package com.budev.utils;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;

public class MethodUtils {
	
	private MethodUtils() {
	}

		public static String prepareErrorJSON(HttpStatus status, Exception ex) {
	    	JSONObject errorJSON=new JSONObject();
	    	try {
				errorJSON.put("success",false);
				errorJSON.put("message",ex.getMessage());
		    	errorJSON.put("statusCode",status.value());
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
	    	
	    	return errorJSON.toString();
	}

	public static String prepareSuccessJSON(HttpStatus status, String message) {
		JSONObject successJSON=new JSONObject();
		try {
			successJSON.put("success",true);
			successJSON.put("message",message);
			successJSON.put("statusCode",status.value());
		} catch (JSONException e) {

			e.printStackTrace();
		}

		return successJSON.toString();
	}



}
