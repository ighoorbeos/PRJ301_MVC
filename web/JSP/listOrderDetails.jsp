<%-- 
    Document   : listOrderDetails
    Created on : Apr 25, 2024, 1:57:40 AM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.OrderDetails" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String title = (String) request.getAttribute("title"); %> 
        <title> <%= title %> </title>
    </head>

    <body>
        <p> <a href="orderdetailscontroller?service=insertOrderDetails">Insert OrderDetails</a></p>


        <!-- form search -->
        <form action="orderdetailscontroller" method="get">
            <p>
                <label for="minPrice">Min Price:</label>
                <input type="text" name="minPrice" id="minPrice">
            </p>
            <p>
                <label for="maxPrice">Max Price:</label>
                <input type="text" name="maxPrice" id="maxPrice">
            </p>
            <p>
                <input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="searchByPriceRange">
            </p>
        </form>

        <!--        form sort productID-->
        <!-- Form sort -->
        <form action="orderdetailscontroller" method="get">
            <p>
                <label for="sort">Sort by ProductID:</label>
                <select name="sort" id="sort">
                    <option value="asc">Ascending</option>
                    <option value="desc">Descending</option>
                </select>
                <input type="submit" name="submit" value="Sort">
                <input type="hidden" name="service" value="sortByProductID">
            </p>
        </form>



        <table border="1">
            <% String titleTable = (String) request.getAttribute("titleTable"); %> 
            <caption><%= titleTable %> </caption>

            <tr>
                <th>orderID</th>
                <th>productID</th>
                <th>unitPrice</th>
                <th>quantity</th>
                <th>discount</th>

                <th>update</th>
                <th>delete</th>
            </tr>

            <% 
            Vector<OrderDetails> vector = (Vector<OrderDetails>) request.getAttribute("data");
            if (vector != null) {
                for (OrderDetails orde : vector) { 
            %>
            <tr>
                <td><%= orde.getOrderID() %></td>
                <td><%= orde.getProductID() %></td>
                <td><%= orde.getUnitPrice() %></td>
                <td><%= orde.getQuantity() %></td>
                <td><%= orde.getDiscount() %></td>

                <td><a href="orderdetailscontroller?service=updateOrderDetails&orderID=<%=orde.getOrderID()%>" >UPDATE </a> </td>
                <td><a href="orderdetailscontroller?service=deleteOrderDetails&orderID=<%=orde.getOrderID()%>" >DELETE </a></td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="7">No data available</td>
            </tr>
            <% } %>

        </table>
    </body>
</html>
