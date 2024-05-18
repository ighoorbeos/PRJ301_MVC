package controller;

import dal.DAOEmployees;
import entity.Employees;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class EmployeeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        DAOEmployees dao = new DAOEmployees();
        String service = request.getParameter("service");

        if (service == null) {
            service = "listAllEmployee";
        }

        if (service.equals("updateEmployee")) {
            String submit = request.getParameter("submit");
            Vector<Employees> vector = null;

            if (submit == null) {

                vector = dao.getEmployee("SELECT * FROM Employees WHERE EmployeeID="
                        + Integer.parseInt(request.getParameter("employeeid")));
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/updateEmployee.jsp").forward(request, response);
            } else {
                String empid = request.getParameter("employeeID");
                String lastName = request.getParameter("lastName");
                String title = request.getParameter("title");
                String titleOfCourtesy = request.getParameter("titleOfCourtesy");
                String birthDay = request.getParameter("birthDay");
                String birthMonth = request.getParameter("birthMonth");
                String birthYear = request.getParameter("birthYear");
                String birthDate = birthYear + "-" + birthMonth + "-" + birthDay;
                String hireDay = request.getParameter("hireDay");
                String hireMonth = request.getParameter("hireMonth");
                String hireYear = request.getParameter("hireYear");
                String hireDate = hireYear + "-" + hireMonth + "-" + hireDay;
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String postalCode = request.getParameter("postalCode");
                String country = request.getParameter("country");
                String homePhone = request.getParameter("homePhone");
                String extension = request.getParameter("extension");
                String notes = request.getParameter("notes");
                String reportsTo = request.getParameter("reportsTo");
                String photoPath = request.getParameter("photoPath");

                int reportsto = Integer.parseInt(reportsTo);
                int empID = Integer.parseInt(empid);

                Employees emp = new Employees(empID, lastName, lastName,
                        title, titleOfCourtesy, birthDate, hireDate, address,
                        city, region, postalCode, country, homePhone,
                        extension, notes, reportsto, photoPath);
                dao.updateEmployee(emp);
                response.sendRedirect("employeecontroller");
            }
        }

        if (service.equals("deleteEmployee")) {
            int empid = Integer.parseInt(request.getParameter("employeeid"));
            int n = dao.deleteEmployee(empid);
            response.sendRedirect("employeecontroller?service=listAllEmployee");
        }

        if (service.equals("insertEmployee")) {
            String submit = request.getParameter("submit");
            Vector<Employees> vector = null;
            if (submit == null) {
                vector = dao.getEmployee("SELECT * FROM Employees");
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/insertEmployee.jsp").forward(request, response);
            } else {
                String lastName = request.getParameter("lastName");
                String title = request.getParameter("title");
                String titleOfCourtesy = request.getParameter("titleOfCourtesy");
                String birthDay = request.getParameter("birthDay");
                String birthMonth = request.getParameter("birthMonth");
                String birthYear = request.getParameter("birthYear");
                String birthDate = birthYear + "-" + birthMonth + "-" + birthDay;
                String hireDay = request.getParameter("hireDay");
                String hireMonth = request.getParameter("hireMonth");
                String hireYear = request.getParameter("hireYear");
                String hireDate = hireYear + "-" + hireMonth + "-" + hireDay;
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String postalCode = request.getParameter("postalCode");
                String country = request.getParameter("country");
                String homePhone = request.getParameter("homePhone");
                String extension = request.getParameter("extension");
                String notes = request.getParameter("notes");
                String reportsTo = request.getParameter("reportsTo");
                String photoPath = request.getParameter("photoPath");

                int reportsto = Integer.parseInt(reportsTo);

                Employees emp = new Employees(0, lastName,
                        lastName, title, titleOfCourtesy, birthDate,
                        hireDate, address, city, region, postalCode, country,
                        homePhone, extension, notes, reportsto, photoPath);
                int n = dao.insertEmployees(emp);
                response.sendRedirect("employeecontroller");
            }
        }

        if (service.equals("login")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("/JSP/loginEmployee.jsp").forward(request, response);
            } else {
                String userName = request.getParameter("username");
                String passWord = request.getParameter("password");
                if(userName.isBlank() || passWord.isBlank()){
                    request.getRequestDispatcher("/JSP/loginEmployee.jsp").forward(request, response);
                }
                // Assuming your DAO method login() returns true if login is successful
                boolean flag = dao.login(userName, passWord);
                if (flag) {
                    session.setAttribute("admin", userName);
                    // Redirect to the appropriate page after successful login
                   
                } 
                    // Handle incorrect login
                    // Redirect back to login page or show error message
                     response.sendRedirect("productcontroller");
                }
            }
        

         if (service.equals("listAllEmployee")) {
            String submit = request.getParameter("submit");
            Vector<Employees> vector = null;
            if (submit == null) {
                vector = dao.getEmployee("SELECT * FROM Employees");
            } else {
                String firstName = request.getParameter("firstName");
                vector = dao.getEmployee("SELECT * FROM Employees WHERE FirstName LIKE '%" + firstName + "%'");
            }

            String title = "Employee Manage";
            String titleTable = "List of Employee";

            RequestDispatcher resd = request.getRequestDispatcher("/JSP/listEmployee.jsp");

            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);

            resd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
