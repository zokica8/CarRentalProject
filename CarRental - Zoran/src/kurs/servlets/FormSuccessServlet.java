package kurs.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kurs.carrental.beans.Bookings;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import kurs.carrental.services.BookingsService;
import lombok.extern.log4j.Log4j2;

/**
 * Servlet implementation class FormSuccessServlet
 */
@WebServlet("/FormSuccessServlet")
@Log4j2
public class FormSuccessServlet extends HttpServlet {
	
	// fields (attributes, instance variables)
	private static final long serialVersionUID = 1L;
	private ConnectionInterface connect = new ConnectionRegular();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormSuccessServlet() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Driver Name: ").append(request.getParameter("driverName"));
		response.getWriter().append("Driver Age: ").append(request.getParameter("age"));
		response.getWriter().append("Driver Email: ").append(request.getParameter("driverEmail"));
		response.getWriter().append("Driver Phone: ").append(request.getParameter("driverPhone"));
		response.getWriter().append("Pick Office: ").append(request.getParameter("pickOffice"));
		response.getWriter().append("Drop Office: ").append(request.getParameter("dropOffice"));
		response.getWriter().append("Date From: ").append(request.getParameter("dateFrom"));
		response.getWriter().append("Date To: ").append(request.getParameter("dateTo"));
		response.getWriter().append("Vehicle Category: ").append(request.getParameter("vehicleCategory"));
		response.getWriter().append("Status: ").append(request.getParameter("status"));
		response.getWriter().append("Total Price: ").append(request.getParameter("totalPrice"));
		
		log.info(request.getParameter("driverName"));
		log.info(request.getParameter("age"));
		log.info(request.getParameter("driverEmail"));
		log.info(request.getParameter("driverPhone"));
		log.info(request.getParameter("pickOffice"));
		log.info(request.getParameter("dropOffice"));
		log.info(request.getParameter("dateFrom"));
		log.info(request.getParameter("dateTo"));
		log.info(request.getParameter("vehicleCategory"));
		log.info(request.getParameter("status"));
		log.info(request.getParameter("totalPrice"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			BookingsService service = new BookingsService();
			Bookings booking = new Bookings();
			BookingsService.connect(connect);
			
			// you don't have to use formatter!!
			//String pattern = "yyyy-MM-dd HH:mm:ss";
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
			
			booking.setDriverName(request.getParameter("driverName"));
			booking.setDriverAge(Integer.parseInt(request.getParameter("age")));
			booking.setDriverEmail(request.getParameter("driverEmail"));
			booking.setDriverPhone(request.getParameter("driverPhone"));
			booking.setPickupOfficeID(Integer.parseInt(request.getParameter("pickOffice")));
			booking.setDropoffOfficeID(Integer.parseInt(request.getParameter("dropOffice")));
			booking.setPickupTime(LocalDateTime.parse(request.getParameter("dateFrom")));
			booking.setDropoffTime(LocalDateTime.parse(request.getParameter("dateTo")));
			booking.setVehicleCategoryID(Integer.parseInt(request.getParameter("vehicleCategory")));
			booking.setStatusID(Integer.parseInt(request.getParameter("status")));
			booking.setPrice(Double.parseDouble(request.getParameter("totalPrice")));
			
			service.insertBooking(booking);
			
			response.getWriter().format("Booking %s inserted", booking);
			
		} catch (SQLException e) {
			log.error("Error with SQL database: " + e.getLocalizedMessage());
			log.error(e.getSQLState() + e.getErrorCode());
			e.printStackTrace();
		}
	}

}
