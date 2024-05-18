<%-- 
    Document   : updateEmployee
    Created on : Apr 23, 2024, 4:52:16 PM
    Author     : NgocHung_ighoorbeos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Employees" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
             Vector<Employees> vector=(Vector<Employees>)request.getAttribute("vector");
             Employees emp=Employees.get(0);
        %>

        <form action="employeecontroller" method="post">
            <table>
                <tr>
                    <td>EmployeeID</td>
                    <td> <input type="text" name="employeeID" readonly value="<%=emp.getEmployeeID()%>" ></td>
                </tr>

                <tr>
                    <td>lastName</td>
                    <td> <input type="text" name="lastName" id="" value="<%=emp.getLastName()%>"></td>
                </tr>

                <tr>
                    <td>firstName</td>
                    <td> <input type="text" name="firstName" id="" value="<%emp.getFirstName()%>"></td>
                </tr>


                <tr>
                    <td>title</td>
                    <td>
                        <select name="title" id="">
                            <% 
                                for (Employees emp : vector) {
                                    if (!uniqueTitles.contains(emp.getTitle())) {
                                        uniqueTitles.add(emp.getTitle());
                            %>

                            <option value="<%=emp.getEmployeeID()%>" <%= emp.getTitle().equals(emp.getTitle()) ? "selected" : "" %>>
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
                        <select name="titleOfCourtesy" id="">
                            <% 
                                for (Employees emp : vector) {
                                    if (!uniqueTitleOfCourtesies.contains(emp.getTitleOfCourtesy())) {
                                        uniqueTitleOfCourtesies.add(emp.getTitleOfCourtesy());
                            %>
                            <option value="<%=emp.getEmployeeID()%>" <%= emp.getTitleOfCourtesy().equals(emp.getTitleOfCourtesy()) ? "selected" : "" %>>
                                <%=emp.getTitleOfCourtesy()%></option>
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
                        <input type="number" name="birthDay" min="1" max="31" placeholder="Day" value="<%= emp.getBirthDate().getDay()%>"> /
                        <input type="number" name="birthMonth" min="1" max="12" placeholder="Month" value="<%= emp.getBirthDate().getMonth()%>"> /
                        <input type="number" name="birthYear" min="1900" max="2100" placeholder="Year" value="<%= emp.getBirthDate().getYear()%>">
                    </td>
                </tr>
                <tr>
                    <td>hireDate</td>
                    <td>
                        <input type="number" name="hireDay" min="1" max="31" placeholder="Day" value="<%= emp.getHireDate().getDay()%>"> /
                        <input type="number" name="hireMonth" min="1" max="12" placeholder="Month" value="<%= emp.getHireDate().getMonth()%>"> /
                        <input type="number" name="hireYear" min="1900" max="2100" placeholder="Year" value="<%= emp.getHireDate().getYear()%>">
                    </td>
                </tr>

                <tr>
                    <td>address</td>
                    <td>
                        <input type="text" name="address" id="" value="<%emp.getAddress()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>city</td>
                    <td>
                        <input type="text" name="city" id="" value="<%emp.getCity()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>region</td>
                    <td>
                        <input type="text" name="region" id="" value="<%emp.getRegion()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>postalCode</td>
                    <td>
                        <input type="text" name="postalCode" id="" value="<%emp.getPostalCode()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>country</td>
                    <td>
                        <input type="text" name="country" id="" value="<%emp.getCountry()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>homePhone</td>
                    <td>
                        <input type="text" name="homePhone" id="" value="<%emp.getHomePhone()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>extension</td>
                    <td>
                        <input type="text" name="extension" id="" value="<%emp.getExtension()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>notes</td>
                    <td>
                        <input type="text" name="notes" id="" value="<%emp.getNotes()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>reportsTo</td>
                    <td>
                        <input type="text" name="reportsTo" id="" value="<%emp.getReportsTo()%>"> 
                    </td>
                </tr>
                <tr>
                    <td>photoPath</td>
                    <td>
                        <input type="text" name="photoPath" id="" value="<%emp.getPhotoPath()%>"> 
                    </td>
                </tr>
                <tr>
                    <td> <input type="submit" name="submit" value="update Employee"></td>
                    <td> <input type="reset" name="reset"> 
                        <input type="hidden" name="service" value="UpdateEmployee">
                    </td>

                </tr>

            </table>

        </form>
    </body>
</html>
