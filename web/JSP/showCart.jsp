<%--
    Document   : showCart
    Created on : Apr 22, 2024, 3:25:54 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.ProductCart" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Detail</title>
    </head>
    <body>

        <h1>Cart Detail</h1>

        <table border="1">
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Subtotal</th>
                <th>Action</th>
            </tr>

            <%
            Vector<ProductCart> vector = (Vector<ProductCart>) request.getAttribute("vectorcart");
            double grandTotal = 0;
            if (vector!= null) {
                for (ProductCart pro : vector) {
                    double subTotal = pro.getUnitPrice() * pro.getQuantity();
                    grandTotal += subTotal;
            %>
            <tr>
                <td><%= pro.getProductID() %></td>
                <td><%= pro.getProductName() %></td>
                <td><%= pro.getUnitPrice() %></td>
                <td><%= pro.getQuantity() %></td>
                <td><%= subTotal %></td>
                <td><a href="CartURL?service=remove&pid=<%=pro.getProductID()%>" >Remove</a></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6">No data available</td>
            </tr>
            <%
            }
            %>

        </table>

        <p>Grand Total: <%= grandTotal %></p>

        <p><a href="CartURL?service=removeAll" >Remove All</a></p>
        <p><a href="CartURL?service=checkOut">Check out</a></p>
        <p><a href="productcontroller?service=listAllProduct">Return</a></p>

    </body>
</html>