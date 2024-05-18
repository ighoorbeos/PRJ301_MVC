/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */
import entity.OrderDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;

public class DAOOrderDetails extends DBConnect {

    //String sql = "SELECT * FROM [Order Details]";
    public Vector<OrderDetails> getOrderDetails(String sql) {
        Vector<OrderDetails> vector = new Vector<OrderDetails>();
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int productID = rs.getInt("ProductID");
                double unitPrice = rs.getDouble("UnitPrice");
                int quantity = rs.getInt("Quantity");
                float discount = rs.getFloat("Discount");

                OrderDetails orderdetail = new OrderDetails(orderID, productID,
                        unitPrice, quantity, discount);

                vector.add(orderdetail);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vector;
    }

    public int insertOrderDetails(OrderDetails orderdetail) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Discount])\n"
                + "     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setInt(1, orderdetail.getOrderID());
            prestate.setInt(2, orderdetail.getProductID());
            prestate.setDouble(3, orderdetail.getUnitPrice());
            prestate.setInt(4, orderdetail.getQuantity());
            prestate.setFloat(5, orderdetail.getDiscount());

            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //update 1 phan tu:
    public int updateDiscount(int orderID, int discount) {
        int n = 0;
        String sql = "UPDATE [dbo].[Order Details]\n"
                + "   SET [Discount] = ?\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement prestate = con.prepareStatement("sql");
            prestate.setFloat(1, discount);
            prestate.setInt(2, orderID);
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateOrderDetails(OrderDetails orderdetail) {
        int n = 0;
        String sql = "UPDATE [dbo].[Order Details]\n"
                + "   SET [OrderID] = ?\n"
                + "      ,[ProductID] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[Discount] = ?\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setInt(1, orderdetail.getOrderID());
            prestate.setInt(2, orderdetail.getProductID());
            prestate.setDouble(3, orderdetail.getUnitPrice());
            prestate.setInt(4, orderdetail.getQuantity());
            prestate.setFloat(5, orderdetail.getDiscount());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //xoa table
    public int removeOrderDetails(int orderID) {
        int n = 0;
        String sqlDelete = "DELETE FROM [dbo].[Order Details]\n"
                + "      WHERE OrderID = " + orderID;
        PreparedStatement pre;
        try {
           Statement st = con.createStatement();
           n = st.executeUpdate(sqlDelete);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

//    public static void main(String[] args) {
//        DAOOrderDetails dao = new DAOOrderDetails();
//        OrderDetails orderdetails = new OrderDetails(2, 3,
//                20.5, 10, (float) 0.2);
//        int n = dao.insertOrderDetails(orderdetails);
//        if(n < 0){
//            System.out.println("FAIL INSERT ");
//        }
//        Vector<OrderDetails> vector = dao.getOrderDetails("SELECT * FROM [Order Details]");
//        for (OrderDetails orderDetails : vector) {
//            System.out.println(orderDetails);
//        }
//
//    }
}
