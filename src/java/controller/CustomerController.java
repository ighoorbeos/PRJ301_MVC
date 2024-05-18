/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCustomers;
import entity.Customers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;

/**
 *
 * @author NgocHung_ighoorbeos
 */
public class CustomerController extends HttpServlet {

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
        DAOCustomers dao = new DAOCustomers();
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllCustomer";

        }

        if (service.equals("login")) {
            String submit = request.getParameter("submit");

            if (submit == null) {

                request.getRequestDispatcher("/JSP/loginCustomer.jsp").forward(request, response);
            } else {

                String userName = request.getParameter("username");
                String pass = request.getParameter("password");
                if (userName.isBlank() || pass.isBlank()) {
                    request.getRequestDispatcher("/JSP/loginCustomer.jsp").forward(request, response);
                }
                boolean flag = dao.login(userName, pass);
                if (flag) {
                    session.setAttribute("userCus", userName);
                }
                response.sendRedirect("productcontroller");
            }
        }

        if (service.equals("updateCustomer")) {
            String submit = request.getParameter("submit");
            Vector<Customers> vector = null;

            if (submit == null) {
                vector = dao.getCustomer("SELECT * FROM Customers");
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/updateCustomer.jsp").forward(request, response);
            } else {
                String customerID = request.getParameter("customerID");
                String companyName = request.getParameter("companyName");
                String contactName = request.getParameter("contactName");
                String contactTitle = request.getParameter("contactTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String postalCode = request.getParameter("postalCode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");

                Customers cus = new Customers(customerID, companyName, contactName,
                        contactTitle, address, city, region, postalCode, country, phone, fax);
                dao.updateCustomer(cus);
                response.sendRedirect("customercontroller");
            }
        }

//        Vector<Customers> cusid = new Vector<Customers>();
//            for (Customers customers : cusid) {
//            customers.getCustomerID();
//        }
        //delete
        //delete
        if (service.equals("deleteCustomer")) {
            String customerID = request.getParameter("customerID");
            if (customerID != null && !customerID.isEmpty()) {
                int n = dao.removeCustomer(customerID);
                if (n > 0) {
                    System.out.println("Customer with ID " + customerID + " removed successfully.");
                } else {
                    System.out.println("Failed to remove customer with ID " + customerID + ".");
                }
            }
            response.sendRedirect("customercontroller?service=listAllCustomer");
            return; // Return to avoid further processing
        }

        //insert
        if (service.equals("insertCustomer")) {
            String submit = request.getParameter("submit");
            Vector<Customers> vector = null;
            if (submit == null) {
                vector = dao.getCustomer("SELECT * FROM Customers");
                request.setAttribute("vector", vector);

                request.getRequestDispatcher("/JSP/insertCustomer.jsp").forward(request, response);
            } else {
                String customerID = request.getParameter("customerID");
                String companyName = request.getParameter("companyName");
                String contactName = request.getParameter("contactName");
                String contactTitle = request.getParameter("contactTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String postalCode = request.getParameter("postalCode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");

                //check data...
                //convert no need because all is string
                Customers cus = new Customers(customerID, companyName, contactName,
                        contactTitle, address, city, region, postalCode, country, phone, fax);
                int n = dao.insertCustomers(cus);

                response.sendRedirect("customercontroller");
            }
        }

        if (service.equals("listAllCustomer")) {
            String submit = request.getParameter("submit");
            Vector<Customers> vector = null;
            if (submit == null) {
                vector = dao.getCustomer("SELECT * FROM Customers");
            } else {
                String companyName = request.getParameter("companyName");
                vector = dao.getCustomer("SELECT * FROM Customers WHERE CompanyName like '%" + companyName + "%'");
            }

            String title = "Customer Manage";
            String titleTable = "List of Customer";

            RequestDispatcher resd = request.getRequestDispatcher("/JSP/listCustomer.jsp");

            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);

            resd.forward(request, response);

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
