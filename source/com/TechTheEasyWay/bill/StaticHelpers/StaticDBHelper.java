package com.TechTheEasyWay.bill.StaticHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class StaticDBHelper {
	
	
	public enum BillApplicationDB{ 
		USER("bill_application_user"),
		PASSWORD("p@ssword01"),
		URL("jdbc:mysql://localhost:3306/bill_application");
		
		private String strParamValue;
	    private BillApplicationDB(final String p_strParamValue){
	    	strParamValue = p_strParamValue;
	    }
	    
	    public String getValue(){
	    	return strParamValue;
	    }
	    
	    public static Connection getConnection(){
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
	 
				return DriverManager.getConnection(URL.getValue(), USER.getValue(), PASSWORD.getValue());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
	    }
	}
}
