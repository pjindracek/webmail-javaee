<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/htmlHeadCommon.jspf" %>
        <title>Webmail</title>
    </head>
    <body>
        <%@include file="/WEB-INF/header.jspf" %>
        <div class="container">
            <a href="user/login">Login</a> or <a href="user/signup">Signup</a>
        </div>
        <%@include file="/WEB-INF/footer.jspf" %>
    </body>
</html>
