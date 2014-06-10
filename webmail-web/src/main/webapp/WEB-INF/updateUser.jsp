<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="htmlHeadCommon.jspf" %>
        <title>Update user</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container">
            <c:if test="${not empty error}"><p>${error}</p></c:if>
            <form method="post" action="postUser" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-md-4"><input type="text" name="name" class="form-control" value="${user.name}"/></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Surname</label>
                    <div class="col-md-4"><input type="text" name="surname" class="form-control" value="${user.surname}"/></div>            
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-md-4"><input type="text" name="email" value="${user.email}" readonly="readonly" class="form-control" style="background-color: #efefef; font-style: italic"/></div>                      
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Password</label>
                    <div class="col-md-4"><input type="password" class="form-control" name="password" value=""/></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Confirmation</label>
                    <div class="col-md-4"><input type="password" class="form-control" name="passwordconf" value=""/></div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-4">
                        <input type="submit" value="Send" class="btn btn-default"/>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
