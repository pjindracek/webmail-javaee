<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="htmlHeadCommon.jspf" %>
        <title>Contact list</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container">
            <h2>Contact list</h2>
            <table class="table">
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
        </div>
        <%@include file="footer.jspf" %>
    </body>
</html>
