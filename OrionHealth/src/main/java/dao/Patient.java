package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class Patient {

	public JSONArray InsertPatient(Connection connection, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String query = "{ call CREATE_PATIENT(?,?,?,?,?,?,?,?) }";
		String firstName = null;
		String lastName = null;
		String emailID = null;
		String password = null;
		String address = null;
		String phone = null;
		int insuranceID;
		String flag = null;
		JSONArray jsonArray = new JSONArray();

		ResultSet rs;
		try {
			CallableStatement stmt = connection.prepareCall(query);
			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName");
			emailID = request.getParameter("emailID");
			password = request.getParameter("password");
			address = request.getParameter("address");
			phone = request.getParameter("phone");
			insuranceID = Integer.parseInt(request.getParameter("insuranceID"));
			password = request.getParameter("password");

			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, emailID);
			stmt.setString(4, password);
			stmt.setString(5, address);
			stmt.setString(6, phone);
			stmt.setInt(7, insuranceID);
			stmt.registerOutParameter(8, Types.VARCHAR);
			stmt.executeUpdate();
			flag = stmt.getString(8);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("flag",flag);
	        jsonArray.put(jsonObj);
	        
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
		return jsonArray;
	}

	public String InsertPatientMedication(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String query = "{ call CREATE_PATIENT_MEDICATION_RECORD(?,?,?,?,?,?,?,?,?,?,?,?) }";
		int patientId;
		String medicineName = null;
		String unit = null;
		int dose;
		String flag = null;
		
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

		

		ResultSet rs;
		try {
			CallableStatement stmt = connection.prepareCall(query);
			patientId = Integer.parseInt(request.getParameter("patientId"));
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

			stmt.setInt(1, patientId);
			stmt.setString(2, medicineName);
			stmt.setInt(3, dose);
			stmt.setString(4, unit);
			stmt.setString(5, mon);
			stmt.setString(6, tue);
			stmt.setString(7, wed);
			stmt.setString(8, thu);
			stmt.setString(9, fri);
			stmt.setString(10, sat);
			stmt.setString(11, sun);
			stmt.registerOutParameter(12, Types.VARCHAR);
			stmt.executeUpdate();
			flag = stmt.getString(12);
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
		return flag;
	}

	public JSONArray patientMedicineListDBCall(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String patientID;
		PreparedStatement pst = null;

		ResultSet rs;
		JSONArray medJSONArray = new JSONArray();
		
		try {
			patientID = request.getParameter("patientID");
			String query = "select medicine_id, medicine_name from patient_medication where patient_id=" + patientID;
			pst = connection.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				int medicineId = rs.getInt("medicine_id");
				String medicineName = rs.getString("medicine_name");
		        JSONObject medicineObj = new JSONObject();
		        medicineObj.put("medicineId", medicineId);
		        medicineObj.put("medicineName", medicineName);
		        medJSONArray.put(medicineObj);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (pst != null) {
				pst.close();
			}
		}
		return medJSONArray;
	}
	
	public JSONArray fetchPatientMedicineIDRecordDBCall(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String patientID;
		String medicineId;
		PreparedStatement pst = null;

		ResultSet rs;
		JSONArray medJSONArray = new JSONArray();
		
		try {
			patientID = request.getParameter("patientID");
			medicineId = request.getParameter("medicineId");
			String query = "select * from patient_medication where patient_id="+patientID+" and medicine_id="+medicineId;
			pst = connection.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				int medId = rs.getInt("medicine_id");
				int pId = rs.getInt("patient_id");
				String medicineName = rs.getString("medicine_name");
				int dose = rs.getInt("dose");
				String unit = rs.getString("unit");
				
				String mon = rs.getString("mon");
				String tue = rs.getString("tue");
				String wed = rs.getString("wed");
				String thu = rs.getString("thu");
				String fri = rs.getString("fri");
				String sat = rs.getString("sat");
				String sun = rs.getString("sun");

				JSONObject medicineObj = new JSONObject();
		        medicineObj.put("patientID", pId);
		        medicineObj.put("medicineId", medId);
		        medicineObj.put("medicineName", medicineName);
		        medicineObj.put("dose", dose);
		        medicineObj.put("unit", unit);
		        
		        medicineObj.put("mon", mon);
		        medicineObj.put("tue", tue);
		        medicineObj.put("wed", wed);
		        medicineObj.put("thu", thu);
		        medicineObj.put("fri", fri);
		        medicineObj.put("sat", sat);
		        medicineObj.put("sun", sun);
		        
		        medJSONArray.put(medicineObj);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (pst != null) {
				pst.close();
			}
		}
		return medJSONArray;
	}
	
	public JSONArray checkIfEmailIDAlreadyExistsInDB(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String emailID;
		PreparedStatement pst = null;
		ResultSet rs;
		JSONArray jsonArray = new JSONArray();
		
		try {
			emailID = request.getParameter("emailID");
			String query = "select COUNT(*) as count from patient where email='"+emailID+"'";
			pst = connection.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
			    int count = rs.getInt("count");
			    if (count >= 1) {
			        JSONObject jsonObj = new JSONObject();
					jsonObj.put("flag","fail");
					jsonObj.put("flagMessage","Email ID "+emailID+" already Exists! Choose different Email ID to Sign Up.");
			        jsonArray.put(jsonObj);
			    } else {
			        JSONObject jsonObj = new JSONObject();
					jsonObj.put("flag","success");
			        jsonArray.put(jsonObj);
			    }
			}
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (pst != null) {
				pst.close();
			}
		}
		return jsonArray;
	}
	
	public JSONArray verifyCredentials(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String emailID;
		String password;
		PreparedStatement pst = null;
		ResultSet rs;
		JSONArray jsonArray = new JSONArray();
		
		try {
			emailID = request.getParameter("emailID");
			password = request.getParameter("password");
			String query = "select COUNT(*) as count from patient where email='"+emailID+"' and password='"+password+"'";
			pst = connection.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
			    int count = rs.getInt("count");
			    if (count >= 1) {
			        JSONObject jsonObj = new JSONObject();
					jsonObj.put("flag","success");
					jsonObj.put("flagMessage","Login Successful.");
			        jsonArray.put(jsonObj);
			    } else {
			        JSONObject jsonObj = new JSONObject();
					jsonObj.put("flag","fail");
					jsonObj.put("flagMessage","Invalid Credentials!");
			        jsonArray.put(jsonObj);
			    }
			}
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (pst != null) {
				pst.close();
			}
		}
		return jsonArray;
	}
	
	public JSONArray fetchPatientDetails(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String emailID;
		PreparedStatement pst = null;
		ResultSet rs;
		JSONArray jsonArray = new JSONArray();
		
		try {
			emailID = request.getParameter("emailID");
			String query = "select * from patient where email='"+emailID+"'";
			pst = connection.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				int patientId = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				int insuranceId = rs.getInt("insurance_id");
				Date dateCreated = rs.getDate("date_created");
				JSONObject jsonObj = new JSONObject();
		        jsonObj.put("patientId", patientId);
		        jsonObj.put("firstname", firstname);
		        jsonObj.put("lastname", lastname);
		        jsonObj.put("email", email);
		        jsonObj.put("password", password);
		        jsonObj.put("address", address);
		        jsonObj.put("phone", phone);
		        jsonObj.put("insuranceId", insuranceId);
		        jsonObj.put("dateCreated", dateCreated);
		        jsonObj.put("flag", "success");
		        jsonObj.put("flagMessage", "Patient Details Loaded.");
		        
		        jsonArray.put(jsonObj);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (pst != null) {
				pst.close();
			}
		}
		return jsonArray;
	}
}
