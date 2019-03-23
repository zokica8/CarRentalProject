package kurs.carrental.officeapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class WelcomeToOfficeApp {

	private JFrame frmWelcomeToOffice;
	private JButton btnNewButton;
	private JButton btnUpdate;
	private JButton btnSearch;
	private JLabel lblWelcomeToOffice;
	private JLabel lblNewLabel;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeToOfficeApp window = new WelcomeToOfficeApp();
					window.frmWelcomeToOffice.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeToOfficeApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeToOffice = new JFrame();
		frmWelcomeToOffice.setTitle("Welcome to Office Application");
		frmWelcomeToOffice.setBounds(100, 100, 887, 492);
		frmWelcomeToOffice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeToOffice.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Insert");
		btnNewButton.setMnemonic(KeyEvent.VK_I);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertBookingDialog dialog = new InsertBookingDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnNewButton.setBounds(89, 335, 158, 63);
		frmWelcomeToOffice.getContentPane().add(btnNewButton);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUpdateBookingDialog dialog = new SearchUpdateBookingDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnUpdate.setBounds(350, 335, 158, 63);
		frmWelcomeToOffice.getContentPane().add(btnUpdate);
		
		btnSearch = new JButton("Search");
		btnSearch.setMnemonic(KeyEvent.VK_S);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUpdateBookingDialog dialog = new SearchUpdateBookingDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnSearch.setBounds(626, 335, 158, 63);
		frmWelcomeToOffice.getContentPane().add(btnSearch);
		
		lblWelcomeToOffice = new JLabel("Welcome to Office Application! ");
		lblWelcomeToOffice.setFont(new Font("Dubai", Font.PLAIN, 29));
		lblWelcomeToOffice.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToOffice.setBounds(89, 33, 677, 75);
		frmWelcomeToOffice.getContentPane().add(lblWelcomeToOffice);
		
		lblNewLabel = new JLabel("Please choose one of the options down below:");
		lblNewLabel.setFont(new Font("Dubai", Font.PLAIN, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 136, 653, 123);
		frmWelcomeToOffice.getContentPane().add(lblNewLabel);
		
		menuBar = new JMenuBar();
		frmWelcomeToOffice.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Window");
		mnNewMenu.setMnemonic(KeyEvent.VK_W);
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);
	}
}
