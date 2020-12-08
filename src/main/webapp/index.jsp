<%-- 
    Document   : index
    Created on : Apr 20, 2020, 6:12:28 PM
    Author     : parth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>US Health Government</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 

    </head>

    <body class="container" style="background-color:#D3D3D3;">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    
                    <a class="navbar-brand" href="#"><span class="fa fa-ambulance" style="color:red"></span> US Health</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">Blood Camps</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <% if(session.getAttribute("user") == null ) { %>
                    <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> Sign Up<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="registerUser.html">As a User</a></li>
                            <li><a href="registerBloodBank.html">As a Blood bank</a></li>

                        </ul>
                    </li>
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

        <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="10000">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">

                <div class="item active">
                    <img src="bloodi.jpg" style="width:100%;height:50%;">
                </div>
                <div class="item">
                    <img src="blood2.jpg" style="width:100%;height:50%;">
                </div>

                <div class="item">
                    <img src="blood4.jpg" style="width:100%;">
                </div>

            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </body>
</html>
