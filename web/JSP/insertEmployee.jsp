<%-- 
    Document   : insertEmployee
    Created on : Apr 23, 2024, 2:46:05 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, java.util.HashSet, entity.Employees" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <% 
            Vector<Employees> vector = (Vector<Employees>) request.getAttribute("vector");
            HashSet<String> uniqueTitles = new HashSet<>();
            HashSet<String> uniqueTitleOfCourtesies = new HashSet<>();
        %>

        <form action="employeecontroller" method="post">
            <table>
                <tr>
                    <td>lastName</td>
                    <td> <input type="text" name="lastName" ></td>
                </tr>

                <tr>
                    <td>firstName</td>
                    <td> <input type="text" name="firstName" ></td>
                </tr>


                <tr>
                    <td>title</td>
                    <td>
                        <select name="title" >
                            <% 
                                for (Employees emp : vector) {
                                    if (!uniqueTitles.contains(emp.getTitle())) {
                                        uniqueTitles.add(emp.getTitle());
                            %>

                            <option value="<%=emp.getEmployeeID()%>">
                                <%=emp.getTitle()%></option>
                                <% }
                                   } 
                                %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>titleOfCourtesy</td>
                    <td>
                        <select name="titleOfCourtesy" >
                            <% 
                                for (Employees emp : vector) {
                                    if (!uniqueTitleOfCourtesies.contains(emp.getTitleOfCourtesy())) {
                                        uniqueTitleOfCourtesies.add(emp.getTitleOfCourtesy());
                            %>
                            <option value="<%=emp.getEmployeeID()%>"><%=emp.getTitleOfCourtesy()%></option>
                            <% 
                                } 
                            } 
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>birthDate</td>
                    <td>
                        <input type="number" name="birthDay" min="1" max="31" placeholder="Day"> /
                        <input type="number" name="birthMonth" min="1" max="12" placeholder="Month"> /
                        <input type="number" name="birthYear" min="1900" max="2100" placeholder="Year">
                    </td>
                </tr>
                <tr>
                    <td>hireDate</td>
                    <td>
                        <input type="number" name="hireDay" min="1" max="31" placeholder="Day"> /
                        <input type="number" name="hireMonth" min="1" max="12" placeholder="Month"> /
                        <input type="number" name="hireYear" min="1900" max="2100" placeholder="Year">
                    </td>
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
                    <td>homePhone</td>
                    <td>
                        <input type="text" name="homePhone" > 
                    </td>
                </tr>
                <tr>
                    <td>extension</td>
                    <td>
                        <input type="text" name="extension" > 
                    </td>
                </tr>
                <tr>
                    <td>notes</td>
                    <td>
                        <input type="text" name="notes" > 
                    </td>
                </tr>
                <tr>
                    <td>reportsTo</td>
                    <td>
                        <input type="text" name="reportsTo" > 
                    </td>
                </tr>
                <tr>
                    <td>photoPath</td>
                    <td>
                        <input type="text" name="photoPath" > 
                    </td>
                </tr>
                <tr>
                    <td> <input type="submit" name="submit" value="insert Employee"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="insertEmployee">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
