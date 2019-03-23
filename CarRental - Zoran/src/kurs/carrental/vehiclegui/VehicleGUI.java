package kurs.carrental.vehiclegui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class VehicleGUI {

	private JFrame frmVehicleApplication;
	private JMenuBar menuBar;
	private JMenu mnWindow;
	private JMenuItem mntmExit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleGUI window = new VehicleGUI();
					window.frmVehicleApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VehicleGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVehicleApplication = new JFrame();
		frmVehicleApplication.setTitle("Vehicle Application");
		frmVehicleApplication.setBounds(100, 100, 874, 490);
		frmVehicleApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVehicleApplication.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Welcome To Vehicle Application");
		lblNewLabel.setFont(new Font("Candara", Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(86, 34, 665, 66);
		frmVehicleApplication.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Please choose one of the options below:");
		lblNewLabel_1.setFont(new Font("Pristina", Font.PLAIN, 35));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(86, 123, 665, 58);
		frmVehicleApplication.getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("Insert");
		btnNewButton.setMnemonic(KeyEvent.VK_I);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertVehicleDialog dialog = new InsertVehicleDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnNewButton.setBounds(86, 325, 97, 52);
		frmVehicleApplication.getContentPane().add(btnNewButton);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDeleteSearchDialog dialog = new UpdateDeleteSearchDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnUpdate.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnUpdate.setBounds(263, 325, 97, 52);
		frmVehicleApplication.getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDeleteSearchDialog dialog = new UpdateDeleteSearchDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnDelete.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnDelete.setBounds(482, 325, 97, 52);
		frmVehicleApplication.getContentPane().add(btnDelete);
		
		btnSearch = new JButton("Search");
		btnSearch.setMnemonic(KeyEvent.VK_S);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDeleteSearchDialog dialog = new UpdateDeleteSearchDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnSearch.setFont(new Font("Georgia", Font.PLAIN, 16));
		btnSearch.setBounds(654, 325, 97, 52);
		frmVehicleApplication.getContentPane().add(btnSearch);
		
		menuBar = new JMenuBar();
		frmVehicleApplication.setJMenuBar(menuBar);
		
		mnWindow = new JMenu("Window");
		mnWindow.setMnemonic(KeyEvent.VK_W);
		menuBar.add(mnWindow);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_DOWN_MASK));
		mnWindow.add(mntmExit);
	}
}
