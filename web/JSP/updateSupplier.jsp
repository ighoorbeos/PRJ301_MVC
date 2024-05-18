<%-- 
    Document   : updateSupplier
    Created on : Apr 26, 2024, 10:12:29 AM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Suppliers" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
             Vector<Suppliers> vector=(Vector<Suppliers>)request.getAttribute("vector");
             Suppliers sp=Suppliers.get(0);
        %>

        <form action="suppliercontroller" method="post">
            <table>
                <tr>
                    <td>supplierID</td>
                    <td> <input type="text" name="supplierID" readonly value="<%=sp.getSupplierID()%>" ></td>
                </tr>
                <tr>
                    <td>companyName</td>
                    <td> <input type="text" name="companyName" id="" value="<%=sp.getCompanyName()%>" ></td>
                </tr>

                <tr>
                    <td>contactName</td>
                    <td> <input type="text" name="contactName" id="" value="<%=sp.getContactName()%>"></td>
                </tr>

                <tr>
                    <td>contactTitle</td>
                    <td> <input type="text" name="contactTitle" id="" value="<%sp.getContactTitle()%>"></td>
                </tr>


                <tr>
                    <td>address</td>
                    <td> <input type="text" name="address" id="" value="<%sp.getAddress()%>"></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td> <input type="text" name="city" id="" value="<%sp.getCity()%>"></td>
                </tr>
                <tr>
                    <td>region</td>
                    <td> <input type="text" name="region" id="" value="<%sp.getRegion()%>"></td>
                </tr>
                 <tr>
                    <td>postalCode</td>
                    <td> <input type="text" name="postalCode" id="" value="<%sp.getPostalCode()%>"></td>
                </tr>

                <tr>
                    <td>country</td>
                    <td>
                        <input type="text" name="country" id="" value="<%sp.getCountry()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td>
                        <input type="text" name="phone" id="" value="<%sp.getPhone()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>fax</td>
                    <td>
                        <input type="text" name="fax" id="" value="<%sp.getFax()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>homePage</td>
                    <td>
                        <input type="text" name="homePage" id="" value="<%sp.getHomePage()%>"> 
                    </td>
                </tr>
               
               
               
                <tr>
                    <td> <input type="submit" name="submit" value="update Supplier"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="updateSupplier">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
