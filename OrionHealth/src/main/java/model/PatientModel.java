package model;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.Patient;

public class PatientModel {
	public JSONArray insertPatientDetails(Connection connection, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String emailID = null;
		JSONArray jsonArray = null;

		try {
			emailID = request.getParameter("emailID");
			if (isValid(emailID)) {
				Patient pat = new Patient();
				jsonArray = pat.checkIfEmailIDAlreadyExistsInDB(connection, request, response);
				if(jsonArray.getJSONObject(0).get("flag")=="success") {
					jsonArray = pat.InsertPatient(connection, request, response);
				}
			} else {
				jsonArray = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("flag","fail");
				jsonObj.put("flagMessage","Email ID "+emailID+" is invalid! Please enter valid Email ID.");
		        jsonArray.put(jsonObj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return jsonArray;
	}

	public static boolean isValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

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
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return medJSONArray;
	}
	
	public JSONArray loginPatient(Connection connection, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String emailID = null;
		JSONArray jsonArray = null;

		try {
			emailID = request.getParameter("emailID");
			if (isValid(emailID)) {
				Patient pat = new Patient();
				jsonArray = pat.verifyCredentials(connection, request, response);
				if(jsonArray.getJSONObject(0).get("flag")=="success") {
					jsonArray = pat.fetchPatientDetails(connection, request, response);
				}
			} else {
				jsonArray = new JSONArray();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("flag","fail");
				jsonObj.put("flagMessage","Email ID "+emailID+" is invalid! Please enter valid Email ID.");
		        jsonArray.put(jsonObj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return jsonArray;
	}

}
