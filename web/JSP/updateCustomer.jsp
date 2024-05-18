<%-- 
    Document   : updateCustomer
    Created on : Apr 22, 2024, 4:31:01 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Customers" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer</title>
    </head>
    <body>

        <% 
            Vector<Customers> vector=(Vector<Customers>)request.getAttribute("vector");
            Customers customers=vector.get(0);
            
        %>

<!--       Đang bị lỗi trùng ID Update-->

        <form action="customercontroller" method="post">
            <table>
                <tr>
                    <td>CustomerID</td>
                    <td> <input type="text" name="customerID" readonly value="<%=customers.getCustomerID()%>" ></td>
                </tr>

                <tr>
                    <td>companyName</td>
                    <td> <input type="text" name="companyName" id="" value="<%=customers.getCompanyName()%>"></td>
                </tr>

                <tr>
                    <td>contactName</td>
                    <td> <input type="text" name="contactName" id="" value="<%=customers.getContactName()%>"></td>

                </tr>
                <tr>
                    <td>contactTitle</td>
                    <td>
                        <select name="contactTitle" id="">
                            <% 
                            for (Customers customer : vector) {
                            %>
                            <option value="<%=customer.getContactTitle()%>"><%=customer.getContactTitle()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>address</td>
                    <td>
                        <input type="text" name="address" id="" value="<%=customers.getAddress()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>city</td>
                    <td>
                        <input type="text" name="city" id="" value="<%=customers.getCity()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>region</td>
                    <td>
                        <input type="text" name="region" id="" value="<%=customers.getRegion()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>postalCode</td>
                    <td>
                        <input type="text" name="postalCode" id=""  value="<%=customers.getPostalCode()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>country</td>
                    <td>
                        <input type="text" name="country" id="" value="<%=customers.getCountry()%>"> 
                    </td>
                </tr>

                <tr>
                    <td>phone</td>
                    <td>
                        <input type="text" name="phone" id="" value="<%=customers.getPhone()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>fax</td>
                    <td>
                        <input type="text" name="fax" id="" value="<%=customers.getFax()%>"> 
                    </td>
                </tr>
                <tr>
                    <td> <input type="submit" name="submit" value="update Cusromer"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="updateCustomer">
                    </td>

                </tr>

            </table>

        </form>

      
    </body>
</html>
