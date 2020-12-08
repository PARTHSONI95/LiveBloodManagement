<%-- 
    Document   : updateBloodCount
    Created on : Apr 19, 2020, 1:51:32 AM
    Author     : parth
--%>

<%@page import="com.myproject.livebloodmanagement.dto.BloodBankDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myproject.livebloodmanagement.dto.BloodCountDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Blood Count</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        
    </head>
    <body class="container-fluid">
        
        <nav class="navbar navbar-inverse">
            <div>
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
        
        
        <% BloodCountDTO bloodCountList = (BloodCountDTO) request.getAttribute("bloodCountToBeUpdated"); %>    
        
        <div class="col-md-4 col-md-offset-4">
            <form class="form-inline" action="confirmToUpdate.htm" method="POST">
                <div>
                    <h2>Blood Count Details (Enter in Units)</h2>
                </div><br/>
                <div class="form-group input-lg">
                    <input type="text" name="ACount" placeholder="A Blood Group Count" value="<%= bloodCountList.getBloodGroupAUnits() %>"/>
                </div><br/><br/>
                <div class="form-group input-lg">
                    <input type="text" name="BCount" placeholder="B Blood Group Count" value="<%= bloodCountList.getBloodGroupBUnits() %>"/>
                </div><br/><br/>
                <div class="form-group input-lg">
                    <input type="text" name="OCount" value="<%= bloodCountList.getBloodGroupOUnits() %>" placeholder="O Blood Group Count" />
                </div><br/><br/>
                <div class="form-group input-lg">
                    <input type="text" name="ABCount" value="<%= bloodCountList.getBloodGroupABUnits() %>" placeholder="AB Blood Group Count" />
                <div><br/><br/>
                    <input class="btn btn-primary mb-2" type="submit" name="Submit" value="Update"/>
                </div><br/>
            </form>
        </div>

    </body>
</html>
