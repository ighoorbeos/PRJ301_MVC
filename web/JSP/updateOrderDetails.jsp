<%-- 
    Document   : updateOrderDetails
    Created on : Apr 25, 2024, 2:11:17 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.OrderDetails" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <body>
       
<% 
            Vector<OrderDetails> vector=(Vector<OrderDetails>)request.getAttribute("vector");
            OrderDetails orde=OrderDetails.get(0);
            
        %>

        <form action="orderdetailscontroller" method="post">
            <table>
                 <tr>
                    <td>orderID</td>
                    <td> <input type="text" name="orderID" readonly value="<%=orde.getOrderID()%>" ></td>
                </tr>
                
                <tr>
                    <td>productID</td>
                    <td> <input type="text" name="productID" readonly value="<%=orde.getProductID()%>"></td>
                </tr>

               
                <tr>
                    <td>unitPrice</td>
                    <td>
                        <input type="text" name="unitPrice" id="" value="<%=orde.getUnitPrice()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>quantity</td>
                    <td>
                        <input type="text" name="quantity" id="" value="<%=orde.getQuantity()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>discount</td>
                    <td>
                        <input type="text" name="discount" id="" value="<%=orde.getDiscount()%>"> 
                    </td>
                </tr>
                
                <tr>
                    <td> <input type="submit" name="submit" value="update OrderDetails"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="updateOrderDetails">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
