<%-- 
    Document   : search
    Created on : Apr 19, 2020, 2:36:53 PM
    Author     : parth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         <script>
      // This example adds a search box to a map, using the Google Place Autocomplete
      // feature. People can enter geographical searches. The search box will return a
      // pick list containing a mix of places and predicted search terms.

      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

      function initAutocomplete() {
        
        // Create the search box and link it to the UI element.
        var input = document.getElementById('searchfield');
        var searchBox = new google.maps.places.SearchBox(input);
        

        // Bias the SearchBox results towards current map's viewport.
        
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
          var places = searchBox.getPlaces();

          if (places.length == 0) {
            return;
          }
      });
      }
    </script> 
       <script src="https://maps.googleapis.com/maps/api/js?key=<API_KEY>&libraries=places&callback=initAutocomplete"
         async defer></script>

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
    <div class="form-group">
        <form action="searchBloodBank.htm" method="POST">
            <input class="form-control" type="text" id="searchfield" name="searchfield" placeholder="Enter location"/>
            <br/>
            <center><input class="btn btn-primary mb-2" type="submit" name="submit" value="Search"></center>
        </form>    
    </div>
    </body>
</html>
