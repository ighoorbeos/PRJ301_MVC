/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOSuppliers;
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
public class SupplierController extends HttpServlet {

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
        DAOSuppliers dao = new DAOSuppliers();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllSupplier";

        }
        
        if(service.equals("updateSupplier")){
            String submit = request.getParameter("submit");
            Vector<Suppliers> vector = null;
            if(submit ==null){
                vector = dao.getSuppliers("SELECT * FROM Suppliers WHERE SupplierID="
                +Integer.parseInt(request.getParameter("spid")));
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/updateSupplier.jsp").forward(request, response);
            }else{
                String supplierID = request.getParameter("supplierID");
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
                String homePage = request.getParameter("homePage");

                int spid = Integer.parseInt(supplierID);
              
               Suppliers sup = new Suppliers(spid, companyName, contactName,
                       contactTitle, address, city, region, postalCode, country, phone, fax, homePage);
                dao.updateSupplier(sup);
                response.sendRedirect("suppliercontroller");
            }
        }
        
         if(service.equals("deleteSupplier")){
            int orderid = Integer.parseInt(request.getParameter("spid"));
            int n = dao.removeSupplier(orderid);
            response.sendRedirect("suppliercontroller?service=listAllSupplier");
        }
        
        if (service.equals("insertOrder")) {
            String submit = request.getParameter("submit");
            Vector<Suppliers> vector = null;

            if (submit == null) {
                vector = dao.getSuppliers("SELECT * FROM Suppliers");
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/insertSupplier.jsp").forward(request, response);
            } else {
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
                String homePage = request.getParameter("homePage");
               
                Suppliers sup = new Suppliers(0, companyName, contactName, 
                        contactTitle, address, city, region, postalCode, 
                        country, phone, fax, homePage);
                int n = dao.insertSuppliers(sup);
                response.sendRedirect("suppliercontroller");

            }
        }

        if (service.equals("listAllSupplier")) {
            String submit = request.getParameter("submit");
            Vector<Suppliers> vector = null;

            if (submit == null) {
                vector = dao.getSuppliers("SELECT * FROM Suppliers");
            } else {
                String companyName = request.getParameter("companyName");
                vector = dao.getSuppliers("SELECT * FROM Suppliers WHERE CompanyName LIKE '%" + companyName + "%'");

            }

            String title = "Supplier Manage";
            String titleTable = "List of Supplier";

            RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/listSupplier.jsp");

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
