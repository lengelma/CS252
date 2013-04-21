package prj.src.rateit.util;

import java.sql.*;
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
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			//TODO STUFF
			//con.createStatement().execute("INSERT INTO `` (`name`, `address`) VALUES ('Bob', '123 Fake Street')");

			PreparedStatement stat = con.prepareStatement("INSERT INTO owners (email,password,firstName,lastName) VALUES(?,?,?,?)");
			stat.setString(1, email);
			stat.setString(2, password);
			stat.setString(3, first);
			stat.setString(4, last);
			stat.executeUpdate();
			
		}catch(Exception err){}
		
		return 0;
	}
	
	/**
	 *Adds a new Buisness to the database
	 *returns the new Buisness's id if successful
	 *-1 if not. 
	 */
	public int addBuisness(String email, String business, String des, String address, String web, String m, String t, String w, String r, String f, String s, String u){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			//TODO STUFF
			//con.createStatement().execute("INSERT INTO `people` (`name`, `address`) VALUES ('Bob', '123 Fake Street')");
			PreparedStatement stat = con.prepareStatement("INSERT INTO business (email, businessName, description, address, monHours, tueHours, wedHours, thrHours, friHours, " +
					"satHours, sunHours, rating, website, numberOfRates) VALUES (?,?,?,?,?,?,?,?,?,?,?,0,?,0)");
			stat.setString(1, email);
			stat.setString(2, business);
			stat.setString(3, des);
			stat.setString(4, address);
			stat.setString(5, m);
			stat.setString(6, t);
			stat.setString(7, w);
			stat.setString(8, r);
			stat.setString(9, f);
			stat.setString(10, s);
			stat.setString(11, u);
			stat.setString(12, web);
			stat.executeUpdate();
			
		}catch(Exception err){}

		
		
		return 0;
	}
	
	/**
	 *Checks a users credentials
	 *Returns true if successful
	 *Returns false if not 
	 */
	public boolean login(String user,String password){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			//TODO STUFF
			
			PreparedStatement stat = con.prepareStatement("SELECT password FROM owners WHERE email LIKE ?");
			stat.setString(1, user);
			ResultSet r = stat.executeQuery();
			
			//check to see if this is valid or not, the idea is if there's not match, skip to false
			//pretty sure it's right, still test
			
			while(r.next()){
				if(password.equals(r.getString(1)))
					return true;
			}
			return false;
			
			//better version
			/*if(r.next()){
				if(password.equals(r.getString(1)))
					return true;
				else
					return false;
			}else
				return false;*/
			
		}catch(Exception err){}

		
		
		return false;
	}
	
	/**
	 *Rates a buisness
	 *Returns 1 if successful, -1 if not 
	 *Currently only works properly if there is only one business with businessName business in both the user and business table
	 */
	public int rateBuisness(Connection con, String business){
		try{
			//Class.forName("com.gjt.mm.mysql.Driver");
			//Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			//TODO STUFF
			//con.createStatement().execute("INSERT INTO `people` (`name`, `address`) VALUES ('Bob', '123 Fake Street')");
			PreparedStatement stat = con.prepareStatement("SELECT rating FROM users WHERE businessName LIKE ?");
			stat.setString(1, business);
			ResultSet r = stat.executeQuery();
			int counter = 0, x = 0;
			
			while(r.next()){
				counter += r.getInt(1);
			}
			
			stat = con.prepareStatement("SELECT numberOfRates FROM business WHERE businessName LIKE ?");
			stat.setString(1, business);
			r = stat.executeQuery();
			r.next();
			x = r.getInt(1)+1;
			counter = counter/x;
			stat = con.prepareStatement("UPDATE business SET rating=?, numberOfRates=? WHERE businessName LIKE ?");
			stat.setInt(1, counter);
			stat.setInt(2, x);
			stat.setString(3, business);
			stat.executeUpdate();
			
		}catch(Exception err){}

		
		
		return 0;
	}
	
	/**
	 *Checks to see if an email address already exists inside the owners table
	 *returns true if it exists 
	 *returns false otherwise
	 */
	public boolean emailExists(String email){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			
			PreparedStatement stat = con.prepareStatement("SELECT email FROM owners WHERE email LIKE ?");
			stat.setString(1, email);
			ResultSet r = stat.executeQuery();
			
			if(r.next())
				return true;
			else
				return false;
		}catch(Exception err){}
		
		return false;
	}
	
	/**
	 * Checks to see if a business with that name exists inside the business table
	 * returns true if it exists
	 * returns false otherwise
	 */
	public boolean businessExists(String business){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			
			PreparedStatement stat = con.prepareStatement("SELECT businessName FROM business WHERE businessName LIKE ?");
			stat.setString(1, business);
			ResultSet r = stat.executeQuery();
				
			if(r.next())
				return true;
			else
				return false;
		}catch(Exception err){}
		return false;
	}
	
	/**
	 * Gets the names of all businesses from the business table with a given email
	 * Returns a ResultSet object containing all names
	 */
	public ResultSet getOwnersBusiness(String email){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			
			PreparedStatement stat = con.prepareStatement("SELECT businessName FROM business WHERE email LIKE ?");
			stat.setString(1, email);
			ResultSet r = stat.executeQuery();
			return r;
		}catch(Exception err){}
		
		return null;
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
	public void commentRate(int rate, String comment, String business){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://128.10.2.13:33066/lab6v1", "user", "cs252");
			
			PreparedStatement stat = con.prepareStatement("INSERT INTO users (businessName, comment, rating) VALUES (?,?,?)");
			stat.setString(1, business);
			stat.setString(2, comment);
			stat.setInt(3, rate);
			stat.executeUpdate();
			rateBuisness(con, business);
		}catch(Exception err){}
		

	}
	public static void test(){
		try{
			
			System.out.println("IM DOIN STUFF!\n");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://data.cs.purdue.edu:33066/lab6v1","user","cs252");
			PreparedStatement stat = con.prepareStatement("INSERT INTO users (email,password,firstName,lastName) VALUES ('lol@test.com','password','lee','engelman')");
			stat.executeUpdate();
			
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}
}
