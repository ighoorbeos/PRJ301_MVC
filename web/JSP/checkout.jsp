<%--
    Document   : checkout
    Created on : Apr 26, 2024, 1:51:25 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <form action="CartURL?service=checkOut" method="post">
            <table>
                <% String cusID = (String) request.getAttribute("cusID");
                   int empID = (int) request.getAttribute("empID"); %>

                <tr>
                    <td>Customer ID:</td>
                    <td><input type="text" name="CustomerID" readonly value="<%= cusID %>"></td>
                </tr>
                <tr>
                    <td>Employee ID:</td>
                    <td><input type="text" name="EmployeeID" readonly value="<%= empID %>"></td>
                </tr>
                <tr>
                    <td>Order Date:</td>
                    <td><input type="date" name="OrderDate" required></td>
                </tr>
                <tr>
                    <td>Required Date:</td>
                    <td><input type="date" name="RequiredDate" required></td>
                </tr>
                <tr>
                    <td>Shipped Date:</td>
                    <td><input type="date" name="ShippedDate" required></td>
                </tr>
                <tr>
                    <td>Ship Via:</td>
                    <td><input type="number" name="ShipVia" required></td>
                </tr>
                <tr>
                    <td>Freight:</td>
                    <td><input type="number" name="Freight" required></td>
                </tr>
                <tr>
                    <td>Ship Name:</td>
                    <td><input type="text" name="ShipName" required></td>
                </tr>
                <tr>
                    <td>Ship Address:</td>
                    <td><input type="text" name="ShipAddress" required></td>
                </tr>
                <tr>
                    <td>Ship City:</td>
                    <td><input type="text" name="ShipCity" required></td>
                </tr>
                <tr>
                    <td>Ship Region:</td>
                    <td><input type="text" name="ShipRegion" required></td>
                </tr>
                <tr>
                    <td>Ship Postal Code:</td>
                    <td><input type="text" name="ShipPostalCode" required></td>
                </tr>
                <tr>
                    <td>Ship Country:</td>
                    <td><input type="text" name="ShipCountry" required></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Insert Order"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>