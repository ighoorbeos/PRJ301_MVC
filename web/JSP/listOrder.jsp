<%-- 
    Document   : listOrder
    Created on : Apr 26, 2024, 9:06:45 AM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Orders" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String title = (String) request.getAttribute("title"); %> 
        <title> <%= title %> </title>
    </head>
    <body>
        <p> <a href="ordercontroller?service=insertOrder">Insert Order</a></p>

        <form action="ordercontroller" method="get">
            <p> <input type="text" name="shipName" id=""></p>
            <p> <input type="submit" name="submit" value="searchName">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllOrder" ><!-- comment -->
            </p>
        </form>


        <table border="1">
            <% String titleTable = (String) request.getAttribute("titleTable"); %> 
            <caption><%= titleTable %> </caption>

            <tr>
                <th>orderID</th>
                <th>customerID</th>
                <th>employeeID</th>
                <th>orderDate</th>
                <th>requiredDate</th>
                <th>shippedDate</th>
                <th>shipVia</th>
                <th>freight</th>
                <th>shipName</th>
                <th>shipAddress</th>
                <th>shipCity</th>
                <th>shipRegion</th>
                <th>shipPostalCode</th>
                <th>shipCountry</th>

                <th>update</th>
                <th>delete</th>
            </tr>

            <% 
            Vector<Orders> vector = (Vector<Orders>) request.getAttribute("data");
            if (vector != null) {
                for (Orders or : vector) { 
            %>
            <tr>
                <td><%= or.getOrderID() %></td>
                <td><%= or.getCustomerID() %></td>
                <td><%= or.getEmployeeID() %></td>
                <td><%= or.getOrderDate() %></td>
                <td><%= or.getRequiredDate() %></td>
                <td><%= or.getShippedDate() %></td>
                <td><%= or.getShipVia() %> </td>
                <td><%= or.getFreight() %></td>
                <td><%= or.getShipName() %></td>
                <td><%= or.getShipAddress() %></td>
                <td><%= or.getShipCity() %></td>
                <td><%= or.getShipRegion() %></td>
                <td><%= or.getShipPostalCode() %></td>
                <td><%= or.getShipCountry() %></td>

                <td><a href="ordercontroller?service=updateOrder&oderid=<%=or.getOrderID()%>" >UPDATE </a> </td>
                <td><a href="ordercontroller?service=deleteOrder&oderid=<%=or.getOrderID()%>" >DELETE </a></td>


            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="16">No data available</td>
            </tr>
            <% } %>

        </table>
    </body>
</html>
