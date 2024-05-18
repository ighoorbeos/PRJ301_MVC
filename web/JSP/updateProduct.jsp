<%-- 
    Document   : updateProduct
    Created on : Apr 19, 2024, 2:58:28 PM
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
            Vector<Products> vectorproduct=(Vector<Products>)request.getAttribute("vectorproduct");
            Products pro=vectorproduct.get(0);
            Vector<Categories> vectorcatee = (Vector<Categories>) request.getAttribute("vectorcatee");
            Vector<Suppliers> vectorsupp = (Vector<Suppliers>) request.getAttribute("vectorsupp");
        %>

        <form action="productcontroller" method="post">
            <table>
                 <tr>
                    <td>ProductID</td>
                    <td> <input type="text" name="ProductID" readonly value="<%=pro.getProductID()%>" ></td>
                </tr>
                
                <tr>
                    <td>productName</td>
                    <td> <input type="text" name="productName" id="" value="<%=pro.getProductName()%>"></td>
                </tr>

                <tr>
                    <td>supplier</td>
                    <td>
                        <select name="supplierID" id="" >
                            <% 
                    for (Categories category : vectorcatee) {
                            %>

                            <option value="<%=category.getCategoryID()%>">
                                <%=category.getCategoryName()%></option>
                                <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>category</td>
                    <td>
                        <select name="categoryID" id="">
                            <% 
                     for (Suppliers supplier : vectorsupp) {
                            %>
                            <option value="<%=supplier.getSupplierID()%>"><%=supplier.getCompanyName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>quantityPerUnit</td>
                    <td>
                        <input type="text" name="quantityPerUnit" id="" value="<%=pro.getQuantityPerUnit()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>unitPrice</td>
                    <td>
                        <input type="text" name="unitPrice" id="" value="<%=pro.getUnitPrice()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>unitsInStock</td>
                    <td>
                        <input type="text" name="unitsInStock" id="" value="<%=pro.getUnitsInStock()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>unitsOnOrder</td>
                    <td>
                        <input type="text" name="unitsOnOrder" id=""  value="<%=pro.getUnitsOnOrder()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>reorderLevel</td>
                    <td>
                        <input type="text" name="reorderLevel" id="" value="<%=pro.getReorderLevel()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>discontinued</td>
                    <td>
                        <input type="radio" name="discontinued" value="1"
                              <%=(pro.isDiscontinued()==false?"checked":"")%>> Continued
                        <input type="radio" name="discontinued" value="0"
                                <%=(pro.isDiscontinued()==true?"checked":"")%>> Discontinued
                    </td>
                </tr>
                <tr>
                    <td> <input type="submit" name="submit" value="update Product"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="updateProduct">
                    </td>

                </tr>

            </table>

        </form>
        <div>  </div>

        <!--        <a href="productcontroller?service =updateProduct&pid=1">Update </a>
                 <a href="productcontroller?service =deleteProduct&pid=1">Delete </a>-->
    </body>
</html>
