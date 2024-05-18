/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */
import entity.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;

public class DAOOrders extends DBConnect {

    // String sql = "SELECT * FROM Orders";
    public Vector<Orders> getOrder(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                String customerID = rs.getString("CustomerID");
                int employeeID = rs.getInt("EmployeeID");
                String orderDate = rs.getString("OrderDate");
                String requiredDate = rs.getString("RequiredDate");
                String shippedDate = rs.getString("ShippedDate");
                int shipVia = rs.getInt("ShipVia");
                double freight = rs.getDouble("Freight");
                String shipName = rs.getString("ShipName");
                String shipAddress = rs.getString("ShipAddress");
                String shipCity = rs.getString("ShipCity");
                String shipRegion = rs.getString("ShipRegion");
                String shipPostalCode = rs.getString("ShipPostalCode");
                String shipCountry = rs.getString("ShipCountry");

                Orders order = new Orders(orderID, customerID, employeeID,
                        orderDate, requiredDate, shippedDate, shipVia, freight,
                        shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry);

                vector.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    
    public int getLastOrderID() {
        

        try {
            //statement: send sql command and get result
            Statement state = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet get table 
            ResultSet rs = state.executeQuery("SELECT *\n"
                    + "FROM Orders\n"
                    + "WHERE OrderID = (SELECT MAX(OrderID) FROM Orders);");
            while (rs.next()) {
                int OrderID = rs.getInt(1);
               return OrderID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public int insertOrder(Orders order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([CustomerID]\n"
                + "           ,[EmployeeID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[RequiredDate]\n"
                + "           ,[ShippedDate]\n"
                + "           ,[ShipVia]\n"
                + "           ,[Freight]\n"
                + "           ,[ShipName]\n"
                + "           ,[ShipAddress]\n"
                + "           ,[ShipCity]\n"
                + "           ,[ShipRegion]\n"
                + "           ,[ShipPostalCode]\n"
                + "           ,[ShipCountry])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setString(1, order.getCustomerID());
            prestate.setInt(2, order.getEmployeeID());
            prestate.setString(3, order.getOrderDate());
            prestate.setString(4, order.getRequiredDate());
            prestate.setString(5, order.getShippedDate());
            prestate.setInt(6, order.getShipVia());
            prestate.setDouble(7, order.getFreight());
            prestate.setString(8, order.getShipName());
            prestate.setString(9, order.getShipAddress());
            prestate.setString(10, order.getShipCity());
            prestate.setString(11, order.getShipRegion());
            prestate.setString(12, order.getShipPostalCode());
            prestate.setString(13, order.getShipCountry());

            n = prestate.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //update 1 phan tu
    public int updateShipCountry(int orderID, String shipCountry) {
        int n = 0;
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [ShipCountry] = ?\n"
                + " WHERE OrderID = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, shipCountry);
            pre.setInt(2, orderID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //update ca table
    public int updateOrder(Orders orders) {
        int n = 0;
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [CustomerID] = ?\n"
                + "      ,[EmployeeID] = ?\n"
                + "      ,[OrderDate] = ?\n"
                + "      ,[RequiredDate] = ?\n"
                + "      ,[ShippedDate] = ?\n"
                + "      ,[ShipVia] = ?\n"
                + "      ,[Freight] = ?\n"
                + "      ,[ShipName] = ?\n"
                + "      ,[ShipAddress] = ?\n"
                + "      ,[ShipCity] = ?\n"
                + "      ,[ShipRegion] = ?\n"
                + "      ,[ShipPostalCode] = ?\n"
                + "      ,[ShipCountry] = ?\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, orders.getCustomerID());
            pre.setInt(2, orders.getEmployeeID());
            pre.setString(3, orders.getOrderDate());
            pre.setString(4, orders.getRequiredDate());
            pre.setString(5, orders.getShippedDate());
            pre.setInt(6, orders.getShipVia());
            pre.setDouble(7, orders.getFreight());
            pre.setString(8, orders.getShipName());
            pre.setString(9, orders.getShipAddress());
            pre.setString(10, orders.getShipCity());
            pre.setString(11, orders.getShipRegion());
            pre.setString(12, orders.getShipPostalCode());
            pre.setString(13, orders.getShipCountry());
            pre.setInt(14, orders.getOrderID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //ham delete
    public int removeOrders(int orderID) {
        int n = 0;
        String sqlDelete = "DELETE FROM [dbo].[Orders]\n"
                + "      WHERE OrderID =" + orderID;

        Statement st;
        try {
            st = con.createStatement();
            n = st.executeUpdate(sqlDelete);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();
        Orders order = new Orders(11083, "qqe", 1, "2/12/2022",
                "2/24/2022", "2/23/2022", 3, 4, "give",
                "HN", "HN", "jj", "kk", "vn");
        int n = dao.insertOrder(order);
        //int n = dao.removeOrders(110778);
        if (n < 0) {
            System.out.println("INSERT ERROR");
        }
        Vector<Orders> vector = dao.getOrder("SELECT * FROM Orders");
        for (Orders orders : vector) {
            System.out.println(orders);
        }
    }
}
