/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */
import entity.Products;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class DAOProducts extends DBConnect {

    //C1:
    public int addProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES\n"
                + "           ('" + pro.getProductName() + "'," + pro.getSupplierID()
                + "," + pro.getCategoryID() + ",'" + pro.getQuantityPerUnit() + "'," + pro.getUnitPrice() + ","
                + pro.getUnitsInStock() + "," + pro.getUnitsOnOrder() + ","
                + pro.getReorderLevel() + "," + (pro.isDiscontinued() == true ? 1 : 0) + ")";

        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //LIST ALL PRODUCTS
    //Dung vector thay arralylist
    public Vector<Products> getProducts(String sql) {
        Vector<Products> vector = new Vector<Products>();
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); //gui cau lenh sql
            ResultSet rs = st.executeQuery(sql); //lay du lieu table tu db
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierID = rs.getInt("SupplierID");
                int categoryID = rs.getInt("CategoryID");
                String quantityPerUnit = rs.getString("QuantityPerUnit");
                double unitPrice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");
                int unitsOnOrder = rs.getInt("UnitsOnOrder");
                int reorderLevel = rs.getInt("ReorderLevel");
                boolean discontinued = rs.getBoolean("Discontinued");

                Products product = new Products(productID, productName, supplierID,
                        categoryID, quantityPerUnit, unitPrice, unitsInStock,
                        unitsOnOrder, reorderLevel, discontinued);

                //System.out.println(product);
                vector.add(product); //day product vao vector
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //C2:=>use
    public int insertProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            //preState.setDataType(index,value,)
            prestate.setString(1, pro.getProductName());
            prestate.setInt(2, pro.getSupplierID());
            prestate.setInt(3, pro.getCategoryID());
            prestate.setString(4, pro.getQuantityPerUnit());
            prestate.setDouble(5, pro.getUnitPrice());
            prestate.setInt(6, pro.getUnitsInStock());
            prestate.setInt(7, pro.getUnitsOnOrder());
            prestate.setInt(8, pro.getReorderLevel());
            prestate.setInt(9, pro.isDiscontinued() == true ? 1 : 0);
            n = prestate.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //update 1 phan tu trong table
    public int updateUnitPrice(int pid, double price) { //truyen vao productid muon update price va price update
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET \n"
                + "      [UnitPrice] = [UnitPrice] + ?\n"
                + "      \n"
                + " WHERE ProductID = ? ";

        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setDouble(1, price);  //truyen truc tiep 
            prestate.setInt(2, pid);
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //Update ca table 
    public int updateProduct(Products pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[SupplierID] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[QuantityPerUnit] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[UnitsInStock] = ?\n"
                + "      ,[UnitsOnOrder] = ?\n"
                + "      ,[ReorderLevel] = ?\n"
                + "      ,[Discontinued] = ?\n"
                + " WHERE ProductID = ?";;

        try {
            PreparedStatement prestate = con.prepareStatement(sql);
            prestate.setString(1, pro.getProductName());
            prestate.setInt(2, pro.getSupplierID());
            prestate.setInt(3, pro.getCategoryID());
            prestate.setString(4, pro.getQuantityPerUnit());
            prestate.setDouble(5, pro.getUnitPrice());
            prestate.setInt(6, pro.getUnitsInStock());
            prestate.setInt(7, pro.getUnitsOnOrder());
            prestate.setInt(8, pro.getReorderLevel());
            prestate.setInt(9, pro.isDiscontinued()==true ? 1: 0);
            prestate.setInt(10, pro.getProductID());
            
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        
        return n;

    }
    
    //update neu no la 1 phan tu thuoc 2 bang => ko update
    //th employees no self join thi check id employee co phai la nguoi quan ly chinh no tu 
    //report to hay khong, neu co ko xoa
    //cap nhat trang thai 1 sp thanh discontinued
    public int updateDiscontinue(int pid, int discontinue){
        int n = 0;
        String sql = "UPDATE [Products]\n"
                + "   SET [Discontinued] = ?"
                + " WHERE ProductID=?";

        try {
            PreparedStatement preState = con.prepareStatement(sql);
            preState.setInt(1, discontinue);
            preState.setInt(2, pid);
            n = preState.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
        
    }
    
    //Xoa 1 phan tu trong bang thep productid
    public int removeProduct(int pid){
        int n = 0;
        //check foreign key [order details]
        String sql = "SELECT * FROM [Order Details] where [ProductID] =" + pid;
        ResultSet rs = this.getData(sql); //thuc hien cau lenh truy van va lay kq luu rs
        try {
            if(rs.next()){ //neu primary key ton tai foreign key => ko xoa
                updateDiscontinue(pid, 1); //neu trang thai la 1 => ko xoa chi chay cau lenh update
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
         }
        
        
        String sqlDelete = "DELETE FROM Products WHERE ProductID =" + pid;
        try {
            Statement st = con.createStatement();
            n = st.executeUpdate(sqlDelete);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    

//         
//    public void displayAll() {
//        String sql = "Select * FROM Products";
//        try {
//            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); //gui cau lenh sql
//            ResultSet rs = st.executeQuery(sql); //lay du lieu table tu db
//            while(rs.next()){
//            int productID = rs.getInt("ProductID");
//            String productName = rs.getString("ProductName");
//            int supplierID = rs.getInt("SupplierID");
//            int categoryID = rs.getInt("CategoryID");
//            String quantityPerUnit = rs.getString("QuantityPerUnit");
//            double unitPrice = rs.getDouble("UnitPrice");
//            int unitsInStock = rs.getInt("UnitsInStock");
//            int unitsOnOrder = rs.getInt("UnitsOnOrder");
//            int reorderLevel = rs.getInt("ReorderLevel");
//            boolean discontinued = rs.getBoolean("Discontinued");
//            
//            Products product = new Products(productID, productName, supplierID, 
//                    categoryID, quantityPerUnit, unitPrice, unitsInStock, 
//                    unitsOnOrder, reorderLevel, discontinued);
//            
//            System.out.println(product);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    // }
//    public static void main(String[] args) {
//        DAOProducts dao = new DAOProducts();
//        Vector<Products> vector = dao.getProducts("SELECT * FROM Products");
//        for (Products products : vector) {
//            System.out.println(products);
//        }
//
//    }
    
    
//    
//    public static void main(String[] args) {
//        DAOProducts dao = new DAOProducts();
//        Products pro = new Products(1, "Laptop", 2, 3,
//                "demo", 10, 20, 23,
//                23, true);
//        int n = dao.insertProduct(pro); //thay bang insertProduct
//        if (n < 0) {
//            System.out.println("Fail insert !");
//        }
//
//        int n = dao.removeProduct(92); //xoa id thu
//        if(n < 0){
//            System.out.println("FAIL DELETE");
//        }
//        Vector<Products> vector = dao.getProducts("SELECT * FROM Products");
//        for (Products products : vector) {
//            System.out.println(products);
//        }
//    }

}
