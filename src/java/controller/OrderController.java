/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOOrders;
import entity.Orders;
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
public class OrderController extends HttpServlet {

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
        DAOOrders dao = new DAOOrders();
        String service = request.getParameter("service");

        if (service == null) {
            service = "listAllOrder";
        }
        
        if(service.equals("updateOrder")){
            String submit = request.getParameter("submit");
            Vector<Orders> vector = null;
            if(submit ==null){
                vector = dao.getOrder("SELECT * FROM Orders WHERE OrderID="
                +Integer.parseInt(request.getParameter("orderid")));
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/updateOrder.jsp").forward(request, response);
            }else{
                String orderID = request.getParameter("orderID");
                String customerID = request.getParameter("customerID");
                String employeeID = request.getParameter("employeeID");
                String orderDate = request.getParameter("orderDate");
                String requiredDate = request.getParameter("requiredDate");
                String shippedDate = request.getParameter("shippedDate");
                String shipVia = request.getParameter("shipVia");
                String freight = request.getParameter("freight");
                String shipName = request.getParameter("shipName");
                String shipAddress = request.getParameter("shipAddress");
                String shipCity = request.getParameter("shipCity");
                String shipRegion = request.getParameter("shipRegion");
                String shipPostalCode = request.getParameter("shipPostalCode");
                String shipCountry = request.getParameter("shipCountry");

                int orderid = Integer.parseInt(orderID);
                int employeeid = Integer.parseInt(employeeID);
                int shipvia = Integer.parseInt(shipVia);
                double freightt = Integer.parseInt(freight);
                
                Orders or = new Orders(orderid, customerID, 
                        employeeid, orderDate, requiredDate,
                        shippedDate, shipvia, freightt, 
                        shipName, shipAddress, shipCity, shipRegion,
                        shipPostalCode, shipCountry);
                dao.updateOrder(or);
                response.sendRedirect("ordercontroller");
            }
        }

        if(service.equals("deleteOrder")){
            int orderid = Integer.parseInt(request.getParameter("oderid"));
            int n = dao.removeOrders(orderid);
            response.sendRedirect("ordercontroller?service=listAllOrder");
        }
        
        
        if (service.equals("insertOrder")) {
            String submit = request.getParameter("submit");
            Vector<Orders> vector = null;

            if (submit == null) {
                vector = dao.getOrder("SELECT * FROM Orders");
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/insertOrder").forward(request, response);
            } else {
                String customerID = request.getParameter("customerID");
                String employeeID = request.getParameter("employeeID");
                String orderDate = request.getParameter("orderDate");
                String requiredDate = request.getParameter("requiredDate");
                String shippedDate = request.getParameter("shippedDate");
                String shipVia = request.getParameter("shipVia");
                String freight = request.getParameter("freight");
                String shipName = request.getParameter("shipName");
                String shipAddress = request.getParameter("shipAddress");
                String shipCity = request.getParameter("shipCity");
                String shipRegion = request.getParameter("shipRegion");
                String shipPostalCode = request.getParameter("shipPostalCode");
                String shipCountry = request.getParameter("shipCountry");

                int employeeid = Integer.parseInt(employeeID);
                int shipvia = Integer.parseInt(shipVia);
                double freightt = Integer.parseInt(freight);
                
                Orders or = new Orders(0, customerID,
                        employeeid, orderDate, requiredDate,
                        shippedDate, shipvia, freightt,
                        shipName, shipAddress, shipCity, shipRegion,
                        shipPostalCode, shipCountry);
                int n = dao.insertOrder(or);
                response.sendRedirect("ordercontroller");
                
            }
        }
        if (service.equals("listAllOrder")) {
            String submit = request.getParameter("submit");
            Vector<Orders> vector = null;

            if (submit == null) {
                vector = dao.getOrder("SELECT * FROM Orders");
            } else {
                String shipName = request.getParameter("shipName");
                vector = dao.getOrder("SELECT * FROM Orders WHERE ShipName Like '%" + shipName + "%'");
            }

            String title = "Orders Manage";
            String titleTable = "List of Orders";

            RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/listOrder.jsp");

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
