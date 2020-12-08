<%-- 
    Document   : bloodBankSearch
    Created on : Apr 13, 2020, 2:49:51 AM
    Author     : parth
--%>

<%@page import="java.util.Map"%>
<%@page import="com.myproject.livebloodmanagement.dto.SearchDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Search Results</title>
        <script type="text/javascript">
            function viewCount(bloodId) {
                document.getElementById(bloodId).style.display = "block";
            }

            function disableCount(bloodId){
                document.getElementById(bloodId).style.display = "none";
                
            }
        </script>

    </head>
    <body class="container">
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
            <center><h1>Search Results</h1></center>
        </div>
        <div class="container">
            <%
                Map<Double, SearchDTO> iter = (Map<Double, SearchDTO>) request.getAttribute("searchResults");

                for (Map.Entry<Double, SearchDTO> map : iter.entrySet()) {
                    SearchDTO search = map.getValue();
            %>            
            <div class="well well-lg">
                <p> Blood Bank Name - <%=search.getBloodBank().getbName()%></p>
                <p> Contact Info - <%=search.getBloodBank().getbPhoneNo()%></p>
                <a class="btn btn-primary" onmouseover="viewCount(<%=search.getBloodBank().getbId()%>)" onmouseout="disableCount(<%=search.getBloodBank().getbId()%>)">Blood Count</a>
                <ul class="list-group" style="display:none;" id="<%=search.getBloodBank().getbId()%>">
                    <li class="list-group-item list-group-item-info">A count - <%=search.getBloodCount().getBloodGroupAUnits()%></li>
                    <li class="list-group-item list-group-item-info">B count - <%=search.getBloodCount().getBloodGroupBUnits()%></li>
                    <li class="list-group-item list-group-item-info">O count - <%=search.getBloodCount().getBloodGroupOUnits()%></li>
                    <li class="list-group-item list-group-item-info">AB count - <%=search.getBloodCount().getBloodGroupABUnits()%></li>      
                </ul>

            </div>
            <%
                }
            %>       
        </div>
    </div> 


</body>
</html>
