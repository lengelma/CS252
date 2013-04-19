package prj.src.rateit.util;

import java.sql.*;


public class DatabaseAPI {
	
	/**
	 *Adds a new owner to the database
	 *returns 1 if successful, -1 if not 
	 */
	public int addOwner(){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://blahblahblah", "root", "");
			//TODO STUFF
			con.createStatement().execute("INSERT INTO `` (`name`, `address`) VALUES ('Bob', '123 Fake Street')");

			
			
		}catch(Exception err){}
		
		return 0;
	}
	
	/**
	 *Adds a new Buisness to the database
	 *returns the new Buisness's id if successful
	 *-1 if not. 
	 */
	public int addBuisness(){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://blahblahblah", "root", "");
			//TODO STUFF
			con.createStatement().execute("INSERT INTO `people` (`name`, `address`) VALUES ('Bob', '123 Fake Street')");
			
			
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
			Connection con =  DriverManager.getConnection("jdbc:mysql://blahblahblah", "root", "");
			//TODO STUFF
			
			
			
		}catch(Exception err){}

		
		
		return false;
	}
	
	/**
	 *Rates a buisness
	 *Returns 1 if successful, -1 if not 
	 */
	public int rateBuisness(){
		try{
			Class.forName("com.gjt.mm.mysql.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://blahblahblah", "root", "");
			//TODO STUFF
			con.createStatement().execute("INSERT INTO `people` (`name`, `address`) VALUES ('Bob', '123 Fake Street')");
			
			
		}catch(Exception err){}

		
		
		return 0;
	}
	
	
}
