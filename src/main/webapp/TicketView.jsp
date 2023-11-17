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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Ticket</title>
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
        <a href="${pageContext.request.contextPath}/Navigate.jsp">Navigation</a>
    </div>
    <div>
        <h3 style="color: darkslateblue;font-weight: 600;margin-left: 2%">Ticket</h3>
    </div>
    <div class="table-class">
        <div>
            <div class="input-group" style="width: 100%;height: 46px">
                <sec:authorize access="hasAnyRole('User','EventPlanner')">
                <a type="button" class="btn btn-primary btn-info" href="${pageContext.request.contextPath}/eventView?method=findOrder"
                >Call Back</a>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('Admin')">
                    <a type="button" class="btn btn-primary btn-info" href="${pageContext.request.contextPath}/eventView?method=admin"
                    >Call Back</a>
                </sec:authorize>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead>
                <th class="table_th">serial number</th>
                <th>price</th>
                <th>section</th>
                <th>ticketId</th>
                <th>Event</th>
                <th>orderId</th>
                </thead>
                <tbody>
                <c:forEach items="${tickets}" var="ticket" varStatus="status">
                    <td>${status.count}</td>
                    <td>${ticket.price.amount}</td>
                    <td>${ticket.section}</td>
                    <td>${ticket.ticketId}</td>
                    <td>${eventName}</td>
                    <td>${ticket.orderId}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>
