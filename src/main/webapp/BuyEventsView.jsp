<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 11/09/2023
  Time: 2:56 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            width: 70%;
            margin: 0 auto;
            margin-top: 20px;
        }

        .table-class th {
            background: #f2dede !important;
        }

    </style>
</head>
<body>
<div class="body-class">
    <div class="box">
        <a href="${pageContext.request.contextPath}/Navigate.jsp">Navigation</a>
    </div>
    <div>
        <h3 style="color: darkslateblue;font-weight: 600;margin-left: 2%">Book Event</h3>
    </div>
    <div class="table-class">
        <form class="form-horizontal" action="${pageContext.request.contextPath}/eventView" method="post">
            <input type="text"  value="buyEvents" name="method" style="display: none">
            <input type="number" value="${eventVenuesVo.eventId}" name="eventId" style="display: none">
            <input type="number"  value="${eventVenuesVo.vipcapacity}" name="vipcapacity" style="display: none">
            <input type="number"  value="${eventVenuesVo.moshcapacity}" name="moshcapacity" style="display: none">
            <input type="number"  value="${eventVenuesVo.standingcapacity}" name="standingcapacity" style="display: none">
            <input type="number"  value="${eventVenuesVo.seatedcapacity}" name="seatedcapacity" style="display: none">

            <div class="errorMag"> <span style="color: red;font-size: 13px;margin-left: -17px;">${errorMsg}</span></div>
            <div class="form-group">
                <label for="eventname" class="col-sm-2 control-label">Event Name</label>
                <div class="col-sm-10">
                    <input type="text" name="eventname" class="form-control" value="${eventVenuesVo.eventname}"
                           id="eventname" disabled="disabled">
                </div>
            </div>

            <div class="form-group">
                <label for="vipcapacity" class="col-sm-2 control-label">VIP Capacity</label>
                <div class="col-sm-10">
                    <input type="number" name="vipcapacity" value="${eventVenuesVo.vipcapacity}" class="form-control"
                           id="vipcapacity" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="vipprice" class="col-sm-2 control-label">VIP Price($)</label>
                <div class="col-sm-10">
                    <input type="text" name="vipprice" value="${eventVenuesVo.vipprice}" class="form-control"
                           id="vipprice" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="buyVipcapacity" class="col-sm-2 control-label">Purchase Quantity</label>
                <div class="col-sm-10">
                    <input type="number" name="buyVipcapacity" onchange="vipcapactiyChange()" value="0" class="form-control"
                           id="buyVipcapacity" placeholder="Please Enter BuyVipcapacity">
                </div>
            </div>

            <div class="form-group">
                <label for="moshcapacity" class="col-sm-2 control-label">Mosh Capacity</label>
                <div class="col-sm-10">
                    <input type="number" name="moshcapacity" value="${eventVenuesVo.moshcapacity}" class="form-control"
                           id="moshcapacity" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="moshprice" class="col-sm-2 control-label">Mosh Price($)</label>
                <div class="col-sm-10">
                    <input type="text" name="moshprice" value="${eventVenuesVo.moshprice}" class="form-control"
                           id="moshprice" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="buyMoshcapacity" class="col-sm-2 control-label">Purchase Quantity</label>
                <div class="col-sm-10">
                    <input type="number" name="buyMoshcapacity" onchange="vipcapactiyChange()" value="0" class="form-control"
                           id="buyMoshcapacity" placeholder="Please Enter BuyMoshcapacity">
                </div>
            </div>

            <div class="form-group">
                <label for="standingcapacity" class="col-sm-2 control-label">Standing Capacity</label>
                <div class="col-sm-10">
                    <input type="number" name="standingcapacity" value="${eventVenuesVo.standingcapacity}"
                           class="form-control" id="standingcapacity" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="standingprice" class="col-sm-2 control-label">Standing Price</label>
                <div class="col-sm-10">
                    <input type="text" name="standingprice" value="${eventVenuesVo.standingprice}" class="form-control"
                           id="standingprice" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="buyStandingcapacity" class="col-sm-2 control-label">Purchase Quantity</label>
                <div class="col-sm-10">
                    <input type="number" name="buyStandingcapacity" onchange="vipcapactiyChange()"  value="0" class="form-control"
                           id="buyStandingcapacity" placeholder="Please Enter buyStandingcapacity">
                </div>
            </div>

            <div class="form-group">
                <label for="seatedcapacity" class="col-sm-2 control-label">Seated Capacity</label>
                <div class="col-sm-10">
                    <input type="number" name="seatedcapacity" value="${eventVenuesVo.seatedcapacity}"
                           class="form-control" id="seatedcapacity" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="seatedprice" class="col-sm-2 control-label">Seated Price</label>
                <div class="col-sm-10">
                    <input type="text" name="seatedprice" value="${eventVenuesVo.seatedprice}" class="form-control"
                           id="seatedprice" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="buySeatedcapacity" class="col-sm-2 control-label">Purchase Quantity</label>
                <div class="col-sm-10">
                    <input type="number" name="buySeatedcapacity" onchange="vipcapactiyChange()" value="0" class="form-control"
                           id="buySeatedcapacity" placeholder="Please Enter BuySeatedcapacity">
                </div>
            </div>

            <div class="form-group">
                <label for="totalAmoumt" class="col-sm-2 control-label">Total($)</label>
                <div class="col-sm-10">
                    <input type="number" name="totalAmoumt" class="form-control" id="totalAmoumt" value="0" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                    <a type="submit" href="${pageContext.request.contextPath}/eventView?method=select"
                       class="btn btn-primary btn-info">Call Back</a>
                </div>
            </div>
        </form>
    </div>

</div>
<script src="SourceFile/jquery.min.js"></script>
<script type="text/javascript">
    function vipcapactiyChange() {
        let vipcapacity = $("#vipcapacity").val();
        let vipprice = $("#vipprice").val() == null ? 0 : $("#vipprice").val();
        var buyVipcapacity = $("#buyVipcapacity").val() == null ? 0 : $("#buyVipcapacity").val();
        if (parseInt(buyVipcapacity) > parseInt(vipcapacity)) {
            $("#buyVipcapacity").val(vipcapacity);
            buyVipcapacity = vipcapacity;
        }

        let moshcapacity = $("#moshcapacity").val();
        let moshprice = $("#moshprice").val() == null ? 0 : $("#moshprice").val();
        var buyMoshcapacity = $("#buyMoshcapacity").val() == null ? 0 : $("#buyMoshcapacity").val();
        if (parseInt((buyMoshcapacity)) > parseInt(moshcapacity)) {
            $("#buyMoshcapacity").val(moshcapacity);
            buyMoshcapacity = moshcapacity;
        }

        let standingcapacity = $("#standingcapacity").val();
        let standingprice = $("#standingprice").val() == null ? 0 : $("#standingprice").val();
        let buyStandingcapacity = $("#buyStandingcapacity").val() == null ? 0 : $("#buyStandingcapacity").val();
        if (parseInt(buyStandingcapacity) > parseInt(standingcapacity)) {
            $("#buyStandingcapacity").val(standingcapacity);
            buyStandingcapacity = standingcapacity;
        }

        let seatedcapacity = $("#seatedcapacity").val();
        let seatedprice = $("#seatedprice").val() == null ? 0 : $("#seatedprice").val();
        let buySeatedcapacity = $("#buySeatedcapacity").val() == null ? 0 : $("#buySeatedcapacity").val();
        if (parseInt(buySeatedcapacity) > parseInt(seatedcapacity)) {
            $("#buySeatedcapacity").val(seatedcapacity);
            buySeatedcapacity = seatedcapacity;
        }


        $("#totalAmoumt").val(buyVipcapacity * vipprice + buyMoshcapacity * moshprice + buyStandingcapacity * standingprice + seatedprice * buySeatedcapacity);

    }
</script>
</body>
</html>
