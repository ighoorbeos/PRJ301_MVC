<%-- 
    Document   : updateCategories
    Created on : Apr 20, 2024, 7:11:23 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Categories" %>
<!DOCTYPE html>
<html>
<head>
    <title>TODO supply a title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>


<!--         ĐANG LỖI UPDATE-->
<body>
    <% 
        Vector<Categories> vector = (Vector<Categories>) request.getAttribute("vector");
        Categories cate = null;
        if (vector != null && !vector.isEmpty()) {
            cate = vector.get(0);
        }
    %>
    
    <form action="categoriescontroller" method="post">
        <table>
            <tr>
                <td>CategoryID</td>
                <td> <input type="text" name="CategoryID" readonly value="<%= (cate != null) ? cate.getCategoryID() : "" %>"></td>
            </tr>
            
            <tr>
                <td>CategoryName</td>
                <td> <input type="text" name="CategoryName" id="categoryName" value="<%= (cate != null) ? cate.getCategoryName() : "" %>"></td>
            </tr>

           
            <tr>
                <td>Description</td>
                <td> <input type="text" name="description" id="description" value="<%= (cate != null) ? cate.getDescription() : "" %>"></td>
            </tr>
            
            
            <tr>
                <td> <input type="submit" name="submit" value="Update Categories"></td>
                <td> <input type="reset" name="reset"> 
                    <input type="hidden" name="service" value="updateCategories">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
