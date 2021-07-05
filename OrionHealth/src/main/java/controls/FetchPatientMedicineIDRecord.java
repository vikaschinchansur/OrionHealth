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

import dao.Database;
import model.PatientModel;

/**
 * Servlet implementation class FetchPatientMedicineIDRecord
 */
@WebServlet("/FetchPatientMedicineIDRecord")
public class FetchPatientMedicineIDRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchPatientMedicineIDRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside POST Method of PatientMedicineList");
		// doGet(request, response);

		Connection connection = null;
		Database database = null;
		PatientModel pm = null;
		PrintWriter out = null;

		try {
			database = new Database();
			pm = new PatientModel();
			connection = database.getConnection();
			System.out.println("Connection==" + connection);
			JSONArray medJSONArray = pm.fetchPatientMedicineIDRecord(connection, request, response);
			out = response.getWriter();
			response.setContentType("application/json");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");

			System.out.println("medJSONArray.toString()==" + medJSONArray.toString());
			out.write(medJSONArray.toString());

		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		} finally {
			if(out!=null) {
				
			}
			if (connection != null) {
				database.closeConnection(connection);
			}
		}
	}

}
