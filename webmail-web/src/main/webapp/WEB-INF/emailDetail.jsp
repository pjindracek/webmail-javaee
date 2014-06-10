<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="htmlHeadCommon.jspf" %>
        <title>Email detail</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container">
            <table>
                <tr>
                    <th>To</th>
                    <td>${email.to}</td>
                </tr>
                <tr>
                    <th>Cc</th>
                    <td>${email.cc}</td>
                </tr>
                <tr>
                    <th>Bcc</th>
                    <td>${email.bcc}</td>
                </tr>
                <tr>
                    <th>Subject</th>
                    <td>${email.subject}</td>
                </tr>
                <tr>
                    <th>Date</th>
                    <td>${email.createdAtFormatted}</td>
                </tr>
                <tr>
                    <th>Message</th>
                    <td>${email.message}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
