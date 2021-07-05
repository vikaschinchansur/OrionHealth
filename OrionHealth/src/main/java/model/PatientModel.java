package model;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.Patient;

public class PatientModel {
	public String insertPatientDetails(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flag = null;
		String emailID = null;

		try {
			emailID = request.getParameter("emailID");
			if (isValid(emailID))
	            System.out.print("Yes its a valid emailID");
	        else {
	        	System.out.print("No!!!!!!!!! Not a valid emailID");
	        	flag = "fail";
	        }
			Patient pat = new Patient();
			flag = pat.InsertPatient(connection, request, response);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return flag;
	}
	
	public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	public String recordPatientMedication(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String flag = null;
		try {
			Patient pat = new Patient();
			flag = pat.InsertPatientMedication(connection, request, response);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return flag;
	}
	
	public JSONArray loadPatientMedicineList(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONArray medJSONArray = null;
		try {
			Patient pat = new Patient();
			medJSONArray = pat.patientMedicineListDBCall(connection, request, response);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return medJSONArray;
	}
	
	public JSONArray fetchPatientMedicineIDRecord(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONArray medJSONArray = null;
		try {
			Patient pat = new Patient();
			medJSONArray = pat.fetchPatientMedicineIDRecordDBCall(connection, request, response);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return medJSONArray;
	}

}
