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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import kurs.carrental.beans.Bookings;
import kurs.carrental.beans.Office;
import kurs.carrental.beans.Status;
import kurs.carrental.beans.VehicleCategory;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import kurs.carrental.services.BookingsService;
import lombok.extern.log4j.Log4j2;

@SuppressWarnings("serial")
@Log4j2
public class InsertBookingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox<VehicleCategory> comboBox;
	private JComboBox<Office> comboBox_1;
	private JComboBox<Office> comboBox_2;
	private JComboBox<Status> comboBox_3;
	private JTextField textField_6;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JMenuBar menuBar;
	private JMenu mnNew;
	private JMenuItem mntmBackToMain;
	private JSpinner spinner;
	private VehicleCategory category;
	private Office office;
	private Status status;
	private ConnectionInterface connect = new ConnectionRegular();
	private Double price;
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertBookingDialog dialog = new InsertBookingDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertBookingDialog() {
		setTitle("Insert Booking");
		setBounds(100, 100, 871, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(184, 37, 119, 20);
		contentPanel.add(textField);
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(184, 142, 119, 20);
		
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(184, 192, 119, 20);
		
		contentPanel.add(textField_3);
		
		comboBox = new JComboBox<>();
		DefaultComboBoxModel<VehicleCategory> comboModel = new DefaultComboBoxModel<>();
		String sql = "select * from vehiclecategory";
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					category = new VehicleCategory();
					category.setCategoryID(rs.getInt("CategoryID"));
					category.setName(rs.getString("Name"));
					category.setDescription(rs.getString("Description"));
					comboModel.addElement(category);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database " + e2.getLocalizedMessage());
		}
		
		comboBox.setBounds(611, 37, 131, 21);
		comboBox.setModel(comboModel);
		contentPanel.add(comboBox);
		
		comboBox_1 = new JComboBox<>();
		DefaultComboBoxModel<Office> comboModel1 = new DefaultComboBoxModel<>();
		String sql1 = "select * from office";
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql1)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					office = new Office();
					office.setOfficeID(rs.getInt("OfficeID"));
					office.setName(rs.getString("Name"));
					office.setCityID(rs.getInt("CityID"));
					comboModel1.addElement(office);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database " + e2.getLocalizedMessage());
		}
		comboBox_1.setBounds(611, 85, 131, 21);
		comboBox_1.setModel(comboModel1);
		contentPanel.add(comboBox_1);
		
		comboBox_2 = new JComboBox<>();
		DefaultComboBoxModel<Office> comboModel2 = new DefaultComboBoxModel<>();
		String sql2 = "select * from office";
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql2)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					office = new Office();
					office.setOfficeID(rs.getInt("OfficeID"));
					office.setName(rs.getString("Name"));
					office.setCityID(rs.getInt("CityID"));
					comboModel2.addElement(office);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database " + e2.getLocalizedMessage());
		}
		comboBox_2.setBounds(611, 142, 131, 21);
		comboBox_2.setModel(comboModel2);
		contentPanel.add(comboBox_2);
		
		comboBox_3 = new JComboBox<>();
		DefaultComboBoxModel<Status> comboModel3 = new DefaultComboBoxModel<>();
		String sql3 = "select * from vehiclestatus";
		try(PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql3)) {
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					status = new Status();
					status.setStatusID(rs.getInt("StatusID"));
					status.setStatus(rs.getString("Status"));
					comboModel3.addElement(status);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database " + e2.getLocalizedMessage());
		}
		comboBox_3.setBounds(611, 192, 131, 21);
		comboBox_3.setModel(comboModel3);
		contentPanel.add(comboBox_3);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(623, 242, 119, 20);
		contentPanel.add(textField_6);
		
		label = new JLabel("Name:");
		label.setBounds(44, 41, 48, 14);
		contentPanel.add(label);
		
		label_1 = new JLabel("Age:");
		label_1.setBounds(44, 88, 48, 14);
		contentPanel.add(label_1);
		
		label_2 = new JLabel("Email:");
		label_2.setBounds(44, 145, 48, 14);
		contentPanel.add(label_2);
		
		label_3 = new JLabel("Phone:");
		label_3.setBounds(44, 195, 48, 14);
		contentPanel.add(label_3);
		
		label_4 = new JLabel("Pickup Time:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_4.setBounds(44, 245, 70, 14);
		contentPanel.add(label_4);
		
		label_5 = new JLabel("Dropoff Time:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_5.setBounds(44, 303, 70, 14);
		contentPanel.add(label_5);
		
		label_6 = new JLabel("Vehicle Category:");
		label_6.setBounds(447, 40, 107, 17);
		contentPanel.add(label_6);
		
		label_7 = new JLabel("Pickup Office:");
		label_7.setBounds(447, 88, 107, 17);
		contentPanel.add(label_7);
		
		label_8 = new JLabel("Dropoff Office:");
		label_8.setBounds(447, 145, 107, 17);
		contentPanel.add(label_8);
		
		label_9 = new JLabel("Status:");
		label_9.setBounds(447, 195, 107, 17);
		contentPanel.add(label_9);
		
		label_10 = new JLabel("Total Price:");
		label_10.setBounds(447, 245, 107, 17);
		contentPanel.add(label_10);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 855, 22);
		contentPanel.add(menuBar);
		
		mnNew = new JMenu("Window");
		mnNew.setMnemonic(KeyEvent.VK_W);
		menuBar.add(mnNew);
		
		mntmBackToMain = new JMenuItem("Exit");
		mntmBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmBackToMain.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mnNew.add(mntmBackToMain);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(18, 18, 100, 1));
		spinner.setFont(new Font("Georgia", Font.PLAIN, 18));
		spinner.setBounds(184, 85, 119, 21);
		contentPanel.add(spinner);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(184, 241, 119, 21);
		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String pattern1 = formatter.format(LocalDateTime.now());
		formattedTextField.setValue(pattern1);
		contentPanel.add(formattedTextField);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(184, 300, 119, 20);
		String pattern2 = formatter.format(LocalDateTime.now().plusDays(3));
		formattedTextField_1.setValue(pattern2);
		contentPanel.add(formattedTextField_1);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Insert");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BookingsService service = new BookingsService();
						
						try {
							Bookings booking = new Bookings();
							booking.setDriverName(textField.getText());
							booking.setDriverAge((int) spinner.getValue());
							booking.setDriverEmail(textField_2.getText());
							booking.setDriverPhone(textField_3.getText());
							booking.setPickupTime(LocalDateTime.parse(formattedTextField.getText(), formatter));
							booking.setDropoffTime(LocalDateTime.parse(formattedTextField_1.getText(), formatter));
							VehicleCategory cat = (VehicleCategory) comboBox.getSelectedItem();
							booking.setVehicleCategoryID(cat.getCategoryID());
							Office pick = (Office) comboBox_1.getSelectedItem();
							booking.setPickupOfficeID(pick.getOfficeID());
							Office drop = (Office) comboBox_2.getSelectedItem();
							booking.setDropoffOfficeID(drop.getOfficeID());
							Status stat = (Status) comboBox_3.getSelectedItem();
							booking.setStatusID(stat.getStatusID());
							if(textField_6.getText().equals("")) {
								price = null;
							}
							else {
								price = Double.parseDouble(textField_6.getText());
							}
							booking.setPrice(price);
							BookingsService.connect(connect);
							service.insertBooking(booking);
							JOptionPane.showMessageDialog(null, "Insert successful!", "Insert successful", JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e1) {
							log.error("There was an error in the SQL database!" + e1.getLocalizedMessage());
						} catch(NullPointerException npe) {
							log.error("Must insert a value!");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel insert?", 
								"Cancel insert operation", JOptionPane.OK_CANCEL_OPTION);
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
