<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="htmlHeadCommon.jspf" %>
        <title>Login</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container">
            <c:if test="${not empty error}"><p>${error}</p></c:if>
            <form method="post" action="sendLogin" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-md-4"><input type="text" name="email" value="" class="form-control"/></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Password</label>
                    <div class="col-md-4"><input type="password" name="password" value="" class="form-control"/></div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-4">
                        <input type="submit" value="Login" class="btn btn-default"/>
                    </div>
                </div>
            </form>
        </div>
        <%@include file="footer.jspf" %>
    </body>
</html>
