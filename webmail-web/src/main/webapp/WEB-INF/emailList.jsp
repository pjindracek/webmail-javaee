<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="htmlHeadCommon.jspf" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#buttonTo").click(function(e) {
                    e.preventDefault();
                    var value = $("#selectTo").val();
                    $("input[name='to']").val(value);
                });
            });
        </script>
        <title>Email list</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container">
            <h2>Emails of user <strong>${user.email}</strong></h2>
            <form method="get" action="filter" class="form-inline">
                <div class="form-group">
                    <input type="text" name="to" value="" placeholder="Email" class="form-control"/>
                </div>
                <div class="form-group">
                    <input type="submit" value="Filter" class="btn btn-default" />
                </div>
                <div class="form-group">
                    <select id="selectTo" class="form-control">
                        <option></option>
                        <c:forEach items="${contacts}" var="contact">
                            <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button id="buttonTo" class="btn btn-default">Add</button>
            </form>
            <table class="table">
                <thead>
                    <tr>
                        <th>To</th>
                        <th>Date</th>
                        <th>Subject</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${emails}" var="email">
                        <tr>
                            <td>${email.to}</td>
                            <td>${email.createdAtFormatted}</td>
                            <td><a href="emailDetail?id=${email.id}">${email.subject}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
