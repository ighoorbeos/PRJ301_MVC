/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */
import entity.Employees;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;

public class DAOEmployees extends DBConnect {

    // String sql = "SELECT * FROM Employees";
    public Vector<Employees> getEmployee(String sql) {
        Vector<Employees> vector = new Vector<Employees>();
        try {

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String title = rs.getString("Title");
                String titleOfCourtesy = rs.getString("TitleOfCourtesy");
                String birthDate = rs.getString("BirthDate");
                String hireDate = rs.getString("HireDate");
                String address = rs.getString("Address");
                String city = rs.getString("city");
                String region = rs.getString("Region");
                String postalCode = rs.getString("PostalCode");
                String country = rs.getString("Country");
                String homePhone = rs.getString("HomePhone");
                String extension = rs.getString("Extension");
                String notes = rs.getString("Notes");
                int reportsTo = rs.getInt("ReportsTo");
                String photoPath = rs.getString("PhotoPath");

                Employees employees = new Employees(employeeID, lastName, firstName,
                        title, titleOfCourtesy, birthDate, hireDate, address, city,
                        region, postalCode, country, homePhone, extension, notes, reportsTo, photoPath);

                vector.add(employees);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int insertEmployees(Employees employ) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees]\n"
                + "           ([LastName]\n"
                + "           ,[FirstName]\n"
                + "           ,[Title]\n"
                + "           ,[TitleOfCourtesy]\n"
                + "           ,[BirthDate]\n"
                + "           ,[HireDate]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[HomePhone]\n"
                + "           ,[Extension]\n"
                + "           ,[Notes]\n"
                + "           ,[ReportsTo]\n"
                + "           ,[PhotoPath])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setString(1, employ.getLastName());
            prestate.setString(2, employ.getFirstName());
            prestate.setString(3, employ.getTitle());
            prestate.setString(4, employ.getTitleOfCourtesy());
            prestate.setString(5, employ.getBirthDate());
            prestate.setString(6, employ.getHireDate());
            prestate.setString(7, employ.getAddress());
            prestate.setString(8, employ.getCity());
            prestate.setString(9, employ.getRegion());
            prestate.setString(10, employ.getPostalCode());
            prestate.setString(11, employ.getCountry());
            prestate.setString(12, employ.getHomePhone());
            prestate.setString(13, employ.getExtension());
            prestate.setString(14, employ.getNotes());
            prestate.setInt(15, employ.getReportsTo());
            prestate.setString(16, employ.getPhotoPath());

            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //ham update 1 phan tu 
    public int updateTitle(int employeeID, String title) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employees]\n"
                + "   SET [Title] = ?\n"
                + " WHERE EmployeeID = ?";
        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setString(1, title);
            prestate.setInt(2, employeeID);
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateEmployee(Employees emp) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employees]\n"
                + "   SET [LastName] = ?\n"
                + "      ,[FirstName] = ?\n"
                + "      ,[Title] = ?\n"
                + "      ,[TitleOfCourtesy] = ?\n"
                + "      ,[BirthDate] = ?\n"
                + "      ,[HireDate] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[HomePhone] = ?\n"
                + "      ,[Extension] = ?\n"
                + "      ,[Notes] = ?\n"
                + "      ,[ReportsTo] = ?\n"
                + "      ,[PhotoPath] = ?\n"
                + " WHERE EmployeeID = ?";

        try {
            PreparedStatement prestate;
            prestate = con.prepareStatement(sql);
            prestate.setString(1, emp.getLastName());
            prestate.setString(2, emp.getFirstName());
            prestate.setString(3, emp.getTitle());
            prestate.setString(4, emp.getTitleOfCourtesy());
            prestate.setString(5, emp.getBirthDate());
            prestate.setString(6, emp.getHireDate());
            prestate.setString(7, emp.getAddress());
            prestate.setString(8, emp.getCity());
            prestate.setString(9, emp.getRegion());
            prestate.setString(10, emp.getPostalCode());
            prestate.setString(11, emp.getCountry());
            prestate.setString(12, emp.getHomePhone());
            prestate.setString(13, emp.getExtension());
            prestate.setString(14, emp.getNotes());
            prestate.setInt(15, emp.getReportsTo());
            prestate.setString(16, emp.getPhotoPath());

            n = prestate.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateReportTo(int employeeID, int reportTo) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employees]\n"
                + "   SET [ReportsTo] = ?\n"
                + " WHERE EmployeeID = ?";

        PreparedStatement pre;
        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1, reportTo);
            pre.setInt(2, employeeID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //ham delete
    public int deleteEmployee(int employeeID) {
        int n = 0;
        String sql = "SELECT * FROM Employees WHERE EmployeeID = " + employeeID;
        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) {
                updateReportTo(employeeID, 2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sqlremove = "DELETE FROM [dbo].[Employees]\n"
                + "      WHERE EmployeeID =" + employeeID;

        Statement st;
        try {
            st = con.createStatement();
            n = st.executeUpdate(sqlremove);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    
    
    //session:
    public boolean login(String user, String pass){
        boolean flag = false; //ko login dc
        try {
            PreparedStatement pre = con.prepareStatement("SELECT * FROM Employees WHERE FirstName =? AND EmployeeID=?");
            pre.setString(1, user);
            pre.setInt(2, Integer.parseInt(pass));
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
              flag = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return flag;
    }

    public static void main(String[] args) {
        DAOEmployees dao = new DAOEmployees();
        Employees empoyees = new Employees(0, "bbb", "ccc",
                "ggg", "fff", "4/20/1888", "6/20/2025", "ggg", "hhh",
                "iii", "kkk", "lll", "mmm",
                "uuu", "ooo", 1, "ppp");

        int n = dao.insertEmployees(empoyees);
        if (n < 0) {
            System.out.println("FAIL INSERT");
        }
        Vector<Employees> emp = dao.getEmployee("SELECT * FROM Employees");
        for (Employees employees1 : emp) {
            System.out.println(employees1);
        }
    }
}
