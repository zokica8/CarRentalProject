package kurs.carrental.vehiclegui;

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
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

import kurs.carrental.beans.Office;
import kurs.carrental.beans.Status;
import kurs.carrental.beans.VehicleCategory;
import kurs.carrental.beans.VehicleManufacturer;
import kurs.carrental.beans.VehicleModel;
import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import kurs.carrental.services.VehiclesService;
import lombok.extern.log4j.Log4j2;

@SuppressWarnings("serial")
@Log4j2
public class UpdateDeleteSearchDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<VehicleCategory> comboBox;
	private ConnectionInterface connect = new ConnectionRegular();
	private JComboBox<Status> comboBox_1;
	private JComboBox<Office> comboBox_2;
	private JComboBox<Office> comboBox_3;
	private JComboBox<Status> comboBox_4;
	private JComboBox<VehicleManufacturer> comboBox_5;
	private Integer vehicleID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateDeleteSearchDialog dialog = new UpdateDeleteSearchDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateDeleteSearchDialog() {
		setTitle("Update Delete Search Vehicle");
		setBounds(100, 100, 874, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Update");
			lblNewLabel.setFont(new Font("Forte", Font.PLAIN, 21));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(57, 26, 141, 36);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblStatus = new JLabel("Status:");
			lblStatus.setBounds(57, 73, 48, 14);
			contentPanel.add(lblStatus);
		}
		{
			comboBox_1 = new JComboBox<>();
			DefaultComboBoxModel<Status> comboModel1 = new DefaultComboBoxModel<>();
			String sql = "select * from vehiclestatus";
			try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						Status status = new Status();
						status.setStatusID(rs.getInt("StatusID"));
						status.setStatus(rs.getString("Status"));
						comboModel1.addElement(status);
					}
				}
			} catch (SQLException e2) {
				log.error("Error in the database! " + e2.getLocalizedMessage());
			}
			comboBox_1.setBounds(195, 69, 107, 22);
			comboBox_1.setModel(comboModel1);
			contentPanel.add(comboBox_1);
		}
		{
			JLabel lblOffice = new JLabel("Office:");
			lblOffice.setBounds(57, 115, 48, 14);
			contentPanel.add(lblOffice);
		}
		{
			comboBox_2 = new JComboBox<>();
			comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
			DefaultComboBoxModel<Office> comboModel2 = new DefaultComboBoxModel<>();
			String sql2 = "select * from office";
			try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql2)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						Office office = new Office();
						office.setOfficeID(rs.getInt("OfficeID"));
						office.setName(rs.getString("Name"));
						office.setCityID(rs.getInt("CityID"));
						comboModel2.addElement(office);
					}
				}
			} catch (SQLException e2) {
				log.error("Error in the database " + e2.getLocalizedMessage());
			}
			comboBox_2.setBounds(195, 111, 107, 22);
			comboBox_2.setModel(comboModel2);
			contentPanel.add(comboBox_2);
		}
		{
			JLabel lblVehicleid = new JLabel("VehicleID:");
			lblVehicleid.setBounds(57, 175, 61, 14);
			contentPanel.add(lblVehicleid);
		}
		{
			textField = new JTextField();
			textField.setBounds(195, 172, 96, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Delete");
			lblNewLabel_1.setFont(new Font("Forte", Font.PLAIN, 21));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(57, 247, 141, 36);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel label = new JLabel("VehicleID:");
			label.setBounds(57, 309, 61, 14);
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(195, 306, 96, 20);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Search");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Forte", Font.PLAIN, 21));
			lblNewLabel_2.setBounds(603, 26, 149, 36);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Category:");
			lblNewLabel_3.setBounds(542, 73, 85, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			comboBox = new JComboBox<>();
			DefaultComboBoxModel<VehicleCategory> comboModel = new DefaultComboBoxModel<>();
			String sql = "select * from vehiclecategory";
			try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						VehicleCategory category = new VehicleCategory();
						category.setCategoryID(rs.getInt("CategoryID"));
						category.setName(rs.getString("Name"));
						category.setDescription(rs.getString("Description"));
						comboModel.addElement(category);
					}
				}
			} catch (SQLException e2) {
				log.error("Error in the database " + e2.getLocalizedMessage());
			}

			comboBox.setBounds(705, 69, 107, 22);
			comboBox.setModel(comboModel);
			contentPanel.add(comboBox);
		}
		{
			JLabel label = new JLabel("Office:");
			label.setBounds(542, 115, 48, 14);
			contentPanel.add(label);
		}
		{
			comboBox_3 = new JComboBox<>();
			comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
			DefaultComboBoxModel<Office> comboModel3 = new DefaultComboBoxModel<>();
			String sql3 = "select * from office";
			try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql3)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						Office office = new Office();
						office.setOfficeID(rs.getInt("OfficeID"));
						office.setName(rs.getString("Name"));
						office.setCityID(rs.getInt("CityID"));
						comboModel3.addElement(office);
					}
				}
			} catch (SQLException e2) {
				log.error("Error in the database " + e2.getLocalizedMessage());
			}
			comboBox_3.setBounds(705, 111, 107, 22);
			comboBox_3.setModel(comboModel3);
			contentPanel.add(comboBox_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Status:");
			lblNewLabel_4.setBounds(542, 175, 48, 14);
			contentPanel.add(lblNewLabel_4);
		}
		{
			comboBox_4 = new JComboBox<>();
			DefaultComboBoxModel<Status> comboModel4 = new DefaultComboBoxModel<>();
			String sql4 = "select * from vehiclestatus";
			try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql4)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						Status status = new Status();
						status.setStatusID(rs.getInt("StatusID"));
						status.setStatus(rs.getString("Status"));
						comboModel4.addElement(status);
					}
				}
			} catch (SQLException e2) {
				log.error("Error in the database! " + e2.getLocalizedMessage());
			}

			comboBox_4.setBounds(705, 171, 107, 22);
			comboBox_4.setModel(comboModel4);
			contentPanel.add(comboBox_4);
		}
		{
			JLabel lblManufacturer = new JLabel("Manufacturer:");
			lblManufacturer.setBounds(542, 237, 96, 14);
			contentPanel.add(lblManufacturer);
		}
		{
			comboBox_5 = new JComboBox<>();
			DefaultComboBoxModel<VehicleManufacturer> comboModel5 = new DefaultComboBoxModel<>();
			String sql5 = "select * from vehiclemanufacturer";
			try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql5)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						VehicleManufacturer manufacturer = new VehicleManufacturer();
						manufacturer.setManufacturerID(rs.getInt("ManufacturerID"));
						manufacturer.setName(rs.getString("Name"));
						comboModel5.addElement(manufacturer);
					}
				}
			} catch (SQLException e) {
				log.error("Error in the database! " + e.getLocalizedMessage());
			}
			comboBox_5.setBounds(705, 233, 107, 22);
			comboBox_5.setModel(comboModel5);
			contentPanel.add(comboBox_5);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton_1 = new JButton("Update");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VehiclesService service = new VehiclesService();
						try {
							Vehicles vehicle = new Vehicles();
							Status status = (Status) comboBox_1.getSelectedItem();
							vehicle.setStatusID(status.getStatusID());
							Office office = (Office) comboBox_2.getSelectedItem();
							vehicle.setOfficeID(office.getOfficeID());
							if (textField.getText().equals("")) {
								vehicleID = null;
							} else {
								vehicleID = Integer.parseInt(textField.getText());
							}
							vehicle.setVehicleID(vehicleID);
							
							VehiclesService.connect(connect);
							service.updateVehicle(vehicle);
							JOptionPane.showMessageDialog(null, "Update was successfully made!", "Update Successful",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e1) {
							log.error("There was an error in the SQL database!" + e1.getLocalizedMessage());
						} catch (NumberFormatException ne) {
							log.error("Value must be a number!");
						} catch (NullPointerException npe) {
							log.error("Must insert a value!");
						}

					}
				});
				buttonPane.add(btnNewButton_1);
			}
			{
				JButton btnNewButton = new JButton("Delete");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VehiclesService service = new VehiclesService();
						try {
							Vehicles vehicle = new Vehicles();
							if (textField_1.getText().equals("")) {
								vehicleID = null;
							} else {
								vehicleID = Integer.parseInt(textField_1.getText());
							}
							vehicle.setVehicleID(vehicleID);
							
							VehiclesService.connect(connect);
							service.deleteVehicle(vehicle.getVehicleID());
							JOptionPane.showMessageDialog(null, "Delete was successfully made!", "Delete Successful",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e1) {
							log.error("There was an error in the SQL database!" + e1.getLocalizedMessage());
						} catch (NumberFormatException ne) {
							log.error("Value must be a number!");
						} catch (NullPointerException npe) {
							log.error("Must insert a value!");
						}
					}
				});
				buttonPane.add(btnNewButton);
			}
			{
				JButton okButton = new JButton("Search");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VehiclesService service = new VehiclesService();
						try {
							Vehicles vehicle = new Vehicles();
							VehicleModel model = new VehicleModel();
							VehicleCategory category = (VehicleCategory) comboBox.getSelectedItem();
							Office office = (Office) comboBox_3.getSelectedItem();
							Status status = (Status) comboBox_4.getSelectedItem();
							vehicle.setStatusID(status.getStatusID());
							// questionable!
							VehicleManufacturer manufacturer = (VehicleManufacturer) comboBox_5.getSelectedItem();
							model.setManufacturerID(manufacturer.getManufacturerID());
							vehicle.setModelID(model.getModelID());
							
							VehiclesService.connect(connect);
							@SuppressWarnings("unused")
							List<Vehicles> vehicles = service.searchVehicles(category.getCategoryID(),
									office.getOfficeID(), status.getStatusID(), null);
							List<Vehicles> vehicles1 = service.searchVehicles(null,
									null, null, manufacturer.getManufacturerID());
							for(Vehicles vehicleFrom : vehicles1) {
								log.info(vehicleFrom);
							}
							JOptionPane.showMessageDialog(null, "Items found: " + vehicles1.size(), 
									"Search Successful!", JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e1) {
							log.error("There was an error in the SQL database!" + e1.getLocalizedMessage());
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
						int action = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to cancel update/delete/search?", "Cancel update/delete/search",
								JOptionPane.OK_CANCEL_OPTION);
						if (action == JOptionPane.OK_OPTION) {
							dispose(); // only this was needed! to close the current window!
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnWindow = new JMenu("Window");
				mnWindow.setMnemonic(KeyEvent.VK_W);
				menuBar.add(mnWindow);
				{
					JMenuItem mntmExit = new JMenuItem("Exit");
					mntmExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
					mnWindow.add(mntmExit);
				}
			}
		}
	}

}
