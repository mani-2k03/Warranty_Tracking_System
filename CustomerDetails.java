import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CustomerDetails extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    public CustomerDetails() {
        setTitle("Customer Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Table headers
        model.addColumn("Product Id");
        model.addColumn("Product Name");
        model.addColumn("Customer Name");
        model.addColumn("Purchased Date");
        model.addColumn("Duration (in yrs)");
        model.addColumn("Warranty Status");
        model.addColumn("Extend Warranty");
        model.addColumn("Customer Contact");

        // 🔹 Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnRefresh = new JButton("Refresh");
        JButton btnActiveOnly = new JButton("Show Active Only");

        btnRefresh.addActionListener(e -> loadCustomerData(false));
        btnActiveOnly.addActionListener(e -> loadCustomerData(true));

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnActiveOnly);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // load all initially
        loadCustomerData(false);
    }

    // 🔹 Add a flag "activeOnly"
    private void loadCustomerData(boolean activeOnly) {
        try {
            model.setRowCount(0); // Clear previous data

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/warranty_tracking_schema?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Mathan****2506"
            );

            Statement st = con.createStatement();

            // if activeOnly=true → exclude Expired rows
            String sql = "SELECT Product_Id, Product_Name, Customer_Name, Purchased_Date, " +
                         "`Duration_of_Warranty( in yrs)`, Warranty_Status, Extend_Warranty, Customer_Contact_Number " +
                         "FROM warranty_details";

            if (activeOnly) {
                sql += " WHERE Warranty_Status <> 'Expired'";
            }

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("Product_Id"),
                        rs.getString("Product_Name"),
                        rs.getString("Customer_Name"),
                        rs.getString("Purchased_Date"),
                        rs.getObject("Duration_of_Warranty( in yrs)"),
                        rs.getString("Warranty_Status"),
                        rs.getString("Extend_Warranty"),
                        rs.getString("Customer_Contact_Number")
                });
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerDetails frame = new CustomerDetails();
            frame.setVisible(true);
        });
    }
}
