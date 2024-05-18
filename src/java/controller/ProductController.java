/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCategories;
import dal.DAOProducts;
import dal.DAOSuppliers;
import entity.Categories;
import entity.Products;
import entity.Suppliers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author NgocHung_ighoorbeos
 */
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOProducts dao = new DAOProducts();
        DAOCategories daocate = new DAOCategories();
        DAOSuppliers daosup = new DAOSuppliers();

        PrintWriter out = response.getWriter();
        String service = request.getParameter("service");

        if (service == null) { //controller chay dau tien => co the service null
            //default
            service = "listAllProduct";
        }

        //update
        if (service.equals("updateProduct")) {
            String submitt = request.getParameter("submit");
            Vector<Categories> vectorcatee = null;
            Vector<Suppliers> vectorsupp = null;
            Vector<Products> vectorproduct = null;
//            for (Categories categories : vectorcate) {
//                categories.getCategoryName();
//            }
            if (submitt == null) { //show form insert
                //get data lay du lieu cho combo box
                vectorcatee = daocate.getCategorieses("SELECT * FROM Categories");
                vectorsupp = daosup.getSuppliers("SELECT * FROM Suppliers");

                request.setAttribute("vectorcatee", vectorcatee);
                request.setAttribute("vectorsupp", vectorsupp);

                vectorproduct = dao.getProducts("SELECT * FROM Products WHERE ProductID="
                        + Integer.parseInt(request.getParameter("pid")));
                request.setAttribute("vectorproduct", vectorproduct);

                request.getRequestDispatcher("/JSP/updateProduct.jsp").forward(request, response);
            } else {
                String id = request.getParameter("ProductID");
                String productName = request.getParameter("productName");
                String supplierID = request.getParameter("supplierID");
                String categoryID = request.getParameter("categoryID");
                String quantityPerUnit = request.getParameter("quantityPerUnit");
                String unitPrice = request.getParameter("unitPrice");
                String unitsInStock = request.getParameter("unitsInStock");
                String unitsOnOrder = request.getParameter("unitsOnOrder");
                String reorderLevel = request.getParameter("reorderLevel");
                String discontinued = request.getParameter("discontinued");

                //check data: empty, duplicate,isnumber...
                //convert:
                int pid = Integer.parseInt(id);
                int supID = Integer.parseInt(supplierID);
                int cateID = Integer.parseInt(categoryID);
                double price = Double.parseDouble(unitPrice);
                int unitStock = Integer.parseInt(unitsInStock);
                int unitOrder = Integer.parseInt(unitsOnOrder);
                int reorder = Integer.parseInt(reorderLevel);
                int discon = Integer.parseInt(discontinued);

                Products pro = new Products(pid, productName, supID,
                        cateID, quantityPerUnit, price, unitStock,
                        unitOrder, reorder, discon == 1 ? true : false);
                dao.updateProduct(pro);

                response.sendRedirect("productcontroller");

            }

        }

        //delete Product
        if (service.equals("deleteProduct")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            int n = dao.removeProduct(pid);
            response.sendRedirect("productcontroller?service=listAllProduct");
        }

        //insert
        if (service.equals("insertProduct")) {
            String submit = request.getParameter("submit");
            Vector<Categories> vectorcate = null;
            Vector<Suppliers> vectorsup = null;

//            for (Categories categories : vectorcate) {
//                categories.getCategoryName();
//            }
            if (submit == null) { //show form insert
                //get data lay du lieu cho combo box
                vectorcate = daocate.getCategorieses("SELECT * FROM Categories");
                vectorsup = daosup.getSuppliers("SELECT * FROM Suppliers");

                request.setAttribute("vectorcate", vectorcate);
                request.setAttribute("vectorsup", vectorsup);

                request.getRequestDispatcher("/JSP/insertProduct.jsp").forward(request, response);
            } else { //insert
                String productName = request.getParameter("productName");
                String supplierID = request.getParameter("supplierID");
                String categoryID = request.getParameter("categoryID");
                String quantityPerUnit = request.getParameter("quantityPerUnit");
                String unitPrice = request.getParameter("unitPrice");
                String unitsInStock = request.getParameter("unitsInStock");
                String unitsOnOrder = request.getParameter("unitsOnOrder");
                String reorderLevel = request.getParameter("reorderLevel");
                String discontinued = request.getParameter("discontinued");

                //check data: empty, duplicate,isnumber...
                //convert:
                int supID = Integer.parseInt(supplierID);
                int cateID = Integer.parseInt(categoryID);
                double price = Double.parseDouble(unitPrice);
                int unitStock = Integer.parseInt(unitsInStock);
                int unitOrder = Integer.parseInt(unitsOnOrder);
                int reorder = Integer.parseInt(reorderLevel);
                int discon = Integer.parseInt(discontinued);
                System.out.println("OK");

                Products pro = new Products(0, productName, supID,
                        cateID, quantityPerUnit, price, unitStock,
                        unitOrder, reorder, discon == 1 ? true : false);
                int n = dao.addProduct(pro);
                //out.print(n);
                System.out.println("n=" + n);
                response.sendRedirect("productcontroller");

            }
        }

        //list all
        if (service.equals("listAllProduct")) {
            //get data from dao

            String submit = request.getParameter("submit");
            Vector<Products> vector = null; //dam bao ko bi loi khi dinh du lieu truy van cu
            if (submit == null) { //chua submit => show all
                vector = dao.getProducts("SELECT * FROM Products");
            } else {  //search
                String pname = request.getParameter("pname");
                vector = dao.getProducts("SELECT * FROM Products WHERE ProductName like '%" + pname + "%'");
            }

            String title = "Product Manage";
            String titleTable = "List of Product";

            RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/listProduct.jsp");

            //set data for view
            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);

            //run:
            dispatch.forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
