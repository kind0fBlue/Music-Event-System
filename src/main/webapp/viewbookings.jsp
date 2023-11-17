<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/8/21
  Time: 11:51
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
            <th>Event Id   </th>
            <th>Booking Id </th>
            <th>Username   </th>
            <th>Total Price</th>
            <th>date  </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="order">
            <tr>
                <td>${eventId}</td>
                <td>${order.orderId}</td>
                <td>${order.userName}</td>
                <td>${order.totalPrice}</td>
                <td>${order.date}</td>
                <td>
                    <form action="viewallbooking?method=detail" method="post">
                        <input type="hidden" name = "orderId" value="${fn:escapeXml(order.orderId)}">
                        <input type="submit"  value="View details">
                    </form>

                    <form action="viewallbooking?method=delete" method="post">
                        <input type="hidden" name = "orderId" value="${fn:escapeXml(order.orderId)}">
                        <input type="hidden" name = "eventId" value="${eventId}">
                        <input type="submit"  value="Cancel Booking">
                    </form>
                </td>
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
