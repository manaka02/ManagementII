<%-- 
    Document   : affiche
    Created on : 30 mars 2017, 22:37:31
    Author     : Toavina RALAMBOSOA
--%>

<%@page import="manaka.management.modele.Feuille"%>
<%@page import="java.util.List"%>
<%@page import="manaka.management.modele.Tache"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
	<title>Self-Management</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="webroot/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="webroot/css/style.css" rel="stylesheet" media="screen">
        <!-- MetisMenu CSS -->
        <link href="webroot/css/metisMenu.min.css" rel="stylesheet">
        
        <!--Modal CSS-->
        <link href="webroot/css/w3.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="webroot/css/sb-admin-2.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="webroot/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        
</head>

<% 
    Feuille feuille = (Feuille)request.getAttribute("feuille");
    List<Tache> tacheList = feuille.getTacheList();
%>

<body>
    <%@include file="includes/menuBar.jsp" %>

    <%@include file="includes/modalLink.jsp" %>

    
    <div class="container-fluid">
        <div class="col-sm-12">
            <div class="col-sm-12">
                <h2>Projet : <% out.println(feuille.getDesignation()); %></h2>
                <button type="button" class="w3-btn w3-right" data-toggle="collapse" data-target="#demo">+</button>
                <div id="demo" class="collapse">
                    <form method="POST" action="nouvelleTache" class="form-group col-sm-8 col-sm-offset-2">
                            <div  class="form-group">
                                    <label>Designation</label>
                                    <input type="text" name="designation" required="" class="form-control">
                            </div>

                            <div  class="form-group">
                                <label>Tache mère</label>
                                <select name="parentId"  class="form-control">
                                    <option value="">---</option>

                                </select>
                            </div>

                            <div  class="form-group">
                                    <label>Date début</label>
                                    <input type="date" name="dateDebut" required="" class="form-control">
                            </div>

                            <div  class="form-group col-sm-12">
                                <div class="col-sm-4">
                                    <label>Date fin</label>
                                    <input type="date" name="dateFin" class="form-control">
                                </div>
                                <div class="col-sm-1">ou</div>
                                <div class="col-sm-4">
                                    <label>La durée</label>
                                        <input type="number" name="duree" min="1" class="form-control">
                                </div>                      
                            </div>
                            <div>
                                <input type="submit" value="Ajouter" class="btn btn-primary col-sm-offset-4 col-sm-3">
                            </div>

                    </form> 
                </div>
            </div>
            <hr style="color:blue;" >

            <div class="col-sm-10 col-lg-offset-2">
                <table class="table" >
                    <thead>
                        <tr>
                            <th>Nom de la tache</th>
                            <th>Date début</th>
                            <th>Date fin</th>                            
                            <th>Ressources</th>
                            <th>Statut</th>
                            <th>Action</th>
                        </tr>

                    </thead>
                    <tbody>
                        <%
                            for (Tache tache : tacheList) {
                                    out.println(tache.toRowTable());
                                }
                        %>
                    </tbody>
                </table>

                
            </div>
        </div>
    </div>  
            <script src="webroot/js/jquery.min.js"></script>
        <script src="webroot/js/bootstrap.min.js"></script>
        <script src="webroot/js/modalControl.js"></script>
        <script src="webroot/js/functionList.js"></script>
</body>
