package controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;
import model.PatientModel;

/**
 * Servlet implementation class SignUpPatient
 */
@WebServlet("/SignUpPatient")
public class SignUpPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpPatient() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Connection connection = null;
		Database database = null;
		PatientModel pm = null;
		try {
			database = new Database();
			pm = new PatientModel();
			connection = database.getConnection();
			System.out.println("Connection=="+connection);
			String flag = pm.insertPatientDetails(connection, request, response);
			if(flag!=null) {
				out.println(flag);
			}
			
		}
		catch (Exception ex)
		{
			out.println("Exception: " + ex.getMessage());
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
