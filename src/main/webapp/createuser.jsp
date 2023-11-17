<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/8/21
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
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

        .content-container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: center;
            margin: 10px;
        }

        h2 {
            margin-top: 0;
            font-size: 24px;
        }

        .input-container {
            margin-bottom: 20px;
        }

        .input-container input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .input-hint {
            font-size: 14px;
            color: #888;
            margin-top: 5px;
        }

        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .Submit, .BackButton {
            padding: 10px;
            font-size: 16px; /* 设置字体大小为16px */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .Submit {
            background-color: #f23333;
            color: #fff;
        }

        .Submit:hover {
            background-color: #b52727;
        }

        .BackButton {
            background-color: #333;
            color: #fff;
        }

        .BackButton:hover {
            background-color: #666;
        }
    </style>
</head>
<body>
<div class="content-container">
    <h2>Create User</h2>
    <form action="CreateUser" method="post">
        <div class="input-container">
            User Name: <input type="text" placeholder="Enter user name" name="userName" onfocus="showInputHint(this)"
                              onblur="hideInputHint(this)" required>
            <div class="input-hint">Please enter 1 to 20 characters</div>
        </div>
        <div class="input-container">
            Password: <input type="text" placeholder="Enter password" name="password" onfocus="showInputHint(this)"
                             onblur="hideInputHint(this)" required>
            <div class="input-hint">Please enter 1 to 20 characters</div>
        </div>
        <div class="input-container">
            Role: <select name="role" required>
            <option value="User">User</option>
            <option value="EventPlanner">Event Planner</option>
        </select>
        </div>
        <input type="submit" class="Submit" value="Submit">
        <a href="${pageContext.request.contextPath}/Navigate.jsp" class="BackButton">Back</a>
    </form>
</div>
</body>
</html>








