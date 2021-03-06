<%-- 
    Document   : adminBBPage
    Created on : Apr 18, 2020, 11:27:55 PM
    Author     : parth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Check Requests</title>
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
                        } else {
                        %>
                    <li><a href="logout.htm" data-toggle="tooltip" data-placement="top" title="Click to LogOut"><span class="glyphicon glyphicon-log-in"></span> ${sessionScope.user}</a></li>
                    <%
                        }
                    %>    
                </ul>
            </div>
        </nav> 
      
        <div class="page-header">
            <center><h1><mark>Registered to handle ${bloodBankAdminUserLogin.getbName()} Blood bank</mark></h1></center>
        </div>
        <table class="table table-hover" border="1">
            
                <tr>
                    <td>${bloodBankAdminUserLogin.getbName()}</td>
                    <td>${bloodBankAdminUserLogin.getbAddress()}</td>
                    <td>${bloodBankAdminUserLogin.getbCity()}</td>
                    <td>${bloodBankAdminUserLogin.getbState()}</td>
                </tr>
        </table>
                <a class="btn btn-danger" href="viewBloodCount.htm">Check Blood Count</a>
    </body>
</html>
