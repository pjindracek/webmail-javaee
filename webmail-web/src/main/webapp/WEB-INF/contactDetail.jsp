<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="htmlHeadCommon.jspf" %>
        <title>Contact detail</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container">
            <p>${contact.surname}, ${contact.name}</p>
            <p>${contact.email}</p>
            <p><a href="updateContact?id=${contact.id}">Edit</a> |
                <a href="deleteContact?id=${contact.id}">Delete</a> | <a href="contactList">Contact list</a></p>
        </div>
        <%@include file="footer.jspf" %>
    </body>
</html>
