package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Patient {

	public String InsertPatient(Connection connection, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String query = "{ call CREATE_PATIENT(?,?,?,?,?,?,?) }";
		String firstName = null;
		String lastName = null;
		String emailID = null;
		String address = null;
		String phone = null;
		int insuranceID;
		String flag = null;
	
        ResultSet rs;
		try {
			CallableStatement stmt = connection.prepareCall(query);
			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName");
			emailID = request.getParameter("emailID");
			address = request.getParameter("address");
			phone = request.getParameter("phone");
			insuranceID = Integer.parseInt(request.getParameter("insuranceID"));
			
			System.out.println("firstName=="+firstName);
			System.out.println("lastName=="+lastName);
			System.out.println("emailID=="+emailID);
			System.out.println("address=="+address);
			System.out.println("phone=="+phone);
			System.out.println("insuranceID=="+insuranceID);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, emailID);
			stmt.setString(4, address);
			stmt.setString(5, phone);
			stmt.setInt(6, insuranceID);
			stmt.registerOutParameter(7, Types.VARCHAR);
			stmt.executeUpdate();
			System.out.println("FLAG===="+stmt.getString(7));
			flag = stmt.getString(7);	
		}
		catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
		return flag;
	}
	
	public String InsertPatientMedication(Connection connection, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String query = "{ call CREATE_PATIENT_MEDICATION_RECORD(?,?,?,?,?,?,?,?,?,?,?) }";
		String medicineName = null;
		String unit = null;
		String monBreakfast, monLunch, monDinner, monBedTime;
		monBreakfast = monLunch = monDinner = monBedTime = null;
		
		
		
		String tueBreakfast, tueLunch, tueDinner, tueBedTime;
		tueBreakfast = tueLunch = tueDinner = tueBedTime = null;
		
		String wedBreakfast, wedLunch, wedDinner, wedBedTime;
		wedBreakfast = wedLunch = wedDinner = wedBedTime = null;
		
		String thuBreakfast, thuLunch, thuDinner, thuBedTime;
		thuBreakfast = thuLunch = thuDinner = thuBedTime = null;
		
		String friBreakfast, friLunch, friDinner, friBedTime;
		friBreakfast = friLunch = friDinner = friBedTime = null;
		
		String satBreakfast, satLunch, satDinner, satBedTime;
		satBreakfast = satLunch = satDinner = satBedTime = null;
		
		String sunBreakfast, sunLunch, sunDinner, sunBedTime;
		sunBreakfast = sunLunch = sunDinner = sunBedTime = null;
		
		int dose;
		String flag = null;
	
        ResultSet rs;
		try {
			CallableStatement stmt = connection.prepareCall(query);
			medicineName = request.getParameter("medicineName");
			dose = Integer.parseInt(request.getParameter("dose"));
			unit = request.getParameter("unit");
			
			monBreakfast = request.getParameter("monBreakfast");
			monLunch = request.getParameter("monLunch");
			monDinner = request.getParameter("monDinner");
			monBedTime = request.getParameter("monBedTime");
			
			tueBreakfast = request.getParameter("tueBreakfast");
			tueLunch = request.getParameter("tueLunch");
			tueDinner = request.getParameter("tueDinner");
			tueBedTime = request.getParameter("tueBedTime");
			
			wedBreakfast = request.getParameter("wedBreakfast");
			wedLunch = request.getParameter("wedLunch");
			wedDinner = request.getParameter("wedDinner");
			wedBedTime = request.getParameter("wedBedTime");
			
			thuBreakfast = request.getParameter("thuBreakfast");
			thuLunch = request.getParameter("thuLunch");
			thuDinner = request.getParameter("thuDinner");
			thuBedTime = request.getParameter("thuBedTime");
			
			friBreakfast = request.getParameter("friBreakfast");
			friLunch = request.getParameter("friLunch");
			friDinner = request.getParameter("friDinner");
			friBedTime = request.getParameter("friBedTime");
			
			satBreakfast = request.getParameter("satBreakfast");
			satLunch = request.getParameter("satLunch");
			satDinner = request.getParameter("satDinner");
			satBedTime = request.getParameter("satBedTime");
			
			sunBreakfast = request.getParameter("sunBreakfast");
			sunLunch = request.getParameter("sunLunch");
			sunDinner = request.getParameter("sunDinner");
			sunBedTime = request.getParameter("sunBedTime");
			
			String mon = String.join(",", monBreakfast, monLunch, monDinner, monBedTime);
			String tue = String.join(",", tueBreakfast, tueLunch, tueDinner, tueBedTime);
			String wed = String.join(",", wedBreakfast, wedLunch, wedDinner, wedBedTime);
			String thu = String.join(",", thuBreakfast, thuLunch, thuDinner, thuBedTime);
			String fri = String.join(",", friBreakfast, friLunch, friDinner, friBedTime);
			String sat = String.join(",", satBreakfast, satLunch, satDinner, satBedTime);
			String sun = String.join(",", sunBreakfast, sunLunch, sunDinner, sunBedTime);
			
			System.out.println("medicineName=="+medicineName);
			System.out.println("dose=="+dose);
			System.out.println("unit=="+unit);
			System.out.println("mon=="+mon);
			System.out.println("tue=="+tue);
			System.out.println("tue=="+wed);
			System.out.println("wed=="+thu);
			System.out.println("fri=="+fri);
			System.out.println("sat=="+sat);
			System.out.println("sun=="+sun);
			
			
			  stmt.setString(1, medicineName);
			  stmt.setInt(2, dose);
			  stmt.setString(3, unit); 
			  stmt.setString(4,mon); 
			  stmt.setString(5, tue); 
			  stmt.setString(6, wed);
			  stmt.setString(7, thu);
			  stmt.setString(8, fri);
			  stmt.setString(9, sat);
			  stmt.setString(10, sun);
			  stmt.registerOutParameter(11, Types.VARCHAR);
			  stmt.executeUpdate(); 
			  System.out.println("FLAG===="+stmt.getString(11)); 
			  flag = stmt.getString(11);
		}
		catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
		return flag;
	}
}
