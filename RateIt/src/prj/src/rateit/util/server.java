package prj.src.rateit.util;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class server {
	final static int port =7755;
	
	 public static void main(String[] args ){  
	      try{  
	         int i = 1;
	         ServerSocket s = new ServerSocket(port);
		 while (true){ 
	            Socket incoming = s.accept();
	            System.out.println("Spawning " + i);
	            Runnable r = new ThreadedHandler(incoming);
	            Thread t = new Thread(r);
	            t.start();
	            i++;
	         }
	      }catch (IOException e){  
	         e.printStackTrace();
	      }
	   }
}

class ThreadedHandler implements Runnable
{ 
   final static String ServerUser = "test";
   final static String ServerPassword = "hama";

   public ThreadedHandler(Socket i)
   { 
      incoming = i; 
   }

   public static Connection getConnection() throws SQLException, IOException{
    /*  Properties props = new Properties();
      FileInputStream in = new FileInputStream("database.properties");
      props.load(in);
      in.close();
      String drivers = props.getProperty("jdbc.drivers");
      if (drivers != null)
        System.setProperty("jdbc.drivers", drivers);
      String url = props.getProperty("jdbc.url");
      String username = props.getProperty("jdbc.username");
      String password = props.getProperty("jdbc.password");
*/ 
  //  System.out.println("url="+url+" user="+username+" password="+password);

/*try{
     // Connection l = DriverManager.getConnection( url, username, password);
      }catch(SQLException e){
      System.out.println("Le Rue");
	}*/
	System.out.println("Le Rue");
       return DriverManager.getConnection("jdbc:mysql://128.10.25.101:33066/pets", "test", "hama");
 //	return DriverManager.getConnection("jdbc:mysql://127.0.0.1:33066/pets", "test", "hama");
}

   void test(String [] args, PrintWriter out){
   	Connection con=null;
	try{
		con = getConnection();
	
		PreparedStatement stat = con.prepareStatement("");
		stat.setString(1, args[3]);
		stat.setString(2, args[4]);
		stat.setString(3, args[5]);
		stat.setString(4, args[6]);
		stat.executeUpdate();
	
		System.out.println("It should have worked");
	} catch (Exception e){
		System.out.println("FOOL!");
		System.out.println(e.toString());
		out.println(e.toString());

	}
   }
   
   void handleRequest( InputStream inStream, OutputStream outStream) {
        Scanner in = new Scanner(inStream);         
        PrintWriter out = new PrintWriter(outStream, 
                                      true /* autoFlush */);

	// Get parameters of the call
	String request = in.nextLine();

	System.out.println("Request="+request);

	String requestSyntax = "Syntax: COMMAND|test|hama|OTHER|ARGS";

	try {
		// Get arguments.
		// The format is COMMAND|USER|PASSWORD|OTHER|ARGS...
		String [] args = request.split("\\|");

		// Print arguments
		for (int i = 0; i < args.length; i++) {
			System.out.println("Arg "+i+": "+args[i]);
		}

		// Get command and password
		String command = args[0];
		String user = args[1];
		String password = args[2];

		// Check user and password. Now it is sent in plain text.
		// You should use Secure Sockets (SSL) for a production environment.
		/*if ( !user.equals(ServerUser) || !password.equals(ServerPassword)) {
			System.out.println("Bad user or password");
			out.println("Bad user or password");
			return;
		}*/

		// Do the operation
		if (command.equals("testing")){
			out.println("Ok");
		}
		
	}
	catch (Exception e) {		
		System.out.println(requestSyntax);
		out.println(requestSyntax);

		System.out.println(e.toString());
		out.println(e.toString());
	}
   }

   public void run() {  
      try
      { 

         try
         {
            InputStream inStream = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream();
	    handleRequest(inStream, outStream);

         }
      	 catch (IOException e)
         {  
            e.printStackTrace();
         }
         finally
         {
            incoming.close();
         }
      }
      catch (IOException e)
      {  
         e.printStackTrace();
      }
   }

   private Socket incoming;
}