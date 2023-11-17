<%--
  Created by IntelliJ IDEA.
  User: hcy37
  Date: 2023/9/14
  Time: 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <title>Update Event</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }

        #container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px #b52727;
            padding: 20px;
            margin: 0 auto;
            max-width: 600px;
        }

        h3 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        table {
            width: 100%;
        }

        table td {
            padding: 10px;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .save {
            background-color: #f23333;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .save:hover {
            background-color: #b52727;
        }

        .home-button {
            background-color: #f23333;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-left: 10px;
        }

        .home-button:hover {
            background-color: #b52727;
        }

        /* Clearfix for floating elements */
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }
    </style>
</head>

<body>
<div id="container">
    <h3>Update Event</h3>

    <form action="eventplanner?method=UpdateEvent" method="POST">
        <input type="hidden" name="method" value="UPDATE" />
        <input type="hidden" name="version" value="${THE_EVENT.version}" />
        <input type="hidden" name="eventId" value="${THE_EVENT.eventId}" />
        <input type="hidden" name="venue" value="${venue}" />
        <table>
            <tbody>
            <td><label style="color: red;font-size: 13px;margin-left: -17px;">${errorMessage}</label></td><tr/>
            <tr>
                <td><label>Event name:</label></td>
                <td><input type="text" name="eventName" value="${THE_EVENT.eventname}" /></td>
            </tr>

            <tr>
                <td><label>Artist name:</label></td>
                <td><input type="text" name="artistName" value="${THE_EVENT.artistname}" /></td>
            </tr>

<%--            <tr>--%>
<%--                <td><label>Total capacity:</label></td>--%>
<%--                <td><input type="number" name="totalCapacity" value="${THE_EVENT.totalcapacity}" /></td>--%>
<%--            </tr>--%>

            <tr>
                <td><label>Start time:</label></td>
                <td>
                    <fmt:formatDate value="${THE_EVENT.starttime}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate" />
                    <input type="text" name="startTime" value="${formattedDate}" />
                </td>
            </tr>

            <tr>
                <td><label>End time:</label></td>
                <td>
                    <fmt:formatDate value="${THE_EVENT.endtime}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate" />
                    <input type="text" name="endTime" value="${formattedDate}" />
                </td>
            </tr>

            <tr>
                <td><label>VIP Price:</label></td>
                <td><input type="number" name="vipPrice" value="${THE_EVENT.vipprice}" /></td>
            </tr>
            <tr>
                <td><label>Mosh Price:</label></td>
                <td><input type="number" name="moshPrice" value="${THE_EVENT.moshprice}" /></td>
            </tr>
            <tr>
                <td><label>Standing Price:</label></td>
                <td><input type="number" name="standingPrice" value="${THE_EVENT.standingprice}" /></td>
            </tr>
            <tr>
                <td><label>Seated Price:</label></td>
                <td><input type="number" name="seatedPrice" value="${THE_EVENT.seatedprice}" /></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Save" class="save" />
                    <a href="${pageContext.request.contextPath}/Navigate.jsp" class="home-button">Home</a>
                </td>
            </tr>

            </tbody>
        </table>
    </form>

    <div class="clearfix"></div>

</div>
</body>

</html>
