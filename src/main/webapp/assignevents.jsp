<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/9/10
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assign Events</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px #b52727;
            border-radius: 5px;
        }

        h2 {
            color: #f23333;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #f23333;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }

        .table {
            margin-top: 20px;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            text-align: center;
            padding: 10px;
        }

        th {
            background-color: #f23333;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:nth-child(odd) {
            background-color: #fff;
        }
        .button-container {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .return-button {
            background-color: #f23333;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            margin-left: 10px;
        }
    </style>
</head>

<body>
<div class="container">
    <form action="assignevents?method=assign&user=${user}" method="post" name="assign">
        <h2>User: ${user}</h2>
        <select name="eventAssign">
            <c:forEach items="${list}" var="event">
                <option value="${event.id}">${event.eventName}</option>
            </c:forEach>
        </select>
        <input type="submit" name="Submit" value="Submit" class="submit-button">
    </form>

    <div class="button-container">
        <a class="return-button" href="${pageContext.request.contextPath}/Navigate.jsp">Back to Navigation</a>
    </div>

    <div class="table">
        <table>
            <thead>
            <tr>
                <th>Event ID</th>
                <th>Event Name</th>
                <th>Artist Name</th>
                <th>Total Capacity</th>
                <th>Event End Time</th>
                <th>Event Start Time</th>
                <th>Venue Id</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${eventList}" var="event">
                <tr>
                    <td>${event.id}</td>
                    <td>${event.eventName}</td>
                    <td>${event.artistName}</td>
                    <td>${event.totalCapacity}</td>
                    <td>${event.endTime}</td>
                    <td>${event.startTime}</td>
                    <td>${event.venueId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    function Msg() {
        alert("Try to assign...");
    }
</script>
</body>

</html>
