<%-- 
    Document   : test
    Created on : Apr 16, 2024, 1:47:26 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       dang1: script : java/servlet
       
       <%  
           int max = 1000; java
           out.println(max); servlet
           %>
           
           
           dang2: expression the bieu thuc
           <h2> max = <% = MAX%> </h2>
           <% for(int i = 10; i <= MAX; i++){ %>
           
           <hr width =" "<% = i %>"><!-- comment -->
           <%}%>
           
           dang3:the khai bao declare _dung khai bao bien global va phuong thuc trong jsp
           <h1 style="color: red"> declare</h1><!-- comment -->
           <%! int MIN = 1;%>
           <%! String getValue(String st){
               return st;
           }
           %>
    </body>
</html>
