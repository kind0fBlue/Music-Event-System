<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/9/10
  Time: 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Denied</title>
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

        .ErrMsg {
            max-width: 400px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .ErrMsg h2 {
            margin-bottom: 10px;
        }

        .ErrMsg p {
            margin-bottom: 20px;
        }

        .ErrMsg button {
            padding: 10px;
            background-color: #f23333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .ErrMsg button:hover {
            background-color: #b52727;
        }
    </style>
</head>
<body>
<div class="ErrMsg">
    <h2>Access Denied</h2>
    <p>User can't enter this page!</p>
    <a href="${pageContext.request.contextPath}"><button>Return to Login</button></a>
</div>
</body>
</html>

