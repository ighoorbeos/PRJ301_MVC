<%-- 
    Document   : insertProduct
    Created on : Apr 16, 2024, 3:06:03 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Products, entity.Categories,entity.Suppliers" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>


        <% 
            Vector<Categories> vectorcate = (Vector<Categories>) request.getAttribute("vectorcate");
            Vector<Suppliers> vectorsup = (Vector<Suppliers>) request.getAttribute("vectorsup");
        %>

        <form action="productcontroller" method="post">
            <table>
                <tr>
                    <td>productName</td>
                    <td> <input type="text" name="productName" ></td>
                </tr>

                <tr>
                    <td>supplierID</td>
                    <td>
                        <select name="supplierID" >
                            <% 
                    for (Categories category : vectorcate) {
                            %>

                            <option value="<%=category.getCategoryID()%>">
                                <%=category.getCategoryName()%></option>
                                <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>categoryID</td>
                    <td>
                        <select name="categoryID" >
                            <% 
                     for (Suppliers supplier : vectorsup) {
                            %>
                            <option value="<%=supplier.getSupplierID()%>"><%=supplier.getCompanyName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>quantityPerUnit</td>
                    <td>
                        <input type="text" name="quantityPerUnit" > 
                    </td>
                </tr>
                <tr>
                    <td>unitPrice</td>
                    <td>
                        <input type="text" name="unitPrice" > 
                    </td>
                </tr>
                <tr>
                    <td>unitsInStock</td>
                    <td>
                        <input type="text" name="unitsInStock" > 
                    </td>
                </tr>
                <tr>
                    <td>unitsOnOrder</td>
                    <td>
                        <input type="text" name="unitsOnOrder" > 
                    </td>
                </tr>
                <tr>
                    <td>reorderLevel</td>
                    <td>
                        <input type="text" name="reorderLevel" > 
                    </td>
                </tr>
                <tr>
                    <td>discontinued</td>
                    <td>
                        <input type="radio" name="discontinued" value="1" checked> Continued
                        <input type="radio" name="discontinued" value="0"> Discontinued
                    </td>
                </tr>
                <tr>
                    <td> <input type="submit" name="submit" value="insert Product"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="insertProduct">
                    </td>

                </tr>

            </table>

        </form>
        <div>  </div>

        <!--        <a href="productcontroller?service =updateProduct&pid=1">Update </a>
                 <a href="productcontroller?service =deleteProduct&pid=1">Delete </a>-->
    </body>
</html>
