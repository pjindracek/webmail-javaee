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
                $("#buttonCc").click(function(e) {
                    e.preventDefault();
                    var value = $("input[name='cc']").val();
                    $("input[name='cc']").val(value + (value.length > 0 ? " " : "") + $("#selectCc").val());
                });
                $("#buttonBcc").click(function(e) {
                    e.preventDefault();
                    var value = $("input[name='bcc']").val();
                    $("input[name='bcc']").val(value + (value.length > 0 ? " " : "") + $("#selectBcc").val());
                });
            });
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <c:if test="${not empty error}"><p>${error}</p></c:if>
        <form method="post" action="send">
            <label>To: <input type="text" name="to" value=""/></label>
            <select id="selectTo">
                <option></option>
                <c:forEach items="${contacts}" var="contact">
                    <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                </c:forEach>
            </select>
            <button id="buttonTo">Add</button><br/>
            <label>Cc: <input type="text" name="cc" value=""/></label>
            <select id="selectCc">
                <option></option>
                <c:forEach items="${contacts}" var="contact">
                    <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                </c:forEach>
            </select>
            <button id="buttonCc">Add</button><br/>
            <label>Bcc: <input type="text" name="bcc" value=""/></label>
            <select id="selectBcc">
                <option></option>
                <c:forEach items="${contacts}" var="contact">
                    <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                </c:forEach>
            </select>
            <button id="buttonBcc">Add</button><br/>
            <label>Subject: <input type="text" name="subject" /></label><br/>
            <label>Message: <textarea name="message"></textarea></label><br/>
            <input type="submit" value="Send"/>
        </form>
    </body>
</html>
