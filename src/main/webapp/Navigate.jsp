<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 17/09/2023
  Time: 9:07 pm
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navigation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .nav-container {
            flex: 1;
            max-width: 300px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: center;
            margin: 10px;
        }

        h1 {
            margin-top: 0;
            font-size: 24px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin: 10px 0;
        }

        a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }

        a:hover {
            color: #f23333;
        }
    </style>
</head>
<body>
<sec:authorize access="hasRole('User')">
<div class="nav-container">
    <h1>User:<sec:authentication property="name"/> Navigation</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/viewevents?method=display">View Events</a></li>
        <li><a href="${pageContext.request.contextPath}/eventView?method=select">Search For and Book Events</a></li>
        <li><a href="${pageContext.request.contextPath}/eventView?method=findOrder">View and Cancel Own Bookings</a></li>
    </ul>
</div>
</sec:authorize>

<sec:authorize access="hasRole('Admin')">
<div class="nav-container">
    <h1>Admin:<sec:authentication property="name"/> Navigation</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/CreateUser">Create and Authenticate Account</a></li>
        <li><a href="${pageContext.request.contextPath}/viewuser?method=display">View User Information and Assign Events</a></li>
        <li><a href="${pageContext.request.contextPath}/CreateVenue">Create Venue</a></li>
        <li><a href="${pageContext.request.contextPath}/eventView?method=admin">View Bookings</a></li>
        <li><a href="${pageContext.request.contextPath}/viewevents?method=display">View All Events</a></li>

    </ul>
</div>
</sec:authorize>

<sec:authorize access="hasRole('EventPlanner')">
<div class="nav-container">
    <h1>Event Planner:<sec:authentication property="name"/> Navigation</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/eventplanner?method=navigate&user=${user}">View and Cancel Events and Bookings</a></li>
        <li><a href="${pageContext.request.contextPath}/eventplanner?method=display">Create Event</a></li>

    </ul>
</div>
</sec:authorize>
</body>
</html>








