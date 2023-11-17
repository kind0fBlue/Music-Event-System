<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/8/19
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <%-- The title of the web page --%>
    <title>AdministratorCreatesVenue</title>
    <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>

        <%-- Defines a style for a logo element --%>
        .logo {
            display: block; <%-- Block-level elements take up the full width of their parent container and start on a new line --%>
            width: auto; <%--  Setting the width to "auto" will ensure that the image retains its original width aspect ratio --%>
            height: 80px;
            margin-left: 20px; <%-- All sides of the element will have a margin of 20 pixels --%>
            margin-right: 80px;
        }

        <%-- Defines a style for a header section of a webpage --%>
        .header {
            display: flex; <%-- Flexbox layout model allows flexible arrangement of elements within a container --%>
            align-items: center; <%-- Aligns child elements along the vertical axis of the flex container --%>
            position: relative; <%-- Keeps element adjusting its position based on the values of the top, right, bottom, and left properties --%>
        }

        .header-text {
            font-size: 18px;
        }

        <%-- Defines a style for a custom-styled dropdown on a webpage --%>
        .custom-select {
            position: relative;
            display: flex;
            align-items: center; /* Vertically center align the text */
            justify-content: center; /* Horizontally center align the text */
            user-select: none; <%-- Prevents users from selecting and highlighting text content within the element --%>
            cursor: pointer; <%-- Changes the cursor appearance to a pointer when hovering over the element --%>
            margin-right: 50px;
            background-color: white; <%-- Set default background color --%>
            transition: background-color 0.3s ease;
            width: 180px
        }

        .custom-select select {
            display: none;
        }

        .custom-options {
            display: none;
            position: absolute;
            top: 100%;
            left: 0;
            list-style-type: none;
            padding: 0;
            margin: 0;
            border: none;
            background-color: white;
            text-align: left;
            width: 180px;
        }

        .custom-select:hover {
            background-color: #f23333; /* Change background color on hover */
            cursor: pointer; /* Change cursor to pointer on hover */
            border-radius: 50px;
            font-weight: bold;
        }

        .custom-select:hover .custom-options {
            display: block;
        }

        .custom-options li {
            white-space: nowrap;
            display: block;
            padding: 10px;
            text-align: left;
            text-decoration: none;
            color: black;
            font-weight: normal;
            transition: font-weight 0.3s ease;
        }

        .custom-options li:hover {
            font-weight: bold;
        }

        .logout-button {
            margin-left: auto; /* Push the button to the right */
            margin-right: 40px;
            padding: 10px 15px; /* Add padding to the button */
            background-color: #f23333;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .welcome-section {
            padding: 7px;
            background-color: #b52727;
            color: white;
            overflow: hidden;
        }

        .welcome-text {
            animation: scrollText 25s linear infinite;
        }

        @keyframes scrollText {
            0% { transform: translateX(100%); }
            100% { transform: translateX(-100%); }
        }

        .slideshow-container {
            width: 100%;
            height: auto;
            overflow: hidden;
            position: relative;
        }

        .slideshow-container img {
            width: 100%;
            height: auto;
            display: block;
        }

        .image-text {
            position: absolute;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%);
            font-size: 30px;
            color: white;
            animation: blinkText 1s infinite;
        }

        @keyframes blinkText {
            0%, 100% {
                opacity: 0;
            }
            50% {
                opacity: 1;
            }
        }

        .input-container {
            position: relative;
        }

        .input-hint {
            font-size: 14px;
            color: red;
            margin-top: 5px;
            display: none;
        }

        * {box-sizing: border-box}

        container {
            padding: 16px;
        }

        input[type=text], input[type=number] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=number]:focus {
            background-color: #ddd;
            outline: none;
        }

        input[type=submit] {
            background-color: #04AA6D;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        input[type=submit]:hover {
            opacity:1;
        }

        /* Add a blue text color to links */
        a {
            color: red;
        }

    </style>

</head>

<body>

<div class="header">

    <img class="logo" src="${pageContext.request.contextPath}/resources/logo.png" alt="Logo">

    <div class="custom-select">
        <div class="header-text">
            <p>Create</p>
        </div>

        <select>
            <option value="Create Venue">Create Venue</option>
            <option value="Create User Account">Create User Account</option>
        </select>

        <ul class="custom-options">
            <li><a href="createvenue.jsp">Create Venue</a></li>
            <li><a href="createuser.jsp">Create User Account</a></li>
        </ul>
    </div>

    <div class="custom-select">

        <div class="header-text">
            <p>View</p>
        </div>

        <select>
            <option value="View Bookings">View Bookings</option>
            <option value="View Events">View Events</option>
        </select>

        <ul class="custom-options">
            <li><a href="viewbookings.jsp">View Bookings</a></li>
            <li><a href="viewevents.jsp">View Events</a></li>
        </ul>

    </div>

    <a class="logout-button" href="login.jsp">Logout</a>

</div>

<div class="welcome-section">
    <div class="welcome-text">
        Hi, administrator! Welcome!
    </div>
</div>

<div class="slideshow-container">
    <img src="${pageContext.request.contextPath}/resources/venue1.jpg" alt="venue1">
    <img src="${pageContext.request.contextPath}/resources/venue2.jpg" alt="venue2">
    <img src="${pageContext.request.contextPath}/resources/venue3.jpg" alt="venue3">
    <div class="image-text">
        ⬇ Create Venue Now
    </div>
</div>

<script>
    var currentSlide = 0;
    var slides = document.querySelectorAll(".slideshow-container img");

    function showSlide(slideIndex) {
        for (var i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slides[slideIndex].style.display = "block";
    }

    function nextSlide() {
        currentSlide = (currentSlide + 1) % slides.length;
        showSlide(currentSlide);
    }

    function startSlideshow() {
        showSlide(currentSlide);
        setInterval(nextSlide, 2000);
    }

    window.onload = startSlideshow;

    function showInputHint(input) {
        var hint = input.nextElementSibling;
        hint.style.display = 'block';
    }

    function hideInputHint(input) {
        var hint = input.nextElementSibling;
        hint.style.display = 'none';
    }

    function showSuccessMessage() {
        alert("Successfully Created!");
    }

</script>


</br>

<section class="content-section">
    <form action="CreateVenue" method="post">
        <h2>Create Venue</h2>
        <div class="input-container">
            Venue: <input type="text" placeholder="Enter venue name" name="venueName" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
            <div class="input-hint">Please enter 1 to 20 characters</div>
        </div>
        <div class="input-container">
            Location: <input type="text" placeholder="Enter venue location" name="location" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>
            <div class="input-hint">Please enter 1 to 40 characters</div>
        </div>
<%--        <div class="input-container">--%>
<%--            Total Sections: <input type="number" placeholder="Enter number of sections (Max 10)" name="totalSections" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>--%>
<%--            <div class="input-hint">Please enter number between 1 to 10</div>--%>
<%--        </div>--%>

<%--        <b>Sections</b>--%>

<%--        <br/>--%>
<%--        <br/>--%>

<%--        <div class="input-container">--%>
<%--            VIP capacity: <input type="number" placeholder="Enter a number" name="vipCapacity" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>--%>
<%--            <div class="input-hint">Please enter number between 1 to 10000</div>--%>
<%--        </div>--%>
<%--        <div class="input-container">--%>
<%--            Mosh capacity: <input type="number" placeholder="Enter a number" name="moshCapacity" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>--%>
<%--            <div class="input-hint">Please enter number between 1 to 10000</div>--%>
<%--        </div>--%>
<%--        <div class="input-container">--%>
<%--            Standing capacity: <input type="number" placeholder="Enter a number" name="standingCapacity" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>--%>
<%--            <div class="input-hint">Please enter number between 1 to 10000</div>--%>
<%--        </div>--%>
<%--        <div class="input-container">--%>
<%--            Seated capacity: <input type="number" placeholder="Enter a number"  name="seatedCapacity" onfocus="showInputHint(this)" onblur="hideInputHint(this)" required>--%>
<%--        <div class="input-hint">Please enter number between 1 to 10000</div>--%>
<%--        </div>--%>

        </br>

        <input type="submit" class="Submit" onclick="showSuccessMessage()">

    </form>
</section>

<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="column">
                <h3>Contact Us</h3>
                <ul>
                    <li><a href="#">About</a> </li>
                    <li><a href="#"></a> Email</li>
                    <li><a href="#"></a> Phone</li>
                </ul>
            </div>
            <div class="column">
                <h3>Ticket Buyers</h3>
                <ul>
                    <li><a href="#">Browse Events</a> </li>
                    <li><a href="#">Ticket Tracking</a> </li>
                    <li><a href="#">Locations</a> </li>
                </ul>
            </div>
            <div class="column">
                <h3>Event Producers</h3>
                <ul>
                    <li><a href="#">Create Events</a> </li>
                    <li><a href="#">Pricing</a> </li>
                    <li><a href="#">Venue List</a> </li>
                </ul>
            </div>
            <div class="column">
                <h3>Help</h3>
                <ul>
                    <li><a href="#">FAQ</a> </li>
                    <li><a href="#">Developers</a> </li>
                    <li><a href="#">Resources</a> </li>
                </ul>
            </div>
            <div class="column">
                <h3>Social Media Link</h3>
                <div class ="socialMedia">
                    <a href="#"><i class = "fab fa-facebook-f"></i></a>
                    <a href="#"><i class = "fab fa-twitter"></i></a>
                    <a href="#"><i class = "fab fa-instagram"></i></a>
                </div>
            </div>
        </div>
    </div>
</footer>
</body>

</html>
