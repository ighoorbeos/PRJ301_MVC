<%-- 
    Document   : listCategories
    Created on : Apr 16, 2024, 3:51:49 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.util.Vector,entity.Categories"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String title=(String) request.getAttribute("title");%>
        <title><%= title %> </title>

    </head>

<body>
    <p>
        <a href="categoriescontroller?service=insertCategories">Insert Categories</a>
        
    </p>
    <!--    the form insert-->
     <form action="categoriescontroller" method="get">
            <p> <input type="text" name="categoryName" id=""></p>
            <p> <input type="submit" name="submit" value="searchName">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllCategories" ><!-- comment -->
            </p>
        </form>
    
     <table border="1">
<!--         hien caption-->
            <% String titleTable = (String) request.getAttribute("titleTable"); %> 
            <caption><%= titleTable %> </caption>

            <tr>
                <th>categoryID</th>
                <th>categoryName</th>
                <th>description</th>
                <th>update</th>
                <th>delete</th>
            </tr>

             <% 
            Vector<Categories> vector = (Vector<Categories>) request.getAttribute("data");
            if (vector != null) {
                for (Categories categories : vector) { 
            %>
            
            
            <tr>
                <td><%=categories.getCategoryID()%></td>
                <td><%=categories.getCategoryName()%></td>
                <td><%=categories.getDescription()%></td>
                <td><a href="categoriescontroller?service=updateCategories&cateid=<%=categories.getCategoryID()%>">Update</a> </td>
                <td><a href="categoriescontroller?service=deleteCategories&cateid=<%=categories.getCategoryID()%>">Delete</a> </td>
            </tr>
            
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="5">No data available</td>
            </tr>
            <% } %>

        </table>
    
    </body>
</html>
