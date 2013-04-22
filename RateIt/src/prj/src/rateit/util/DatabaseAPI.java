package prj.src.rateit.util;

import java.net.*;
import java.sql.*;
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
	 *returns 1 if successful, -1 if not 
	 */
	public int addOwner(String email, String password, String first, String last){
		Socket requestSocket;
		OutputStream outToServer;
		try{
			requestSocket = new Socket("128.10.25.101",33766);
			outToServer = requestSocket.getOutputStream();
			DataOutputStream out =new DataOutputStream(outToServer);
			out.writeUTF("ADDOWNER "+email+" "+password+" "+first+" "+last);
			requestSocket.close();
			
		}catch(Exception err){}
		return 0;
	}
	
	/**
	 *Adds a new Buisness to the database
	 *returns the new Buisness's id if successful
	 *-1 if not. 
	 */
	public int addBuisness(String email, String business, String desc, String address, String web, String mon, String tue, String wed, String thr, String fri, String sat, String sun){
		Socket requestSocket;
		OutputStream outToServer;
		try{
			requestSocket = new Socket("128.10.25.101",33766);
			outToServer = requestSocket.getOutputStream();
			DataOutputStream out =new DataOutputStream(outToServer);
			out.writeUTF("RATE "+email+" "+business+" "+desc+" "+address+" "+web+" "+mon+" "+tue+" "+wed+" "+thr+" "+fri+" "+sat+" "+sun);
			requestSocket.close();
				
		}catch(Exception err){}
			

		
		
		return 0;
	}
	
	/**
	 *Checks a users credentials
	 *Returns true if successful
	 *Returns false if not 
	 */
	public String login(String user,String password){
		Socket requestSocket;
		OutputStream outToServer;
		InputStream inFromServer;
		String answer=null;
		try{
			requestSocket = new Socket("128.10.25.101",33766);
			outToServer = requestSocket.getOutputStream();
			inFromServer = requestSocket.getInputStream();
			DataOutputStream out =new DataOutputStream(outToServer);
			DataInputStream in = new DataInputStream(inFromServer);
			
			out.writeUTF("LOGIN "+user+" "+password);
			answer = in.readUTF();
			
			requestSocket.close();
			
		}catch(Exception err){}
		

		
		
		return answer;
	}
	
	/**
	 * Gets the names of all businesses from the business table with a given email
	 * Returns a ResultSet object containing all names
	 */
	public String[] getOwnersBusiness(String email){
		Socket requestSocket;
		OutputStream outToServer;
		InputStream inFromServer;
		String answer=null;
		String[] ans = null;
		try{
			requestSocket = new Socket("128.10.25.101",33766);
			outToServer = requestSocket.getOutputStream();
			inFromServer = requestSocket.getInputStream();
			DataOutputStream out =new DataOutputStream(outToServer);
			DataInputStream in = new DataInputStream(inFromServer);
			
			out.writeUTF("GETOWNERBUISNESSES"+email);
			answer = in.readUTF();
			
			requestSocket.close();
			
		}catch(Exception err){}
		
		if(answer!=null){
			
			
		}
		
		return ans;
	}
	
	/**
	 * Gets the names of all businesses sorted in the business table
	 * Returns them as a ResultSet object
	 */
	public ResultSet getAllBusiness(){
		
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			
			PreparedStatement stat = con.prepareStatement("SELECT businessName FROM business");
			ResultSet r = stat.executeQuery();
			return r;
		}catch(Exception err){}
		
		return null;
	}
	/**
	 * Retrieves information for the chosen business from the business table
	 * Returns the values as a ResultSet object
	 * Currently only works properly if there is only one business of that name in the table
	 */
	public ResultSet getBusinessInfo(String business){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			
			PreparedStatement stat = con.prepareStatement("SELECT businessName, description, address, monHours, tueHours," +
					"wedHours, thrHours, friHours, satHours, sunHours, rating, website FROM business WHERE businessName LIKE ?");
			//PreparedStatement stat = con.prepareStatement("SELECT * FROM business WHERE businessName LIKE ?");
			stat.setString(1, business);
			ResultSet r = stat.executeQuery();
			return r;
		}catch(Exception err){}
		
		return null;
	}
	/**
	 * Allows an owner to change all information about a business with name oName
	 * Currently all information must be entered at once
	 * Currently only works properly if there is only one business with the name in the table
	 */
	public void updateBusiness(String oName, String email, String business, String des, String address, String web, String m, String t, String w, String r, String f, String s, String u){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			
			PreparedStatement stat = con.prepareStatement("UPDATE business SET businessName=?, description=?, address=?, monHours=?, tueHours=?," +
					" wedHours=?, thrHours=?, friHours=?, satHours=?, sunHours=?, website=? WHERE businessName LIKE ?");
			stat.setString(1, business);
			stat.setString(2, des);
			stat.setString(3, address);
			stat.setString(4, m);
			stat.setString(5, t);
			stat.setString(6, w);
			stat.setString(7, r);
			stat.setString(8, f);
			stat.setString(9, s);
			stat.setString(10, u);
			stat.setString(11, web);
			stat.setString(12, oName);
			stat.executeUpdate();
		}catch(Exception err){}
	}
	/**
	 * Allows a user to enter a row with comment and rating into the users table
	 * Also, updates the business' rating in the business table
	 */
	public void rate(int rate, String business){
		Socket requestSocket;
		OutputStream outToServer;
		try{
			requestSocket = new Socket("128.10.25.101",33766);
			outToServer = requestSocket.getOutputStream();
			DataOutputStream out =new DataOutputStream(outToServer);
			out.writeUTF("RATE "+business+" "+rate);
			requestSocket.close();
			
		}catch(Exception err){}
		

	}
}
