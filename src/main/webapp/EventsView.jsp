<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 11/09/2023
  Time: 12:46 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="SourceFile/bootstrap.min.css">
    <script src="SourceFile/jquery.min.js"></script>
    <script src="SourceFile/bootstrap.min.js"></script>
    <style>
        .box {
            height: 40px;
            border-bottom: 1px solid lightpink;
            text-align: center;
            border-radius: 6px 6px 6px 6px;
            margin-top: 2px;
            background-color: #2a2b38;
            background-image: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/1462889/pat.svg');
            background-position: bottom center;
            background-repeat: no-repeat;
        }

        .box a {
            width: 80px;
            height: 40px;
            display: inline-block;
            text-align: center;
            line-height: 40px;
            font-size: 12px;
            color: #999999;
            text-decoration: none;
        }

        .box a:hover {
            background-color: #0f6674;
            color: black;
        }

        .links {
            margin-bottom: 15px;
        }

        .links a {
            margin: 0 3px;
        }

        .body-class {
            width: 100%;
            height: 97vh;
            border-radius: 6px;
        }

        .table-class {
            width: 80%;
            margin: 0 auto;
            margin-top: 20px;
        }

        .table-class th {
            background: #f2dede !important;
        }

        .add-btn {
            margin-left: 20px !important;
            border-radius: 3px;
            width: 13%;
        }

        .table_th {
            width: 20px;
        }
    </style>
</head>
<body>
<div class="body-class">
    <div class="box">
        <a href="${pageContext.request.contextPath}/eventView?method=findOrder">Order</a>
        <a href="${pageContext.request.contextPath}/Navigate.jsp">Navigation</a>
    </div>
    <div>
        <h3 style="color: darkslateblue;font-weight: 600;margin-left: 2%">Event</h3>
    </div>
    <div class="table-class">
        <div>
            <div class="input-group" style="width: 100%;height: 46px">
                <form action="${pageContext.request.contextPath}/eventView" method="post">
                    <input type="text" value="${eventName}" name="eventName" class="form-control" style="width:30%"
                           placeholder="please enter eventname">
                    <input type="text" name="method" value="select" style="display: none">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">Go!</button>
                    </span>
                </form>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                <th class="table_th">Serial Number</th>
                <th>Event Name</th>
                <th>Artist Name</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>VIP($)</th>
                <th>Mosh($)</th>
                <th>Standing($)</th>
                <th>Seated($)</th>
                <th>Venue Name</th>
                <th>Location</th>
                <th>Vip Capacity</th>
                <th>Mosh Capacity</th>
                <th>Standing Capacity</th>
                <th>Seated Capacity</th>
                </thead>
                <tbody>
                <c:forEach items="${eventVenuesVos}" var="eventVenues" varStatus="status">
                    <td>${status.count}</td>
                    <td>${eventVenues.eventname}</td>
                    <td>${eventVenues.artistname}</td>
                    <td>${eventVenues.starttime}</td>
                    <td>${eventVenues.endtime}</td>
                    <td>${eventVenues.vipprice}</td>
                    <td>${eventVenues.moshprice}</td>
                    <td>${eventVenues.standingprice}</td>
                    <td>${eventVenues.seatedprice}</td>
                    <td>${eventVenues.venuename}</td>
                    <td>${eventVenues.location}</td>
                    <td>${eventVenues.vipcapacity}</td>
                    <td>${eventVenues.moshcapacity}</td>
                    <td>${eventVenues.standingcapacity}</td>
                    <td>${eventVenues.seatedcapacity}</td>
                    <td>
                        <a type="button" class="btn btn-primary btn-info"
                           href="${pageContext.request.contextPath}/eventView?eventId=${eventVenues.eventId}&method=findEventByEventId"
                        >Buy</a>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
