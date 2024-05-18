/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOOrderDetails;
import entity.OrderDetails;
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
public class OrderDetailsController extends HttpServlet {

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
        DAOOrderDetails dao = new DAOOrderDetails();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllOrderDetails";
        }

        if (service.equals("updateOrderDetails")) {
            String submit = request.getParameter("submit");
            Vector<OrderDetails> vector = null;

            if (submit == null) {
                vector = dao.getOrderDetails("SELECT * FROM [Order Details] WHERE ProductID="
                        + Integer.parseInt(request.getParameter("orderID")));
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/updateOrderDetails.jsp");
            } else {
                String orderID = request.getParameter("orderID");
                String productID = request.getParameter("productID");
                String unitPrice = request.getParameter("unitPrice");
                String quantity = request.getParameter("quantity");
                String discount = request.getParameter("discount");

                int orderid = Integer.parseInt(orderID);
                int productid = Integer.parseInt(productID);
                double unitprice = Double.parseDouble(unitPrice);
                int quantityy = Integer.parseInt(quantity);
                float discountt = Float.parseFloat(discount);

                OrderDetails orde = new OrderDetails(orderid, productid, 
                        unitprice, quantityy, discountt);
                
                dao.updateOrderDetails(orde);
                response.sendRedirect("orderdetailscontroller");
                
            }
        }

        if (service.equals("deleteOrderDetails")) {
            int orderid = Integer.parseInt(request.getParameter("orderID"));
            int n = dao.removeOrderDetails(orderid);
            response.sendRedirect("orderdetailscontroller?service=listAllOrderDetails");
        }

        if (service.equals("sortByProductID")) {
            String sort = request.getParameter("sort");
            String query = "SELECT * FROM [Order Details] ORDER BY ProductID";
            if (sort != null && sort.equals("desc")) {
                query += " DESC";
            }
            Vector<OrderDetails> vector = dao.getOrderDetails(query);
            String title = "OrderDetails Manage - Sorted by ProductID";
            String titleTable = "Sorted List by ProductID";
            RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/listOrderDetails.jsp");
            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);
            dispatch.forward(request, response);
        }

        if (service.equals("insertOrderDetails")) {
            String submit = request.getParameter("submit");
            Vector<OrderDetails> vector = null;
            if (submit == null) {
                vector = dao.getOrderDetails("SELECT * FROM [Order Details]");
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/insertOrderDetails.jsp").forward(request, response);
            } else { //insert
                String orderID = request.getParameter("orderID");
                String productID = request.getParameter("productID");
                String unitPrice = request.getParameter("unitPrice");
                String quantity = request.getParameter("quantity");
                String discount = request.getParameter("discount");

                int orderid = Integer.parseInt(orderID);
                int productid = Integer.parseInt(productID);
                double unitprice = Double.parseDouble(unitPrice);
                int quantityy = Integer.parseInt(quantity);
                float discountt = Float.parseFloat(discount);

                OrderDetails orde = new OrderDetails(orderid, productid,
                        unitprice, quantityy, discountt);
                response.sendRedirect("orderdetailscontroller");

            }
        }

        if (service.equals("listAllOrderDetails")) {
            Vector<OrderDetails> vector = dao.getOrderDetails("SELECT * FROM [Order Details]");
            String title = "OrderDetails Manage";
            String titleTable = "List of OrderDetails";

            RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/listOrderDetails.jsp");

            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);

            dispatch.forward(request, response);
        } else if (service.equals("searchByUnitPrice")) {
            String unitPrice = request.getParameter("unitPrice");
            Vector<OrderDetails> vector = dao.getOrderDetails("SELECT * FROM [Order Details] WHERE UnitPrice LIKE '%" + unitPrice + "%'");
            String title = "OrderDetails Manage - Search Result";
            String titleTable = "Search Result for Unit Price: " + unitPrice;

            RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/listOrderDetails.jsp");

            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);

            dispatch.forward(request, response);
        } else if (service.equals("searchByPriceRange")) {
            String minPrice = request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice");
            Vector<OrderDetails> vector = dao.getOrderDetails("SELECT * FROM [Order Details] WHERE UnitPrice BETWEEN " + minPrice + " AND " + maxPrice);
            String title = "OrderDetails Manage - Search Result";
            String titleTable = "Search Result for Price Range: " + minPrice + " - " + maxPrice;

            RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/listOrderDetails.jsp");

            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);

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
