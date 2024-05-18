<%-- 
    Document   : insertCategories
    Created on : Apr 19, 2024, 4:32:08 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Categories"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <% 
           Vector<Categories> vector = (Vector<Categories>)request.getAttribute("vector");
        %>

        <form action="categoriescontroller" method="post">
            <table>
                <tr>
                   <td>categoriesName</td>
                    <td>
                        <input type="text" name="categoryName" > 
                    </td>
                </tr>

                <tr>
                    <td>description</td>
                    <td>
                        <input type="text" name="description" > 
                    </td>
                </tr>

                <tr>
                    <td> <input type="submit" name="submit" value="insert Categories"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="insertCategories">
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
