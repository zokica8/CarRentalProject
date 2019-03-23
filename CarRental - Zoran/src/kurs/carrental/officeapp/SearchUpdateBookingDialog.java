package kurs.carrental.officeapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kurs.carrental.beans.Bookings;
import kurs.carrental.beans.Status;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import kurs.carrental.services.BookingsService;
import lombok.extern.log4j.Log4j2;

@SuppressWarnings("serial")
@Log4j2
public class SearchUpdateBookingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_6;
	private JLabel lblNewLabel;
	private JLabel lblUpdate;
	private JButton btnSearch;
	private Integer bookingID;
	private Status status;
	private JComboBox<Status> comboBox;
	private JComboBox<Status> comboBox_1;
	private ConnectionInterface connect = new ConnectionRegular();
	private JFormattedTextField formattedTextField_1;
	private JFormattedTextField formattedTextField;
	private DateTimeFormatter formatter;
	private LocalDateTime dateTime;
	private LocalDateTime dateTime1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchUpdateBookingDialog dialog = new SearchUpdateBookingDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SearchUpdateBookingDialog() {
		setTitle("Search Booking");
		setBounds(100, 100, 877, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setText("");
			textField.setColumns(10);
			textField.setBounds(178, 77, 120, 20);
			contentPanel.add(textField);
		}
		{
			textField_1 = new JTextField();
			textField_1.setText("");
			textField_1.setColumns(10);
			textField_1.setBounds(178, 125, 120, 20);
			contentPanel.add(textField_1);
		}
		{
			textField_6 = new JTextField();
			textField_6.setText("");
			textField_6.setColumns(10);
			textField_6.setBounds(619, 156, 96, 20);
			contentPanel.add(textField_6);
		}
		{
			JLabel label = new JLabel("Search By Keyword:");
			label.setBounds(35, 80, 120, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Booking ID:");
			label.setBounds(35, 128, 79, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblStatus = new JLabel("Status:");
			lblStatus.setBounds(35, 185, 79, 14);
			contentPanel.add(lblStatus);
		}
		{
			JLabel label = new JLabel("Pickup Time:");
			label.setFont(new Font("Tahoma", Font.PLAIN, 10));
			label.setBounds(35, 235, 79, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Dropoff Time:");
			label.setFont(new Font("Tahoma", Font.PLAIN, 10));
			label.setBounds(35, 290, 79, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Booking ID:");
			label.setBounds(481, 159, 96, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblStatus_1 = new JLabel("Status:");
			lblStatus_1.setBounds(481, 102, 79, 14);
			contentPanel.add(lblStatus_1);
		}
		{
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 855, 22);
			contentPanel.add(menuBar);
			{
				JMenu mnNew = new JMenu("Window");
				mnNew.setMnemonic(KeyEvent.VK_W);
				menuBar.add(mnNew);
				{
					JMenuItem mntmBackToMain = new JMenuItem("Exit");
					mntmBackToMain.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					mntmBackToMain.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
					mnNew.add(mntmBackToMain);
				}
			}
		}
		
		lblNewLabel = new JLabel("Search:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("MS Gothic", Font.ITALIC, 23));
		lblNewLabel.setBounds(52, 36, 236, 30);
		contentPanel.add(lblNewLabel);
		
		lblUpdate = new JLabel("Update:");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("MS Gothic", Font.ITALIC, 23));
		lblUpdate.setBounds(457, 36, 236, 30);
		contentPanel.add(lblUpdate);
		
		comboBox = new JComboBox<Status>();
		DefaultComboBoxModel<Status> comboModel = new DefaultComboBoxModel<>();
		String sql = "select * from vehiclestatus";
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					status = new Status();
					status.setStatusID(rs.getInt("StatusID"));
					status.setStatus(rs.getString("Status"));
					comboModel.addElement(status);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database! " + e2.getLocalizedMessage());
		}
		comboBox.setBounds(178, 181, 120, 20);
		comboBox.setModel(comboModel);
		contentPanel.add(comboBox);
		
		comboBox_1 = new JComboBox<Status>();
		DefaultComboBoxModel<Status> comboModel1 = new DefaultComboBoxModel<>();
		String sql1 = "select * from vehiclestatus";
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql1)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					status = new Status();
					status.setStatusID(rs.getInt("StatusID"));
					status.setStatus(rs.getString("Status"));
					comboModel1.addElement(status);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database! " + e2.getLocalizedMessage());
		}
		comboBox_1.setBounds(619, 98, 96, 18);
		comboBox_1.setModel(comboModel1);
		contentPanel.add(comboBox_1);
		{
			formattedTextField = new JFormattedTextField();
			formattedTextField.setBounds(178, 232, 120, 20);
			String pattern = "yyyy-MM-dd HH:mm:ss";
			formatter = DateTimeFormatter.ofPattern(pattern);
			String pattern1 = formatter.format(LocalDateTime.now());
			formattedTextField.setValue(pattern1);
			contentPanel.add(formattedTextField);
		
			formattedTextField_1 = new JFormattedTextField();
			formattedTextField_1.setBounds(178, 287, 120, 20);
			String pattern2 = formatter.format(LocalDateTime.now().plusDays(3));
			formattedTextField_1.setValue(pattern2);
			contentPanel.add(formattedTextField_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BookingsService service = new BookingsService();
							try {
								Bookings booking = new Bookings();
								Status stat = (Status) comboBox_1.getSelectedItem();
								booking.setStatusID(stat.getStatusID());
								if(textField_6.getText().equals("")) {
									bookingID = null;
								}
								else {
									bookingID = Integer.parseInt(textField_6.getText());
								}
								booking.setBookingID(bookingID);
								BookingsService.connect(connect);
								service.updateBooking(booking);
								JOptionPane.showMessageDialog(null, "Update was successfully made!", 
										"Update Successful", JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e1) {
							log.error("There was an error in the SQL database!" + e1.getLocalizedMessage());
						} catch(NumberFormatException ne) {
							log.error("Value must be a number!");
						} catch(NullPointerException npe) {
							log.error("Must insert a value!");
						}
					}
				});
				
				btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							BookingsService service = new BookingsService();
							try {
								if(textField_1.getText().equals("")) {
									bookingID = null;
								}
								else {
									bookingID = Integer.parseInt(textField_1.getText());
								}
								Status stat = (Status) comboBox.getSelectedItem();
								if(formattedTextField.getText().equals("")) {
									dateTime = null;
								}
								else {
									dateTime = LocalDateTime.parse(formattedTextField.getText(), formatter);
								}
								if(formattedTextField_1.getText().equals("")) {
									dateTime1 = null;
								}
								else {
									dateTime1 = LocalDateTime.parse(formattedTextField_1.getText(), formatter);
								}
								BookingsService.connect(connect);
								List<Bookings> bookings = service.searchBookings(textField.getText(), bookingID, 
										stat.getStatusID(), dateTime, dateTime1);
								for(Bookings booking: bookings) {
									log.info(booking);
								}
								JOptionPane.showMessageDialog(null, "Items found: " + bookings.size(), 
										"Search Successful!", JOptionPane.INFORMATION_MESSAGE);
							} catch (NumberFormatException e1) {
								log.error("Value must be a number!");
							} catch (SQLException e1) {
								log.error("There was an error in the SQL database!" + e1.getLocalizedMessage());
							} catch(NullPointerException npe) {
								log.error("Must insert a value!");
							} catch(DateTimeParseException dtpe) {
								log.error("DateTimeFormat not valid!");
							}
					}
				});
				buttonPane.add(btnSearch);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel update/search?", 
								"Cancel update/search", JOptionPane.OK_CANCEL_OPTION);
						if(action == JOptionPane.OK_OPTION) {
							dispose(); // only this was needed! to close the current window!
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
