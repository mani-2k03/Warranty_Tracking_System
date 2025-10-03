import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField product_nameField_;
	private JTextField customer_nameField_;
	private JTextField purchased_dateField_;
	private JTextField customer_contact_numberField_;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("Warranty Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel product_nameField = new JLabel("Product Name");
		product_nameField.setFont(new Font("Tahoma", Font.BOLD, 17));
		product_nameField.setBounds(62, 47, 137, 45);
		contentPane.add(product_nameField);
		
		JLabel customer_nameField = new JLabel("Customer Name");
		customer_nameField.setFont(new Font("Tahoma", Font.BOLD, 17));
		customer_nameField.setBounds(62, 113, 137, 45);
		contentPane.add(customer_nameField);
		
		JLabel purchased_dateField = new JLabel("Purchased Date");
		purchased_dateField.setFont(new Font("Tahoma", Font.BOLD, 17));
		purchased_dateField.setBounds(62, 178, 137, 45);
		contentPane.add(purchased_dateField);
		
		JLabel duration_of_warrantyField = new JLabel("Duration of Warranty");
		duration_of_warrantyField.setFont(new Font("Tahoma", Font.BOLD, 17));
		duration_of_warrantyField.setBounds(62, 249, 197, 45);
		contentPane.add(duration_of_warrantyField);
		
		JLabel warranty_statusField = new JLabel("Warranty Status");
		warranty_statusField.setFont(new Font("Tahoma", Font.BOLD, 17));
		warranty_statusField.setBounds(62, 322, 159, 45);
		contentPane.add(warranty_statusField);
		
		JLabel extend_warrantyField = new JLabel("Extend Warranty");
		extend_warrantyField.setFont(new Font("Tahoma", Font.BOLD, 17));
		extend_warrantyField.setBounds(62, 387, 167, 45);
		contentPane.add(extend_warrantyField);
		
		JLabel customer_contact_numberField = new JLabel("Contact Number");
		customer_contact_numberField.setFont(new Font("Tahoma", Font.BOLD, 17));
		customer_contact_numberField.setBounds(62, 442, 137, 45);
		contentPane.add(customer_contact_numberField);
		
		product_nameField_ = new JTextField();
		product_nameField_.setBounds(322, 56, 225, 34);
		contentPane.add(product_nameField_);
		product_nameField_.setColumns(10);
		
		customer_nameField_ = new JTextField();
		customer_nameField_.setColumns(10);
		customer_nameField_.setBounds(322, 122, 225, 34);
		contentPane.add(customer_nameField_);
		
		purchased_dateField_ = new JTextField();
		purchased_dateField_.setToolTipText("YYYY-MM-DD");
		purchased_dateField_.setColumns(10);
		purchased_dateField_.setBounds(322, 187, 225, 34);
		contentPane.add(purchased_dateField_);
		
		JComboBox duration_of_warrantyField_ = new JComboBox();
		duration_of_warrantyField_.setFont(new Font("Tahoma", Font.BOLD, 10));
		duration_of_warrantyField_.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		duration_of_warrantyField_.setBounds(322, 257, 225, 34);
		contentPane.add(duration_of_warrantyField_);
		
		JComboBox warranty_statusField_ = new JComboBox();
		warranty_statusField_.setFont(new Font("Tahoma", Font.BOLD, 10));
		warranty_statusField_.setModel(new DefaultComboBoxModel(new String[] {"Ongoing", "Expired"}));
		warranty_statusField_.setBounds(322, 322, 225, 35);
		contentPane.add(warranty_statusField_);
		
		JComboBox extend_warrantyField_ = new JComboBox();
		extend_warrantyField_.setFont(new Font("Tahoma", Font.BOLD, 10));
		extend_warrantyField_.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		extend_warrantyField_.setBounds(322, 387, 225, 35);
		contentPane.add(extend_warrantyField_);
		
		customer_contact_numberField_ = new JTextField();
		customer_contact_numberField_.setColumns(10);
		customer_contact_numberField_.setBounds(322, 453, 225, 34);
		contentPane.add(customer_contact_numberField_);
		
		JLabel lblNewLabel = new JLabel("Warranty Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(208, 10, 206, 36);
		contentPane.add(lblNewLabel);
		
		JButton SubmitBtn = new JButton("Submit");
		SubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubmitBtn.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        try {
				            // Validate inputs
				            if (product_nameField_.getText().trim().isEmpty() ||
				                customer_nameField_.getText().trim().isEmpty() ||
				                purchased_dateField_.getText().trim().isEmpty() ||
				                customer_contact_numberField_.getText().trim().isEmpty()) {
				                javax.swing.JOptionPane.showMessageDialog(null, "⚠️ Please fill all fields!");
				                return;
				            }

				            // Validate phone number
				            String contact = customer_contact_numberField_.getText().trim();
				            if (!contact.matches("\\d{10}")) {
				                javax.swing.JOptionPane.showMessageDialog(null, "⚠️ Enter a valid 10-digit phone number!");
				                return;
				            }

				            // Parse date properly
				            java.sql.Date sqlDate;
				            try {
				                sqlDate = java.sql.Date.valueOf(purchased_dateField_.getText()); // YYYY-MM-DD
				            } catch (IllegalArgumentException ex) {
				                javax.swing.JOptionPane.showMessageDialog(null, "⚠️ Enter Purchased Date in YYYY-MM-DD format!");
				                return;
				            }

				            // ✅ Declare query here
				            String query = "INSERT INTO warranty_details "
				                         + "(`Product_Name`, `Customer_Name`, `Purchased_Date`, "
				                         + "`Duration_of_Warranty( in yrs)`, `Warranty_Status`, `Extend_Warranty`, `Customer_Contact_Number`) "
				                         + "VALUES (?, ?, ?, ?, ?, ?, ?)";

				            // ✅ Try-with-resources to auto-close
				            try (Connection con = DriverManager.getConnection(
				                        "jdbc:mysql://localhost:3306/warranty_tracking_schema?useSSL=false",
				                        "root",
				                        "Mathan****2506");
				                 java.sql.PreparedStatement pst = con.prepareStatement(query)) {

				                // Set values
				                pst.setString(1, product_nameField_.getText());
				                pst.setString(2, customer_nameField_.getText());
				                pst.setDate(3, sqlDate);
				                pst.setString(4, (String) duration_of_warrantyField_.getSelectedItem());
				                pst.setString(5, (String) warranty_statusField_.getSelectedItem());
				                pst.setString(6, (String) extend_warrantyField_.getSelectedItem());
				                pst.setString(7, contact);

				                pst.executeUpdate();
				                javax.swing.JOptionPane.showMessageDialog(null, "✅ Data Saved Successfully!");
				                
				                CustomerDetails CPage=new CustomerDetails();
				                CPage.show();
				            }

				        } catch (Exception ex) {
				            ex.printStackTrace();
				            javax.swing.JOptionPane.showMessageDialog(null, "❌ Error: " + ex.getMessage());
				        }
				    }
				});

}
		    
		});
		
		SubmitBtn.setBackground(Color.YELLOW);
		SubmitBtn.setFont(new Font("Tahoma", Font.BOLD, 10));
		SubmitBtn.setBounds(258, 519, 121, 27);
		contentPane.add(SubmitBtn);

	}
}
