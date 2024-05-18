<%-- 
    Document   : listCustomer
    Created on : Apr 16, 2024, 4:53:57 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Customers"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String title = (String) request.getAttribute("title"); %> 

        <title> <%=title%> </title>
    </head>
    <body>
        <p> <a href="customercontroller?service=insertCustomer">Insert Customer</a></p>
        
        
        <form action="customercontroller" method="get">
            <p> <input type="text" name="companyName" id=""></p>
            <p> <input type="submit" name="submit" value="searchName">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllCustomer" ><!-- comment -->
            </p>
        </form>


        <table border="1">
            <%String titleTable =(String) request.getAttribute("titleTable"); %> 
            <caption><%= titleTable %> </caption>

            <tr>
                <th>customerID</th>
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
                <th>update</th>
                <th>delete</th>
            </tr>

            <% 
            Vector<Customers> vector = (Vector<Customers>) request.getAttribute("data");
            if(vector != null) {
                for(Customers customers : vector){ 
            %>
            <tr>
                <td><%=customers.getCustomerID()%></td>
                <td><%=customers.getCompanyName()%></td>
                <td><%=customers.getContactName()%></td>
                <td><%=customers.getContactTitle()%></td>
                <td><%=customers.getAddress()%></td>
                <td><%=customers.getCity()%></td>
                <td><%=customers.getRegion()%> </td>
                <td><%=customers.getPostalCode()%></td>
                <td><%=customers.getCountry()%></td>
                <td><%=customers.getPhone()%></td>
                <td><%=customers.getFax()%></td>
                <td><a href="customercontroller?service=updateCustomer&customerID=<%=customers.getCustomerID()%>">UPDATE </a></td>
                <td><a href="customercontroller?service=deleteCustomer&customerID=<%=customers.getCustomerID()%>">DELETE </a></td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="13">No data available</td>
            </tr>
            <%}%>

        </table>


    </body>
</html>
