<%--
  Created by IntelliJ IDEA.
  User: Johnny Li
  Date: 12/09/2023
  Time: 8:54 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="table">
    <table>
        <thead>
        <tr>
            <th>Ticket Id  </th>
            <th>Booking Id   </th>
            <th>Section Name   </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="ticket">
            <tr>
                <td>${ticket.ticketId}</td>
                <td>${ticket.orderId}</td>
                <td>${ticket.section}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%--<script>
$('#deleteEvent').on('show.bs.modal', function(event) {
var button = $(event.relatedTarget)
var eventName = button.data('eventName')
var modal = $(this)
modal.find('#eventName').val(eventName)
})
</script>--%>
</body>
</html>
