package kurs.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import kurs.carrental.services.VehiclesService;
import lombok.extern.log4j.Log4j2;

/**
 * Servlet implementation class DebugDemoServlet
 */
@WebServlet("/DebugDemoServlet")
@Log4j2
public class DebugDemoServlet extends HttpServlet {

	// fields(attributes, instance variables)
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static int cnt = 0;
	private ConnectionInterface connect = new ConnectionRegular();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// constructors
	public DebugDemoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	// servlet methods
	public void init(ServletConfig config) throws ServletException {
		cnt++;
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		cnt--;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// setting the content type
		response.setContentType("text/html");

		// get the stream to write the data
		try (PrintWriter pw = response.getWriter()) {
			VehiclesService service = new VehiclesService();
			Integer categoryId = null;
			Integer officeId = null;
			Integer status = null;
			Integer manufacturer = null;

			VehiclesService.connect(connect);
			List<Vehicles> vehicles = service.searchVehicles(categoryId, officeId, status, manufacturer);
			for (Vehicles vehicle : vehicles) {
				log.info(vehicle);
			}

			pw.println("<!DOCTYPE html><html><body>");
			pw.println("<p>Registration Number: </p>");
			pw.println("<select name=\"bookingstatus\">");
			for (Vehicles vehicle : vehicles) {
				pw.println(String.format("<option value=\"%s\">%s</option>", vehicle.getVehicleID(),
						vehicle.getRegNumber()));
			}
			pw.println("</select>");
			pw.println("</body></html>");
		} catch (SQLException e) {
			log.error("Error with SQL Database! Please try later!");
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
