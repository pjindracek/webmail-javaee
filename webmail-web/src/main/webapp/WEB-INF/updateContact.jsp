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
        <c:if test="${not empty error}"><p>${error}</p></c:if>
        <form method="post" action="postContact?id=${contact.id}">
            Name: <input type="text" name="name" value="${contact.name}"/><br/>
            Surname: <input type="text" name="surname" value="${contact.surname}"/><br/>
            Email: <input type="text" name="email" value="${contact.email}"/><br/>
            <input type="submit" value="Send"/>
        </form>
    </body>
</html>
