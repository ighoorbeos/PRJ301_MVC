/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */
import entity.Customers;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;

public class DAOCustomers extends DBConnect {

    // String sql = "SELECT * FROM Customers";
    public Vector<Customers> getCustomer(String sql) {
        Vector<Customers> vector = new Vector<Customers>();
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String customerID = rs.getString("CustomerID");
                String companyName = rs.getString("CompanyName");
                String contactName = rs.getString("ContactName");
                String contactTitle = rs.getString("ContactTitle");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String region = rs.getString("Region");
                String postalCode = rs.getString("PostalCode");
                String country = rs.getString("Country");
                String phone = rs.getString("Phone");
                String fax = rs.getString("Fax");

                Customers customer = new Customers(customerID, companyName, contactName,
                        contactTitle, address, city, region, postalCode, country, phone, fax);

                vector.add(customer);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

     public boolean login(String user, String pass) {
        boolean flag = false;
        try {
            PreparedStatement pre = con.prepareCall("select * from Customers where CustomerID = ? and ContactName = ?");
            pre.setString(1, pass);
            pre.setString(2, user);

            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }
     
    public int insertCustomers(Customers cus) {
        int n = 0;
        String sql = " INSERT INTO [dbo].[Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement prestate;
        try {
            prestate = con.prepareCall(sql);
            prestate.setString(1, cus.getCustomerID());
            prestate.setString(2, cus.getCompanyName());
            prestate.setString(3, cus.getContactName());
            prestate.setString(4, cus.getContactTitle());
            prestate.setString(5, cus.getAddress());
            prestate.setString(6, cus.getCity());
            prestate.setString(7, cus.getRegion());
            prestate.setString(8, cus.getPostalCode());
            prestate.setString(9, cus.getCountry());
            prestate.setString(10, cus.getPhone());
            prestate.setString(11, cus.getFax());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    //update 1 phan tu trong bang 
    public int updateContactName(int customerID, String contactName) {
        int n = 0;
        String sql = "UPDATE [dbo].[Customers]\n"
                + "   SET [ContactName] = ?\n"
                + " WHERE CustomerID = ?";
        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setString(1, contactName);
            prestate.setInt(2, customerID);
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //update ca bang table
    public int updateCustomer(Customers cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[Customers]\n"
                + "   SET [CustomerID] = ?\n"
                + "      ,[CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Fax] = ?\n"
                + " WHERE CustomerID = ?";
        PreparedStatement prestate;
        try {
            prestate = con.prepareStatement(sql);
            prestate.setString(1, cus.getCustomerID());
            prestate.setString(2, cus.getCompanyName());
            prestate.setString(3, cus.getContactName());
            prestate.setString(4, cus.getContactTitle());
            prestate.setString(5, cus.getAddress());
            prestate.setString(6, cus.getCity());
            prestate.setString(7, cus.getRegion());
            prestate.setString(8, cus.getPostalCode());
            prestate.setString(9, cus.getCountry());
            prestate.setString(10, cus.getPhone());
            prestate.setString(11, cus.getFax());
            prestate.setString(12,cus.getCustomerID());

            n = prestate.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;

    }

   public int removeCustomer(String customerID) {
    int n = 0;
    String sql = "DELETE FROM [dbo].[Customers] WHERE CustomerID = ?";
    try {
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, customerID);
        n = preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return n;
}


//    public static void main(String[] args) {
//        DAOCustomers dao = new DAOCustomers();
////        Customers cus = new Customers("1DA", "FPT",
////                "NgocHung", "depzai", "DHFPT", "HN",
////                "VN", "VN123", "BG", "00", "ttt");
////        int n = customer.insertCustomers(cus);
////        if (n < 0) {
////            System.out.println("FAIL TO INSERT");
////        }
//
////          int n = dao.updateContactName(2, "Ngoc Hung");
////          int a = dao.removeCustomer();
//            String n = dao.removeCustomer("uuu");
//        Vector<Customers> vector = dao.getCustomer("SELECT * FROM Customers");
//        for (Customers customers : vector) {
//            System.out.println(customers);
//        }
//    }
    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
        Customers cus = new Customers("aaaa", "cccc", "",
                "", "", "", "0000", "", "", "", "");
        // Test case 1: Delete an existing customer
        int result1 = dao.updateCustomer(cus);
        if (result1 > 0) {
            System.out.println("Customer update successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }

       

        // Print all customers after deletion
        Vector<Customers> vector = dao.getCustomer("SELECT * FROM Customers");
        for (Customers customers : vector) {
            System.out.println(customers);
        }
    }

}
