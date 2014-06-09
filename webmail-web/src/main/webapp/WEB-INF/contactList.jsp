<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <h2>Contact list</h2>
        <table>
            <thead>
                <tr>
                    <th>Surname, Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${contacts}" var="con">
                    <tr>
                        <td><a href="contactDetail?id=${con.id}">${con.surname}, ${con.name}</a></td>
                        <td>${con.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
