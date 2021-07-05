package controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dao.Database;
import model.PatientModel;

/**
 * Servlet implementation class RecordPatientMedication
 */
@WebServlet("/RecordPatientMedication")
public class RecordPatientMedication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordPatientMedication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(">>>>> GET request received");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(">>>>> POST request received");
		//doGet(request, response);
		
		Connection connection = null;
		Database database = null;
		PatientModel pm = null;
		PrintWriter out = null;
		
		try {
			database = new Database();
			pm = new PatientModel();
			connection = database.getConnection();
			System.out.println("Connection=="+connection);
			String flag = pm.recordPatientMedication(connection, request, response);
			System.out.println("flag=="+flag);
			out = response.getWriter();
	        response.setContentType("application/json");
	        response.setHeader("Cache-control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	 
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.setHeader("Access-Control-Max-Age", "86400");
	        
			/*
			 * Gson gson = new Gson(); JsonObject myObj = new JsonObject(); JsonElement
			 * myElement = null;
			 * 
			 * if(flag == null){ myObj.addProperty("success", false); } else {
			 * myObj.addProperty("success", true); myElement =
			 * gson.toJsonTree(flag.toString()); }
			 * 
			 * myObj.add("flagObj", myElement); out.println(myObj.toString());
			 */
	        
	        JSONArray array = new JSONArray();
	        
	        JSONObject medicine = new JSONObject();
	        
	        
	        medicine.put("flag", flag);
	        medicine.put("name", request.getParameter("medicineName"));
	        medicine.put("dose", request.getParameter("dose"));
	        array.put(medicine);
	        System.out.println("array.toString()=="+array.toString());
	        out.write(array.toString());
			
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex.getMessage());
		}
		finally
		{
			out.close();
			if (connection!=null) {
				database.closeConnection(connection);
			}
		}
	}

}
