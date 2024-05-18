<%-- 
    Document   : listEmployee
    Created on : Apr 23, 2024, 1:43:55 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Employees" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String title = (String) request.getAttribute("title"); %> 
        <title> <%= title %> </title>
    </head>
    <body>

        <p> <a href="employeecontroller?service=insertEmployee">Insert Employee</a></p>

        <form action="employeecontroller" method="get">

            <p> <input type="text" name="firstName" id=""></p>
            <p> <input type="submit" name="submit" value="searchName">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllEmployee" ><!-- comment -->
            </p>
        </form>


        <table border="1">
            <% String titleTable = (String) request.getAttribute("titleTable"); %> 
            <caption><%= titleTable %> </caption>

            <tr>
                <th>employeeID</th>
                <th>lastName</th>
                <th>firstName</th>
                <th>title</th>
                <th>titleOfCourtesy</th>
                <th>birthDate</th>
                <th>hireDate</th>
                <th>address</th>
                <th>city</th>
                <th>region</th>
                <th>postalCode</th>
                <th>country</th>
                <th>homePhone</th>
                <th>extension</th>
                <th>notes</th>
                <th>reportsTo</th>
                <th>photoPath</th>

                <th>update</th>
                <th>delete</th>
            </tr>

            <% 
            Vector<Employees> vector = (Vector<Employees>) request.getAttribute("data");
            if (vector != null) {
                for (Employees emp : vector) { 
            %>
            <tr>
                <td><%= emp.getEmployeeID() %></td>
                <td><%= emp.getLastName() %></td>
                <td><%= emp.getFirstName() %></td>
                <td><%= emp.getTitle() %></td>
                <td><%= emp.getTitleOfCourtesy() %></td>
                <td><%= emp.getBirthDate() %></td>
                <td><%=  emp.getHireDate() %></td>
                <td><%=  emp.getAddress() %> </td>
                <td><%= emp.getCity() %></td>
                <td><%= emp.getRegion() %></td>
                <td><%=  emp.getPostalCode()%></td>
                <td><%=  emp.getCountry()%></td>
                <td><%=  emp.getHomePhone()%></td>
                <td><%=  emp.getExtension()%></td>
                <td><%=  emp.getNotes()%></td>
                <td><%=  emp.getReportsTo()%></td>
                <td><%=  emp.getPhotoPath()%></td>

                <td><a href="employeecontroller?service=updateEmployee&employeeid=<%=emp.getEmployeeID()%>" >UPDATE </a> </td>
                <td><a href="employeecontroller?service=deleteEmployee&employeeid=<%=emp.getEmployeeID()%>" >DELETE </a></td>


            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="19">No data available</td>
            </tr>
            <% } %>

        </table>  
    </body>
</html>
