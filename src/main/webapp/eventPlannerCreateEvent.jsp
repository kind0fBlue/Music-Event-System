<%--
  Created by IntelliJ IDEA.
  User: Johnny Li
  Date: 12/09/2023
  Time: 3:59 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Event Planner Create Event</title>
    <style>
        select {
            width:100px;
            overflow:hidden;
            white-space:nowrap;
            text-overflow:ellipsis;
        }
        select option {
            width:100px;
            text-overflow:ellipsis;
            overflow:hidden;
        }</style>
</head>
<body>
<section class="content-section">
    <form action="eventplanner?method=add" method="post" name="main form">
        <h2>${user} Create Event</h2>
        <input type="hidden" name = "user" value="${user}">
        <div class="input-container">
            Event: <input type="text" placeholder="Enter venue name" name="eventName" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
        </div>
        <div class="input-container">
            Artist: <input type="text" placeholder="Enter artist name" name="artistName" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
            <div class="input-hint">Please enter 1 to 40 characters</div>
        </div>
<%--        <div class="input-container">--%>
<%--            Total Capacity: <input type="number" placeholder="Enter number of people of this event " name="totalCapacity" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>--%>
<%--            <div class="input-hint">Please enter number between 1 to 100000</div>--%>
<%--        </div>--%>
        <div class="input-container">
            <span style="color: red;font-size: 13px;margin-left: -17px;">${errorMessage}</span>
            <div>startTime: <input type="text" placeholder="Enter the start date and time of this event" name="startTime" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required></div>
        </div>
        <div class="input-container">
            endTime: <input type="text" placeholder="Enter the end date and time of this event" name="endTime" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
        </div>
        <div>
            Venue:
            <select name="venueId" id="venueId">
                <c:forEach items="${availableList}" var="venue">
                    <option value="${venue.venueId}"> ${venue.venueName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="input-container">
            VIP Capacity:
            <input type="number" name="vipCapacity" required />
        </div>
        <div class="input-container">
            VIP Price:
            <input type="number" name="vipPrice" required />
        </div>

        <div class="input-container">
            Mosh Capacity:
            <input type="number" name="moshCapacity" required />
        </div>
        <div class="input-container">
            Mosh Price:
            <input type="number" name="moshPrice" required />
        </div>

        <div class="input-container">
            Standing Capacity:
            <input type="number" name="standingCapacity" required />
        </div>
        <div class="input-container">
            Standing Price:
            <input type="number" name="standingPrice" required />
        </div>

        <div class="input-container">
            Seated Capacity:
            <input type="number" name="seatedCapacity" required />
        </div>
        <div class="input-container">
            Seated Price:
            <input type="number" name="seatedPrice" required />
        </div>
        <input type="submit" name="Submit">

    </form>
</section>

</body>
</html>
