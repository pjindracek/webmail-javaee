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
        <title>Compose email</title>
    </head>
    <body>
        <%@include file="header.jspf" %>
        <div class="container">
            <c:if test="${not empty error}"><p>${error}</p></c:if>
            <form method="post" action="send" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">To</label>
                    <div class="col-md-4"><input type="text" name="to" value="" class="form-control"/></div>
                    <div class="col-md-2">
                        <select id="selectTo" class="form-control">
                            <option></option>
                            <c:forEach items="${contacts}" var="contact">
                                <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <button id="buttonTo" class="btn btn-default">Add</button>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Cc</label>
                    <div class="col-md-4"><input type="text" name="cc" value="" class="form-control"/></div>
                    <div class="col-md-2">
                        <select id="selectCc" class="form-control">
                            <option></option>
                            <c:forEach items="${contacts}" var="contact">
                                <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <button id="buttonCc" class="btn btn-default">Add</button>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Bcc</label>
                    <div class="col-md-4"><input type="text" name="bcc" value="" class="form-control"/></div>
                    <div class="col-md-2">
                        <select id="selectBcc" class="form-control">
                            <option></option>
                            <c:forEach items="${contacts}" var="contact">
                                <option value="${contact.email}">${contact.surname}, ${contact.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <button id="buttonBcc" class="btn btn-default">Add</button>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Subject</label>
                    <div class="col-md-4"><input type="text" name="subject" class="form-control"/></div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Message</label>
                    <div class="col-md-4"><textarea name="message" class="form-control" rows="10"></textarea></div>
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
