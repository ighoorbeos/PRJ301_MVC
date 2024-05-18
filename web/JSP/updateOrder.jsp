<%-- 
    Document   : updateOrder
    Created on : Apr 26, 2024, 9:38:18 AM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Orders" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <% 
             Vector<Employees> vector=(Vector<Employees>)request.getAttribute("vector");
             Employees emp=Employees.get(0);
        %>

        <form action="employeecontroller" method="post">
            <table>
                <tr>
                    <td>orderID</td>
                    <td> <input type="text" name="orderID" readonly value="<%=or.getEmployeeID()%>" ></td>
                </tr>
                <tr>
                    <td>customerID</td>
                    <td> <input type="text" name="customerID" readonly value="<%=or.getCustomerID()%>" ></td>
                </tr>

                <tr>
                    <td>employeeID</td>
                    <td> <input type="text" name="employeeID" id="" value="<%=or.getEmployeeID()%>"></td>
                </tr>

                <tr>
                    <td>orderDate</td>
                    <td> <input type="text" name="orderDate" id="" value="<%or.getOrderDate()%>"></td>
                </tr>


                <tr>
                    <td>requiredDate</td>
                    <td> <input type="text" name="requiredDate" id="" value="<%or.getRequiredDate()%>"></td>
                </tr>
                <tr>
                    <td>shippedDate</td>
                    <td> <input type="text" name="shippedDate" id="" value="<%or.getShippedDate()%>"></td>
                </tr>
                <tr>
                    <td>shipVia</td>
                    <td> <input type="text" name="shipVia" id="" value="<%or.getShipVia()%>"></td>
                </tr>
                 <tr>
                    <td>freight</td>
                    <td> <input type="text" name="freight" id="" value="<%or.getFreight()%>"></td>
                </tr>

                <tr>
                    <td>shipName</td>
                    <td>
                        <input type="text" name="shipName" id="" value="<%or.getShipName()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>shipAddress</td>
                    <td>
                        <input type="text" name="shipAddress" id="" value="<%or.getShipAddress()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>shipCity</td>
                    <td>
                        <input type="text" name="shipCity" id="" value="<%or.getShipCity()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>shipRegion</td>
                    <td>
                        <input type="text" name="shipRegion" id="" value="<%or.getShipRegion()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>countshipPostalCodery</td>
                    <td>
                        <input type="text" name="shipPostalCode" id="" value="<%or.getShipPostalCode()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>shipCountry</td>
                    <td>
                        <input type="text" name="shipCountry" id="" value="<%or.getShipCountry()%>"> 
                    </td>
                </tr>
               
               
                <tr>
                    <td> <input type="submit" name="submit" value="insert Order"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="updateOrder">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
