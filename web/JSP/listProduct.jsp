<%-- 
    Document   : listProduct
    Created on : Apr 16, 2024, 1:43:59 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Products" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String title = (String) request.getAttribute("title"); %> 
        <title> <%= title %> </title>
    </head>
    <body>

        <p align="right"> <a href="CartURL?service=showCart">SHOW CART</a></p>
        <p align="right"> <a href="employeecontroller?service=login">Login</a></p>
        <%
        String userEmp = (String) session.getAttribute("admin");
        if(userEmp==null){
        session.setAttribute("admin", "DEMO");
            }
        if(userEmp!=null){
        %>
        <span style="color: red"> Welcome <%=userEmp%></span></p>
        <%}%>
        <br>
        <p align = "right"><a href="customercontroller?service=login">Login Customer</a>
            <%
        String userCus = (String)session.getAttribute("userCus");
        if(userCus!=null){
        %>
        <span style="color: red"> Welcome <%=userCus%></span></p>
        <%}%>
        
        
        <p> <a href="productcontroller?service=insertProduct">Insert Products</a></p>

        <form action="productcontroller" method="get">
            <p> <input type="text" name="pname" id=""></p>
            <p> <input type="submit" name="submit" value="searchName">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllProduct" ><!-- comment -->
            </p>
        </form>


        <table border="1">
            <% String titleTable = (String) request.getAttribute("titleTable"); %> 
            <caption><%= titleTable %> </caption>

            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>SupplierID</th>
                <th>CategoryID</th>
                <th>QuantityPerUnit</th>
                <th>UnitPrice</th>
                <th>UnitsInStock</th>
                <th>UnitsOnOrder</th>
                <th>ReorderLevel</th>
                <th>Discontinued</th>
                <th>update</th>
                <th>delete</th>
                <th>add2Cart</th>
            </tr>

            <% 
            Vector<Products> vector = (Vector<Products>) request.getAttribute("data");
            if (vector != null) {
                for (Products pro : vector) { 
            %>
            <tr>
                <td><%= pro.getProductID() %></td>
                <td><%= pro.getProductName() %></td>
                <td><%= pro.getSupplierID() %></td>
                <td><%= pro.getCategoryID() %></td>
                <td><%= pro.getQuantityPerUnit() %></td>
                <td><%= pro.getUnitPrice() %></td>
                <td><%= pro.getUnitsInStock() %> </td>
                <td><%= pro.getUnitsOnOrder() %></td>
                <td><%= pro.getReorderLevel() %></td>
                <td><%= pro.isDiscontinued() %></td>
                <td><a href="productcontroller?service=updateProduct&pid=<%=pro.getProductID()%>" >UPDATE </a> </td>
                <td><a href="productcontroller?service=deleteProduct&pid=<%=pro.getProductID()%>" >DELETE </a></td>
                <td><a href="CartURL?service=add2cart&pid=<%=pro.getProductID()%>" >add2cart </a></td>


            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="12">No data available</td>
            </tr>
            <% } %>

        </table>
    

    </body>
</html>
