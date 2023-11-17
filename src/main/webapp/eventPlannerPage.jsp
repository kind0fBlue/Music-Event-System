<%--
  Created by IntelliJ IDEA.
  User: Johnny Li
  Date: 11/09/2023
  Time: 4:09 pm
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
<h2>User:${user}</h2>

<%--<section class="content-section">
    <form action="eventplanner?method=add" method="post" name="main form">
        <h2>Create Event</h2>
        <div class="input-container">
            Event: <input type="text" placeholder="Enter venue name" name="eventName" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
        </div>
        <div class="input-container">
            Artist: <input type="text" placeholder="Enter artist name" name="artistName" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
            <div class="input-hint">Please enter 1 to 40 characters</div>
        </div>
        <div class="input-container">
            Total Capacity: <input type="number" placeholder="Enter number of people of this event " name="totalCapacity" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
            <div class="input-hint">Please enter number between 1 to 100000</div>
        </div>
        <div class="input-container">
            startTime: <input type="text" placeholder="Enter the start date and time of this event" name="startTime" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
        </div>
        <div class="input-container">
            duration: <input type="number" placeholder="Enter the duration of this event in minutes" name="duration" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
        </div>
        &lt;%&ndash;        <form action="CreateVenue?method=display" method="post" name="drop down form">&ndash;%&gt;
        <div class="input-container">
            <select name="venueId" id="venueId">
                <c:forEach items="${availableList}" var="venue">
                    <option value="${venue.venueId}" the option is: ${venue.venueId}
                    </option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" name="Submit">

    </form>
</section>--%>
<form action="eventplanner?method=display" method="post">
    <input type="hidden" name = "user" value="${user}">
    <input type="submit"  value="Add events">
</form>
<div class="table">
    <table>
        <thead>
        <tr>
            <th>Event ID   </th>
            <th>Event Name  </th>
            <th>Artist Name  </th>
            <th>Total Capacity  </th>
            <th>Event End Time </th>
            <th>Event Start Time  </th>
            <th>Venue Id  </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${eventList}" var="event">
            <%--<c:url var = "tempLink" value = >
                <c:param name = "method" value = "LOAD" />
                <c:param name = "eventId" value = "${event.id}" />
            </c:url >--%>
            <tr>
                <td>${event.id}</td>
                <td>${event.eventName}</td>
                <td>${event.artistName}</td>
                <td>${event.totalCapacity}</td>
                <td>${event.endTime}</td>
                <td>${event.startTime}</td>
                <td>${event.venueId}</td>
                <td>
                    <form action="eventplanner?method=delete" method="post">
                        <input type="hidden" name = "id" value="${fn:escapeXml(event.id)}">
                        <input type="hidden" name = "venueId" value="${fn:escapeXml(event.venueId)}">
                        <input type="hidden" name = "user" value="${user}">
                        <input type="submit"  value="Delete">
                    </form>

                    <form action="viewallbooking?method=display" method="post">
                        <input type="hidden" name = "eventId" value="${fn:escapeXml(event.id)}">
                        <input type="hidden" name = "user" value="${user}">
                        <input type="submit"  value="View All Booking">
                    </form>
                </td>
                <td> <form action="eventplanner?method=update" method="post">
                    <input type="hidden" name = "id" value="${event.id}">
                    <input type="hidden" name = "eventName" value="${event.eventName}">
                    <input type="hidden" name = "artistName" value="${event.artistName}">
                    <input type="hidden" name = "totalCapacity" value="${event.totalCapacity}">
                    <input type="hidden" name = "endTime" value="${event.endTime}">
                    <input type="hidden" name = "startTime" value="${event.startTime}">
                    <input type="hidden" name = "venueId" value="${event.venueId}">
                    <input type="hidden" name = "version" value="${event.version}">
                <input type="submit"  value="Update">
                </form>
            </tr>

        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
