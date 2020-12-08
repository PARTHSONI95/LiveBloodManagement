<%-- 
    Document   : root
    Created on : Apr 18, 2020, 9:59:19 PM
    Author     : parth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
    <center><h1><mark>Requested Blood Banks for Approval</mark></h1></center>
    <form class="jumbotron" action="approved.htm" method="POST"> 
        <table class="table table-hover" border="1">
            <tr><th>Name</th><th>Address</th><th>City</th><th>State</th><th>Contact No</th><th>Action</th>
            <c:forEach var="bloodbank" items="${requestScope.bloodBankList}">
                <tr>
                    <td>${bloodbank.getbName()}</td>
                    <td>${bloodbank.getbAddress()}</td>
                    <td>${bloodbank.getbCity()}</td>
                    <td>${bloodbank.getbState()}</td>
                    <td>${bloodbank.getbPhoneNo()}</td>
                    <td><input type="checkbox" name="appdeny" value="${bloodbank.getbId()}"/></td>
                </tr>
            </c:forEach>
        </table>
                <select name="action" id="act">
                    <option value="approve">Approve</option>
                    <option value="deny">Deny</option>
                </select>
                <td><input type="Submit" value="OK"></td>
    </form> 
</body>
</html>
