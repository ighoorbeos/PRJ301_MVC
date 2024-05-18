<%-- 
    Document   : insertCustomer
    Created on : Apr 19, 2024, 2:42:14 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Customers" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>


        <% 
            Vector<Customers> vector = (Vector<Customers>) request.getAttribute("vector");
           
        %>

        <form action="customercontroller" method="post">
            <table>
                <tr>
                    <td>customerID</td>
                    <td> <input type="text" name="customerID" ></td>
                </tr>
                <tr>
                    <td>companyName</td>
                    <td> <input type="text" name="companyName" ></td>
                </tr>

                <tr>
                    <td>contactName</td>
                   <td> <input type="text" name="contactName" ></td>
                </tr>
                <tr>
                    <td>contactTitle</td>
                    <td> <input type="text" name="contactTitle" ></td>
                </tr>
                <tr>
                    <td>address</td>
                    <td>
                        <input type="text" name="address" > 
                    </td>
                </tr>
                <tr>
                    <td>city</td>
                    <td>
                        <input type="text" name="city" > 
                    </td>
                </tr>
                <tr>
                    <td>region</td>
                    <td>
                        <input type="text" name="region" > 
                    </td>
                </tr>
                <tr>
                    <td>postalCode</td>
                    <td>
                        <input type="text" name="postalCode" > 
                    </td>
                </tr>
                <tr>
                    <td>country</td>
                    <td>
                        <input type="text" name="country" > 
                    </td>
                </tr>
                
                 <tr>
                    <td>phone</td>
                    <td>
                        <input type="text" name="phone" > 
                    </td>
                </tr>
                
                 <tr>
                    <td>fax</td>
                    <td>
                        <input type="text" name="fax" > 
                    </td>
                </tr>
                
                <tr>
                    <td> <input type="submit" name="submit" value="insert Customer"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="insertCustomer">
                    </td>

                </tr>

            </table>

        </form>
        <div>  </div>

        <!--        <a href="productcontroller?service =updateProduct&pid=1">Update </a>
                 <a href="productcontroller?service =deleteProduct&pid=1">Delete </a>-->
    </body>
</html>
