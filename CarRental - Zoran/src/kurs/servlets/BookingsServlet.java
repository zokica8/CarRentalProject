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

import kurs.carrental.beans.Office;
import kurs.carrental.beans.Status;
import kurs.carrental.beans.VehicleCategory;
import kurs.carrental.services.BookingsService;
import lombok.extern.log4j.Log4j2;

/**
 * Servlet implementation class BookingsServlet
 */
@WebServlet("/BookingsServlet")
@Log4j2
public class BookingsServlet extends HttpServlet {
	
	// fields(attributes, instance variables)
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}
	
	// helper methods 
	protected List<Office> getOfficeNames() throws SQLException {
		BookingsService service = new BookingsService();
		return service.getOffices("OfficeID", "Name", "office");
	}
	
	protected List<VehicleCategory> returnCategories() throws SQLException {
		BookingsService service = new BookingsService();
		return service.getCategories("CategoryID", "Description", "vehiclecategory");
	}
	
	protected List<Status> getStatuses() throws SQLException {
		BookingsService service = new BookingsService();
		return service.getAllStatuses("StatusID", "Status", "vehiclestatus");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// setting content type 
		response.setContentType("text/html");
		
		// get the stream to write the data
		try(PrintWriter pw = response.getWriter()) {

			pw.println("<!DOCTYPE html><html><body>");
			pw.println("<form method=\"post\" action=\"FormSuccessServlet\">");
			pw.println("<p>Driver Name:</p>");
			pw.println("<input type=\"text\" name=\"driverName\">");
			pw.println("<p>Driver Age:</p>");
			pw.println("<input type=\"number\" name=\"age\" min=\"18\" max=\"100\">");
			pw.println("<p>Driver Email:</p>");
			pw.println("<input type=\"text\" name=\"driverEmail\">");
			pw.println("<p>Driver Phone:</p>");
			pw.println("<input type=\"text\" name=\"driverPhone\">");
		    pw.println("<p>Pick Office:</p>");
		    pw.println("<select name=\"pickOffice\">");
		    for(Office office: getOfficeNames()) {
		    	pw.println(String.format("<option value=\"%s\">%s</option>",
		    			office.getOfficeID(), office.getName()));
		    }
		    pw.println("</select>");
		    pw.println("<br>");
		    pw.println("<p>Drop Office:</p>");
		    pw.println("<select name=\"dropOffice\">");
		    for(Office office: getOfficeNames()) {
		    	pw.println(String.format("<option value=\"%s\">%s</option>",
		    			office.getOfficeID(), office.getName()));
		    }
		    pw.println("</select>");
		    pw.println("<p>Date From:</p>");
		    pw.println("<input type=\"datetime-local\" value=\"2019-01-01T00:00:00\" step=\"1\" name=\"dateFrom\"");
		    pw.println("<br>");
		    pw.println("<p>Date To:</p>");
		    pw.println("<input type=\"datetime-local\" value=\"2019-01-01T00:00:00\" step=\"1\" name=\"dateTo\"");
		    pw.println("<br>");
		    pw.println("<p>Vehicle Category: </p>");
		    pw.println("<select name=\"vehicleCategory\">");
		    for(VehicleCategory category: returnCategories()) {
		    	pw.println(String.format("<option value=\"%s\">%s</option>",
		    			category.getCategoryID(), category.getDescription()));
		    }
		    pw.println("</select>");
		    pw.println("<p>Status: </p>");
		    pw.println("<select name=\"status\">");
		    for(Status status : getStatuses()) {
		    	pw.println(String.format("<option value=\"%s\">%s</option>", 
		    			status.getStatusID(), status.getStatus()));
		    }
		    pw.println("</select>");
		    pw.println("<p>Total Price: </p>");
		    pw.println("<input type=\"text\" name=\"totalPrice\">");
		    pw.println("<p><input type=\"submit\" value=\"Submit\"></p>");
			pw.println("</form>");
			pw.println("</body></html>");
		} catch (SQLException e) {
			log.error("Error with SQL Database! Please try later!");
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	

}
