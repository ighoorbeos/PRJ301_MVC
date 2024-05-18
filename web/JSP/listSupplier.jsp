<%-- 
    Document   : listSupplier
    Created on : Apr 26, 2024, 9:52:28 AM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Suppliers" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String title = (String) request.getAttribute("title"); %> 
        <title> <%= title %> </title>
    </head>
    <body>
        <p> <a href="suppliercontroller?service=insertOrder">Insert Supplier</a></p>

        <form action="suppliercontroller" method="get">
            <p> <input type="text" name="companyName" id=""></p>
            <p> <input type="submit" name="submit" value="searchName">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllSupplier" ><!-- comment -->
            </p>
        </form>


        <table border="1">
            <% String titleTable = (String) request.getAttribute("titleTable"); %> 
            <caption><%= titleTable %> </caption>

            <tr>
                <th>supplierID</th>
                <th>companyName</th>
                <th>contactName</th>
                <th>contactTitle</th>
                <th>address</th>
                <th>city</th>
                <th>region</th>
                <th>postalCode</th>
                <th>country</th>
                <th>phone</th>
                <th>fax</th>
                <th>homePage</th>
               

                <th>update</th>
                <th>delete</th>
            </tr>

            <% 
            Vector<Suppliers> vector = (Vector<Suppliers>) request.getAttribute("data");
            if (vector != null) {
                for (Suppliers sp : vector) { 
            %>
            <tr>
                <td><%= sp.getSupplierID() %></td>
                <td><%= sp.getCompanyName() %></td>
                <td><%= sp.getContactName() %></td>
                <td><%= sp.getContactTitle() %></td>
                <td><%= sp.getAddress() %></td>
                <td><%= sp.getCity() %></td>
                <td><%= sp.getRegion() %> </td>
                <td><%= sp.getPostalCode() %></td>
                <td><%= sp.getCountry() %></td>
                <td><%= sp.getPhone() %></td>
                <td><%= sp.getFax() %></td>
                <td><%= sp.getHomePage() %></td>


                <td><a href="suppliercontroller?service=updateSupplier&spid=<%=sp.getSupplierID()%>" >UPDATE </a> </td>
                <td><a href="suppliercontroller?service=deleteSupplier&spid=<%=sp.getSupplierID()%>" >DELETE </a></td>


            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="14">No data available</td>
            </tr>
            <% } %>

        </table>
    </body>
</html>
