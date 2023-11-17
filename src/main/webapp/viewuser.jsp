<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/9/9
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script src="SourceFile/jquery.min.js"></script>
    <link href="SourceFile/bootstrap.min.css" rel="stylesheet">
    <title>User List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }

        .container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px #b52727;
            padding: 20px;
            margin-top: 20px;
        }

        .table-container {
            overflow-x: auto;
        }

        .table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            table-layout: fixed;
        }

        .table th, .table td {
            text-align: center;
            padding: 10px;
            vertical-align: middle;
        }

        .table th {
            background-color: #f23333;
            color: #fff;
            font-weight: bold;
            width: 25%;
        }

        .table td {
            width: 25%;
            height: 50px;
        }

        .table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .table tr:nth-child(odd) {
            background-color: #fff;
        }

        .add-user-button, .return-button {
            margin-bottom: 10px;
            padding: 5px 20px;
            background-color: #f23333 !important;
            color: #fff;
            transition: background-color 0.3s;
        }

        .add-user-button:hover, .return-button:hover, .table td button:active {
            background-color: #f23333 !important;
        }

        .table td button {
            padding: 5px 10px;
            background-color: #f23333 !important;
            color: #fff;
            transition: background-color 0.3s;
        }

        .table td button:hover {
            background-color: #b52727 !important;
        }
    </style>
</head>
<body>
<div class="container">
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <input type="button" class="btn btn-primary add-user-button" value="Add a user" onclick="location.href=('createuser.jsp')">
        <a href="${pageContext.request.contextPath}/Navigate.jsp" class="btn btn-primary return-button">Return to Navigation</a>
    </div>
    <div class="table-container">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>User Name</th>
                <th>Password</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user" varStatus="loop">
                <tr class="${loop.index % 2 == 0 ? 'even' : 'odd'}">
                    <td>${user.username}</td>
                    <td>${fn:substring(user.password, 0, 1)}****${fn:substring(user.password, fn:length(user.password)-1, fn:length(user.password))}</td>
                    <td>${user.role}</td>
                    <td>
                        <c:if test="${user.role=='EventPlanner'}">
                            <form action="assignevents?method=navigate&user=${user.username}" method="post">
                                <button type="submit" class="btn btn-info">Assigned events</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
