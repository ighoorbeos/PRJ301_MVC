/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author NgocHung_ighoorbeos
 */
import entity.Categories;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class DAOCategories extends DBConnect {

    //String sql = "SELECT * FROM Categories";
    public Vector<Categories> getCategorieses(String sql) {
        Vector<Categories> vector = new Vector<Categories>();
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                String description = rs.getString("Description");

                Categories categories = new Categories(categoryID, categoryName, description);

                vector.add(categories);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

public int insertCategories(Categories cate) {
    int n = 0;
    String sql = "INSERT INTO [dbo].[Categories]\n"
            + "           ([CategoryName]\n"
            + "           ,[Description])\n" // Closing parenthesis added here
            + "     VALUES(?,?)";

    try {
        PreparedStatement prestate = con.prepareStatement(sql);
        prestate.setString(1, cate.getCategoryName());
        prestate.setString(2, cate.getDescription());

        n = prestate.executeUpdate();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return n;
}


    public int updateCategoryName(int cateid, String cateName) {
        int n = 0;
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [CategoryName] = ?>\n"
                + " WHERE CategoryID = ?";
        PreparedStatement prestate;
        try {
            prestate = con.prepareStatement(sql);
            prestate.setString(1, cateName);
            prestate.setInt(2, cateid);
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //update mot bang 
    public int updateCategory(Categories cate) {
        int n = 0;
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [CategoryName] = ?\n"
                + "      ,[Description] = ?\n"
                + " WHERE CategoryID = ?";

        PreparedStatement prestate;
        try {
            prestate = con.prepareStatement(sql);
            prestate.setString(1, cate.getCategoryName());
            prestate.setString(2, cate.getDescription());
            prestate.setInt(3, cate.getCategoryID());
            n = prestate.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //xoa phan tu trong bang category
    public int removeCategory(int cateid) {
        int n = 0;
        String sql = "SELECT * FROM [Categories] WHERE CategoryID =" + cateid;
        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) {
                //neu co dk xoa bang dien vao
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //Neu ko can check phan tu , xoa
        String sqlDelete = "DELETE FROM [dbo].[Categories]\n"
                + "      WHERE CategoryID = " + cateid;
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
    DAOCategories cate = new DAOCategories();
    Categories categori = new Categories(9, "Clock", "TRUE");

    // Attempt to update the category
    int n = cate.updateCategory(categori);
  
    // Check if the update was successful
    if(n > 0 ){
        System.out.println("Update successful");
    } else {
        System.out.println("Update failed");
    }

    // Retrieve all categories after update
    Vector<Categories> vector = cate.getCategorieses("SELECT * FROM Categories");
    for (Categories categories : vector) {
        System.out.println(categories);
    }
}

}
