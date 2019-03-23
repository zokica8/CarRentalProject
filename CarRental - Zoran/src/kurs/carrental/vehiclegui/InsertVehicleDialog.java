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
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import kurs.carrental.beans.FuelType;
import kurs.carrental.beans.Office;
import kurs.carrental.beans.Status;
import kurs.carrental.beans.TransmissionType;
import kurs.carrental.beans.VehicleCategory;
import kurs.carrental.beans.VehicleModel;
import kurs.carrental.beans.Vehicles;
import kurs.carrental.connection.ConnectionInterface;
import kurs.carrental.connection.ConnectionRegular;
import kurs.carrental.services.VehiclesService;
import lombok.extern.log4j.Log4j2;

@SuppressWarnings("serial")
@Log4j2
public class InsertVehicleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblDoors;
	private JSpinner spinner;
	private JLabel lblNewLabel_1;
	private JSpinner spinner_1;
	private JLabel lblNewLabel_2;
	private JSlider slider;
	private JLabel lblPower;
	private JLabel lblNewLabel_3;
	private JComboBox<VehicleModel> comboBox;
	private JLabel lblOffice;
	private JComboBox<Office> comboBox_1;
	private JComboBox<VehicleCategory> comboBox_2;
	private JLabel lblCategory;
	private JLabel lblFueltype;
	private JComboBox<FuelType> comboBox_3;
	private JLabel lblStatus;
	private JComboBox<Status> comboBox_4;
	private JLabel lblTransmission;
	private JComboBox<TransmissionType> comboBox_5;
	private JSpinner spinner_2;
	private ConnectionInterface connect = new ConnectionRegular();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertVehicleDialog dialog = new InsertVehicleDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertVehicleDialog() {
		setTitle("Insert Vehicle");
		setBounds(100, 100, 867, 493);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Registration Number:");
			lblNewLabel.setBounds(23, 42, 124, 14);
			contentPanel.add(lblNewLabel);
		}

		textField = new JTextField();
		textField.setBounds(182, 39, 96, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		lblDoors = new JLabel("Doors:");
		lblDoors.setBounds(23, 97, 48, 14);
		contentPanel.add(lblDoors);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinner.setBounds(182, 94, 96, 20);
		contentPanel.add(spinner);

		lblNewLabel_1 = new JLabel("Seats:");
		lblNewLabel_1.setBounds(23, 158, 48, 14);
		contentPanel.add(lblNewLabel_1);

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinner_1.setBounds(182, 155, 96, 20);
		contentPanel.add(spinner_1);

		lblNewLabel_2 = new JLabel("Volume:");
		lblNewLabel_2.setBounds(23, 210, 48, 14);
		contentPanel.add(lblNewLabel_2);

		slider = new JSlider();
		slider.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		slider.setMinimum(500);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(250);
		slider.setMaximum(3000);
		slider.setBounds(23, 235, 259, 124);
		contentPanel.add(slider);

		lblPower = new JLabel("Power:");
		lblPower.setBounds(332, 210, 48, 14);
		contentPanel.add(lblPower);

		lblNewLabel_3 = new JLabel("Model:");
		lblNewLabel_3.setBounds(484, 42, 48, 14);
		contentPanel.add(lblNewLabel_3);

		comboBox = new JComboBox<>();
		DefaultComboBoxModel<VehicleModel> comboModel = new DefaultComboBoxModel<>();
		String sql = "select * from vehiclemodel";
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					VehicleModel vm = new VehicleModel();
					vm.setModelID(rs.getInt("ModelID"));
					vm.setName(rs.getString("Name"));
					vm.setManufacturerID(rs.getInt("ManufacturerID"));
					comboModel.addElement(vm);
				}
			}
		} catch (SQLException e1) {
			log.error("Error in the database " + e1.getLocalizedMessage());
		}

		comboBox.setBounds(647, 38, 115, 20);
		comboBox.setModel(comboModel);
		contentPanel.add(comboBox);

		lblOffice = new JLabel("Office:");
		lblOffice.setBounds(484, 97, 48, 14);
		contentPanel.add(lblOffice);

		comboBox_1 = new JComboBox<>();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		DefaultComboBoxModel<Office> comboModel1 = new DefaultComboBoxModel<>();
		String sql1 = "select * from office";
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql1)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Office office = new Office();
					office.setOfficeID(rs.getInt("OfficeID"));
					office.setName(rs.getString("Name"));
					office.setCityID(rs.getInt("CityID"));
					comboModel1.addElement(office);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database " + e2.getLocalizedMessage());
		}
		comboBox_1.setBounds(647, 93, 115, 22);
		comboBox_1.setModel(comboModel1);
		contentPanel.add(comboBox_1);

		comboBox_2 = new JComboBox<>();
		DefaultComboBoxModel<VehicleCategory> comboModel2 = new DefaultComboBoxModel<>();
		String sql2 = "select * from vehiclecategory";
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql2)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					VehicleCategory category = new VehicleCategory();
					category.setCategoryID(rs.getInt("CategoryID"));
					category.setName(rs.getString("Name"));
					category.setDescription(rs.getString("Description"));
					comboModel2.addElement(category);
				}
			}
		} catch (SQLException e2) {
			log.error("Error in the database " + e2.getLocalizedMessage());
		}
		comboBox_2.setBounds(647, 154, 115, 22);
		comboBox_2.setModel(comboModel2);
		contentPanel.add(comboBox_2);

		lblCategory = new JLabel("Category:");
		lblCategory.setBounds(484, 158, 48, 14);
		contentPanel.add(lblCategory);

		lblFueltype = new JLabel("FuelType:");
		lblFueltype.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFueltype.setBounds(484, 198, 48, 14);
		contentPanel.add(lblFueltype);

		comboBox_3 = new JComboBox<>();
		DefaultComboBoxModel<FuelType> comboModel3 = new DefaultComboBoxModel<>();
		String sql3 = "select * from fueltype";
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql3)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					FuelType fuelType = new FuelType();
					fuelType.setFuelTypeID(rs.getInt("FuelTypeID"));
					fuelType.setFuelType(rs.getString("FuelType"));
					comboModel3.addElement(fuelType);
				}
			}
		} catch (SQLException e) {
			log.error("Error in the database " + e.getLocalizedMessage());
		}
		comboBox_3.setBounds(647, 198, 115, 22);
		comboBox_3.setModel(comboModel3);
		contentPanel.add(comboBox_3);

		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(484, 240, 48, 14);
		contentPanel.add(lblStatus);

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
			log.error("Error in the database " + e2.getLocalizedMessage());
		}
		comboBox_4.setBounds(647, 236, 115, 22);
		comboBox_4.setModel(comboModel4);
		contentPanel.add(comboBox_4);

		lblTransmission = new JLabel("Transmission:");
		lblTransmission.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTransmission.setBounds(484, 293, 80, 14);
		contentPanel.add(lblTransmission);

		comboBox_5 = new JComboBox<>();
		DefaultComboBoxModel<TransmissionType> comboModel5 = new DefaultComboBoxModel<>();
		String sql5 = "select * from transmissiontype";
		try (PreparedStatement pstmt = connect.returnConnection().prepareStatement(sql5)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					TransmissionType trans = new TransmissionType();
					trans.setTransmissionTypeID(rs.getInt("TransmissionTypeID"));
					trans.setName(rs.getString("Name"));
					comboModel5.addElement(trans);
				}
			}

		} catch (SQLException e1) {
			log.error("Error in the database " + e1.getLocalizedMessage());
		}
		comboBox_5.setBounds(647, 289, 115, 22);
		comboBox_5.setModel(comboModel5);
		contentPanel.add(comboBox_5);

		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(50, 50, 200, 1));
		spinner_2.setBounds(310, 266, 96, 41);
		contentPanel.add(spinner_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Insert");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VehiclesService service = new VehiclesService();

						try {
							Vehicles vehicle = new Vehicles();
							vehicle.setRegNumber(textField.getText());
							vehicle.setDoors((int) spinner.getValue());
							vehicle.setSeats((int) spinner_1.getValue());
							vehicle.setVolume(slider.getValue());
							vehicle.setPower((int) spinner_2.getValue());
							VehicleModel model = (VehicleModel) comboBox.getSelectedItem();
							vehicle.setModelID(model.getModelID());
							Office office = (Office) comboBox_1.getSelectedItem();
							vehicle.setOfficeID(office.getOfficeID());
							VehicleCategory category = (VehicleCategory) comboBox_2.getSelectedItem();
							vehicle.setCategoryID(category.getCategoryID());
							FuelType fuelType = (FuelType) comboBox_3.getSelectedItem();
							vehicle.setFuelTypeID(fuelType.getFuelTypeID());
							Status status = (Status) comboBox_4.getSelectedItem();
							vehicle.setStatusID(status.getStatusID());
							TransmissionType trans = (TransmissionType) comboBox_5.getSelectedItem();
							vehicle.setTransmissionTypeID(trans.getTransmissionTypeID());
							
							VehiclesService.connect(connect);
							service.insertVehicle(vehicle);
							JOptionPane.showMessageDialog(null, "Insert successful!", 
									"Insert Successful", JOptionPane.INFORMATION_MESSAGE);
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
						int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel insert?",
								"Cancel insert operation", JOptionPane.OK_CANCEL_OPTION);
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
