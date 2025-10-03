import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Warranty Tracking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		UsernameLabel.setBounds(149, 77, 106, 44);
		contentPane.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		PasswordLabel.setBounds(149, 148, 106, 44);
		contentPane.add(PasswordLabel);
		
		usernameField = new JTextField();
		usernameField.setToolTipText("");
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		usernameField.setBounds(301, 85, 156, 35);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(301, 157, 156, 34);
		contentPane.add(passwordField);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.setForeground(new Color(0, 0, 0));
		LoginBtn.setBackground(new Color(128, 255, 128));
		LoginBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/warranty_tracking_schema?useSSL=false&allowPublicKeyRetrieval=true","root","Mathan****2506");
					
					String username=usernameField.getText();
					String password=passwordField.getText();
					
					Statement stmt=con.createStatement();
					
					String sql = "SELECT * FROM login_details WHERE username='" + username + "' AND password='" + password + "'";

					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						dispose();
						
						HomePage homePage=new HomePage();
						homePage.show();
					}
					else
					{

						JOptionPane.showMessageDialog(null, "Username or password is incorrect!");

						usernameField.setText("");
						passwordField.setText("");
					}
					
					con.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1.getMessage());
				}
				
				
			}
		});
		LoginBtn.setBounds(149, 257, 106, 29);
		contentPane.add(LoginBtn);
		
		JButton ResetBtn = new JButton("Reset");
		ResetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			usernameField.setText("");
			passwordField.setText("");
			}
		});
		ResetBtn.setForeground(Color.BLACK);
		ResetBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
		ResetBtn.setBackground(new Color(128, 255, 128));
		ResetBtn.setBounds(351, 254, 106, 34);
		contentPane.add(ResetBtn);

	}
}
