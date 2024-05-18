/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */
import entity.Suppliers;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;

public class DAOSuppliers extends DBConnect {

    public Vector<Suppliers> getSuppliers(String sql) {
        //String sql = "SELECT * FROM Suppliers";
        Vector<Suppliers> vector = new Vector<Suppliers>();

        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                int supplierID = rs.getInt("SupplierID");
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
                String homePage = rs.getString("HomePage");

                Suppliers sup = new Suppliers(supplierID, companyName, contactName,
                        contactTitle, address, city, region, postalCode, country, phone, fax, homePage);

                vector.add(sup);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int insertSuppliers(Suppliers sup) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[HomePage])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setString(1, sup.getCompanyName());
            prestate.setString(2, sup.getContactName());
            prestate.setString(3, sup.getContactTitle());
            prestate.setString(4, sup.getAddress());
            prestate.setString(5, sup.getCity());
            prestate.setString(6, sup.getRegion());
            prestate.setString(7, sup.getPostalCode());
            prestate.setString(8, sup.getCountry());
            prestate.setString(9, sup.getPhone());
            prestate.setString(10, sup.getFax());
            prestate.setString(11, sup.getHomePage());

            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateCompanyName(int supplierID, String companyName) {
        int n = 0;
        String sql = "UPDATE [dbo].[Suppliers]\n"
                + "   SET [CompanyName] = <CompanyName, nvarchar(40),>\n"
                + " WHERE SupplierID = ?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, companyName);
            pre.setInt(2, supplierID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateSupplier(Suppliers sup) {
        int n = 0;
        String sql = "UPDATE [dbo].[Suppliers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Fax] = ?\n"
                + "      ,[HomePage] = ?\n"
                + " WHERE SupplierID = ?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getContactName());
            pre.setString(3, sup.getContactTitle());
            pre.setString(4, sup.getAddress());
            pre.setString(5, sup.getCity());
            pre.setString(6, sup.getRegion());
            pre.setString(7, sup.getPostalCode());
            pre.setString(8, sup.getCountry());
            pre.setString(9, sup.getPhone());
            pre.setString(10, sup.getFax());
            pre.setString(11, sup.getHomePage());
            pre.setInt(12, sup.getSupplierID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeSupplier(int supplierID) {
        int n = 0;
        String sqlRemove = "DELETE FROM [dbo].[Suppliers]\n"
                + "      WHERE SupplierID = ?";
        try {
            Statement st = con.createStatement();
            n = st.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOSuppliers dao = new DAOSuppliers();
        Suppliers sup = new Suppliers(30, "Fpt", "AAA",
                "Hn", "Hn", "HN", "VN", "VN123",
                "VN", "000", "ads", "aaa");
        int n = dao.insertSuppliers(sup);
        if (n < 0) {
            System.out.println("Insert error");
        }
        Vector<Suppliers> vector = dao.getSuppliers("SELECT * FROM [Suppliers]");
        for (Suppliers suppliers : vector) {
            System.out.println(suppliers);
        }
    }
}
