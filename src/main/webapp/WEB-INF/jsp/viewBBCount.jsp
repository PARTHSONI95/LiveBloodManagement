<%-- 
    Document   : viewBBCount
    Created on : Apr 19, 2020, 12:28:42 AM
    Author     : parth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        
        <title>View Blood Count</title>
    </head>
    <body class="container-fluid">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><span class="fa fa-ambulance" style="color:red"></span> US Health</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Home</a></li>
                    <li><a href="#">Blood Camps</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <% if (session.getAttribute("user") == null) { %>
                    <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        <%
                        }else{
                        %>
                    <li><a href="logout.htm" data-toggle="tooltip" data-placement="top" title="Click to LogOut"><span class="glyphicon glyphicon-log-in"></span> ${sessionScope.user}</a></li>
                    <%
                        }
                    %>    
                </ul>
            </div>
        </nav> 
        <h1>Available Blood Group Count (in units)</h1>
        
        <form action="updateNewBloodCountdetails.htm" method="POST">
            <table class="table table-hover" border="1">
            <tr><th>A</th><th>B</th><th>O</th><th>AB</th></tr>
                <tr>
                    <td>${requestScope.BBCount.getBloodGroupAUnits()}</td>
                    <td>${requestScope.BBCount.getBloodGroupBUnits()}</td>
                    <td>${requestScope.BBCount.getBloodGroupOUnits()}</td>
                    <td>${requestScope.BBCount.getBloodGroupABUnits()}</td>
                </tr>
        
        </table>
                <input type="hidden" name="updateBBC" value="${requestScope.BBCount.getBloodId()}">
                <input type="submit" value="Update">        
    </body>
</html>
