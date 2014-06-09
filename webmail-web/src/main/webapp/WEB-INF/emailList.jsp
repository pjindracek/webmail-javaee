<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../javascript/jquery-2.1.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#buttonTo").click(function(e) {
                    e.preventDefault();
                    var value = $("#selectTo").val();
                    $("input[name='to']").val(value);
                });
            });
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <h2>Emails of user <strong>${user.email}</strong></h2>
        <form method="get" action="filter">
            <input type="text" name="to" value="" placeholder="Email"/>
            <input type="submit" value="Filter"/>
            <select id="selectTo">
                <option></option>
                <c:forEach items="${contacts}" var="contact">
                    <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                </c:forEach>
            </select>
            <button id="buttonTo">Add</button><br/>
        </form>
        <table>
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
    </body>
</html>
