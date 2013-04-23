package prj.src.rateit.util;

import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.io.*;
/*NOTE
 * I'm not sure if the ip address in the url is correct or not.
 * However, the server will be listening to port 33066, so that part is correct
 */

/**
 * Database Name: lab6v1 contains 3 tables: business, owners, users
 * --Table owners has 5 columns:
 * ----id, int auto-incremented
 * ----email, 40 chars
 * ----password, 40 chars
 * ----firstName, 40 chars
 * ----lastName, 40 chars
 * --Table users has 3 columns:
 * ----businessName, 40 chars--to be used as an ID
 * ----comment, text
 * ----rating, int
 * --Table business has 14 columns:
 * ----email, 40 chars--to be used as an ID, this is the owner's email address, not the business'
 * ----businessName, 40 chars--is used as an ID in certain functions
 * ----description, text
 * ----address, 200 chars
 * ----monHours, 100 chars
 * ----tueHours, 100 chars
 * ----wedHours, 100 chars
 * ----thuHours, 100 chars
 * ----friHours, 100 chars
 * ----satHours, 100 chars
 * ----sunHours, 100 chars
 * ----rating, int
 * ----website, 100 chars--the business's website, not the owner's
 * ----numberOfRates, int
 */
public class DatabaseAPI {
	
	/**
	 *Adds a new owner to the database
	 *returns true if successful, false if not.
	 */
	public static boolean addOwner(String email, String password, String first, String last){
		Socket requestSocket;
		PrintWriter out;
		BufferedReader in;
		String answer = null;
		try{
			System.out.println("ATTEMPTING TO CONNECT!");
			requestSocket = new Socket("128.10.25.101",7755);
			System.out.println("CONNECTED!");
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
			out.println("ADDOWNER|"+email+"|"+password+"|"+first+"|"+last);
			answer = in.readLine();
			requestSocket.close(); 
			
		}catch(Exception err){
			System.out.println(err.toString());
		}
		if(answer.equals("true")){
			System.out.println("SUCCESSFULLY CREATED SOMEONE!");
			return true;			
		}else{
			System.out.println("FAILURE!");
			return false;
		}
	}
	
	/**
	 *Updates an owner's information
	 */
	public static void updateOwner(String email,String newemail, String password, String first, String last){
		Socket requestSocket;
		PrintWriter out;
		try{
			System.out.println("ATTEMPTING TO CONNECT!");
			requestSocket = new Socket("128.10.25.101",7755);
			System.out.println("CONNECTED!");
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			out.println("UPDATEOWNER|"+email+"|"+newemail+"|"+password+"|"+first+"|"+last);
			requestSocket.close(); 
			
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}
	
	/**
	 *Adds a new Business to the database
	 */
	public static void addBusiness(String email, String business, String desc, String address, String web, String mon, String tue, String wed, String thr, String fri, String sat, String sun){
		Socket requestSocket;
		PrintWriter out;
		try{
			System.out.println("ATTEMPTING TO CONNECT!");
			requestSocket = new Socket("128.10.25.101",7755);
			System.out.println("CONNECTED!");
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			out.println("ADDBUSINESS|"+email+"|"+business+"|"+desc+"|"+address+"|"+web+"|"+mon+"|"+tue+"|"+wed+"|"+thr+"|"+fri+"|"+sat+"|"+sun);
			requestSocket.close(); 
			
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}
	
	/**
	 *Checks a users credentials
	 *Returns true if successful
	 *Returns false if not 
	 */
	public static boolean login(String user,String password){
		Socket requestSocket;
		PrintWriter out;
		BufferedReader in;
		String answer = null;
		try{
			requestSocket = new Socket("128.10.25.101",7755);
			in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			out.println("LOGIN|"+user+"|"+password);
			
			answer = in.readLine();
			requestSocket.close(); 
			
		}catch(Exception err){
			System.out.println(err.toString());
		}
		if(answer.equals("true")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 *Gets all information from a business
	 */
	public static String[] getOwnersBusinesses(String email){
		Socket requestSocket;
		PrintWriter out;
		BufferedReader in;
		String answer=null;
		List<String> ans = null;
		String[] strarray = null;
		try{
			requestSocket = new Socket("128.10.25.101",7755);
			in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			
			out.println("GETOWNERSBUSINESSES|"+email);
			answer = in.readLine();
			
			requestSocket.close();
			
		}catch(Exception err){}
		
		if(answer!=null){
			ans = Arrays.asList(answer.split("\\|"));
			strarray = ans.toArray(new String[0]);
			for(int i=0;i<strarray.length;i++){
				System.out.println(strarray[i]);
			}
		}
		return strarray;
	}
			
	/**
	 * Gets the names of all businesses sorted in the business table
	 * Returns them as a ResultSet object
	 */
	public static String[] getAllBusinesses(){
		Socket requestSocket;
		PrintWriter out;
		BufferedReader in;
		String answer=null;
		List<String> ans = null;
		String[] strarray = null;
		try{
			requestSocket = new Socket("128.10.25.101",7755);
			in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			
			out.println("GETALLBUSINESSES");
			answer = in.readLine();
			
			requestSocket.close();
			
		}catch(Exception err){}
		
		if(answer!=null){
			ans = Arrays.asList(answer.split("\\|"));
			strarray = ans.toArray(new String[0]);
			for(int i=0;i<strarray.length;i++){
				System.out.println(strarray[i]);
			}
		}
		return strarray;
	}
	/**
	 * Retrieves information for the chosen business from the business table
	 * Returns the values as a ResultSet object
	 * Currently only works properly if there is only one business of that name in the table
	 * 0BusinessName | 1Description | 2Address | 3Mon | 4Tues | 5Wed | 6Thurs | 7Fri | 8Sat | 9Sun | 10Rating | 11Website
	 */
	public static String[] getBusinessInfo(String business){
		Socket requestSocket;
		PrintWriter out;
		BufferedReader in;
		String answer=null;
		List<String> ans = null;
		String[] strarray = null;
		try{
			requestSocket = new Socket("128.10.25.101",7755);
			in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			
			out.println("INFO|"+business);
			answer = in.readLine();
			
			requestSocket.close();
			
		}catch(Exception err){}
		
		if(answer!=null){
			ans = Arrays.asList(answer.split("\\|"));
			strarray = ans.toArray(new String[0]);
			for(int i=0;i<strarray.length;i++){
				System.out.println(strarray[i]);
			}
		}
		return strarray;
	}
	/**
	 * Allows an owner to change all information about a business with name oName
	 * Currently all information must be entered at once
	 * Currently only works properly if there is only one business with the name in the table
	 * 0EMAIL|1BUSINESS|2DESCRIPTION|3ADDRESS|4MONDAY|5TUESDAY|6WEDNESDAY|7THURSDAY|8FRIDAY|9SATURDAY|10SUNDAY|11EMAIL
	 */
	public static void updateBusiness(String email, String business, String desc, String address, String mon, String tue, String wed, String thr, String fri, String sat, String sun, String web){
		Socket requestSocket;
		PrintWriter out;
		try{
			requestSocket = new Socket("128.10.25.101",7755);
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			out.println("UPDATE|"+email+"|"+business+"|"+desc+"|"+address+"|"+mon+"|"+tue+"|"+wed+"|"+thr+"|"+fri+"|"+sat+"|"+sun+"|"+web);			
			requestSocket.close(); 
			
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}
	/**
	 * Allows a user to enter a row with comment and rating into the users table
	 * Also, updates the business' rating in the business table
	 */
	public static void rate(int rate, String business){
		Socket requestSocket;
		PrintWriter out;
		try{
			requestSocket = new Socket("128.10.25.101",7755);
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			out.println("RATE|"+business+"|"+rate);
			requestSocket.close(); 
			
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}
	/**
	 *Testing method that sends "testing" to the server. server should return okay.
	 */
	public static void test(){
		
		Socket requestSocket;
		PrintWriter out;
		BufferedReader in;
		try{
			requestSocket = new Socket("128.10.25.101",7755);
			in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
			out = new PrintWriter(requestSocket.getOutputStream(),true);
			out.println("testing");
			System.out.println(in.readLine());
			requestSocket.close();
		}catch(Exception err){
			System.out.println(err.toString());
		}		
	}
}
