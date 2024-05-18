/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCustomers;
import dal.DAOEmployees;
import dal.DAOOrderDetails;
import dal.DAOOrders;
import dal.DAOProducts;
import entity.Customers;
import entity.Employees;
import entity.OrderDetails;
import entity.Orders;
import entity.ProductCart;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;


/**
 *
 * @author NgocHung_ighoorbeos
 */
public class CartController extends HttpServlet {
    
    private static final String CartURL = "\"CartURL?service=showCart\"";

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
        HttpSession session = request.getSession(true);
        DAOProducts dao = new DAOProducts();
        String service = request.getParameter("service");

        if (service.equals("add2cart")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            String key = pid + "";
            Object obj = session.getAttribute(key);

            if (obj instanceof ProductCart) {
                ProductCart procart = (ProductCart) obj;
                procart.setQuantity(procart.getQuantity() + 1);
            } else {
                String sql = "SELECT * FROM Products WHERE ProductID =" + pid;
                Vector<Products> vector = dao.getProducts(sql);
                if (!vector.isEmpty()) {
                    Products pro = vector.get(0);
                    ProductCart procart = new ProductCart(pid, pro.getProductName(), pro.getUnitPrice(), 1);
                    session.setAttribute(key, procart);
                } else {
                    response.sendRedirect(CartURL);
                    return;
                }
            }
            response.sendRedirect("CartURL?service=showCart");
        }

        if (service.equals("showCart")) {
            Vector<ProductCart> vectorcart = new Vector<ProductCart>();
            double grandTotal = 0;
            Enumeration<String> enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = enu.nextElement();
                if (!key.equals("user")) {
                    Object obj = session.getAttribute(key);
                    if (obj instanceof ProductCart) {
                        ProductCart procart = (ProductCart) obj;
                        vectorcart.add(procart);
                        grandTotal += procart.getUnitPrice() * procart.getQuantity();
                    }
                }
            }
            request.setAttribute("vectorcart", vectorcart);
            request.setAttribute("grandTotal", grandTotal);
            request.getRequestDispatcher("/JSP/showCart.jsp").forward(request, response);
        }

        if (service.equals("remove")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            String key = pid + "";
            session.removeAttribute(key);
            response.sendRedirect("CartURL?service=showCart");
        }

        if (service.equals("removeAll")) {
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (!key.equals("user")) {
                    session.removeAttribute(key);
                }
            }
            response.sendRedirect("CartURL?service=showCart");
        }

        if (service.equals("checkout")) {
            DAOCustomers cdao = new DAOCustomers();
            String cusName = (String) session.getAttribute("userCus");
            Vector<Customers> vectorCus = cdao.getCustomer("SELECT * FROM Customers Where ContactName = '" + cusName + "'");
            String cusID = vectorCus.get(0).getCustomerID();

            DAOEmployees edao = new DAOEmployees();
            String empName = (String) session.getAttribute("admin");
            Vector<Employees> vectorEmp = edao.getEmployee("SELECT * FROM Employees Where FirstName = '" + empName + "'");
            int empID = vectorEmp.get(0).getEmployeeID();

            request.setAttribute("cusID", cusID);
            request.setAttribute("empID", empID);

            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("/JSP/checkout.jsp").forward(request, response);
            } else {
                String CustomerID = request.getParameter("CustomerID");
                String EmployeeID = request.getParameter("EmployeeID");
                String OrderDate = request.getParameter("OrderDate");
                String RequiredDate = request.getParameter("RequiredDate");
                String ShippedDate = request.getParameter("ShippedDate");
                String ShipVia = request.getParameter("ShipVia");
                String Freight = request.getParameter("Freight");
                String ShipName = request.getParameter("ShipName");
                String ShipAddress = request.getParameter("ShipAddress");
                String ShipCity = request.getParameter("ShipCity");
                String ShipRegion = request.getParameter("ShipRegion");
                String ShipPostalCode = request.getParameter("ShipPostalCode");
                String ShipCountry = request.getParameter("ShipCountry");

                int empid = Integer.parseInt(EmployeeID);
                int ship = Integer.parseInt(ShipVia);
                double freight = Double.parseDouble(Freight);

                DAOOrders oddao = new DAOOrders();
                int orderID = oddao.insertOrder(new Orders(0, CustomerID, empid,
                        OrderDate, RequiredDate, ShippedDate, ship,
                        freight, ShipName, ShipAddress, ShipCity,
                        ShipRegion, ShipPostalCode, ShipCountry));

                DAOOrderDetails odtdao = new DAOOrderDetails();
                Enumeration<String> enu = session.getAttributeNames();
                while (enu.hasMoreElements()) {
                    String key = enu.nextElement();
                    if (!key.equals("userCus") && !key.equals("userEmp")) {
                        ProductCart productCart = (ProductCart) session.getAttribute(key);
                        odtdao.insertOrderDetails(new OrderDetails(orderID, productCart.getProductID(), productCart.getUnitPrice(), productCart.getQuantity(), 0));
                    }
                }

                session.removeAttribute("userCus");
                session.removeAttribute("admin");
                response.sendRedirect("productcontroller");
            }
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
