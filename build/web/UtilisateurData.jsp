<%-- 
    Document   : UtilisateurData
    Created on : 13 avr. 2017, 14:06:16
    Author     : Toavina RALAMBOSOA
--%>

<%@page import="self.management.utils.Utils"%>
<%@page import="self.management.modele.Tache"%>
<%@page import="java.util.List"%>
<%@page import="self.management.modele.Utilisateur"%>
<%@page import="self.management.modele.Feuille"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

        <!-- Custom CSS -->
        <link href="webroot/css/sb-admin-2.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="webroot/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="webroot/js/jquery.min.js"></script>
        <script src="webroot/js/bootstrap.min.js"></script>
        <link href="webroot/css/semantic.min.css" rel="stylesheet" media="screen">
    </head>
    <body>
        
<% 
    Feuille feuille = (Feuille)request.getAttribute("feuille");
    Utilisateur user = (Utilisateur)request.getAttribute("user");
    List<Tache> tacheList = (List<Tache>)request.getAttribute("tacheList");
    
%>
    <%@include file="includes/menuBar.jsp" %>
	    <div class="container">
	    	<h1>Description et charges de travail</h1>
	        <div class="col-sm-12">
	            <div class="ui items">
	                <div class="item">
	                  <div class="image">
	                    <img src="webroot/images/Yves-Saint-Laurent-jogging-suits-men1.jpg">
	                  </div>
	                  <div class="content">
	                    <a class="header"><%=user.getNom() %></a>
	                    <div class="meta">
                                <p><b>mail :</b><%=user.getMail()%></p>
	                      <p><b>date début :</b>22 décembre 2016</p>
	                    </div>
	                    <div class="description">
	                      <p></p>
	                    </div>
	                    <div class="extra">
	                      Pas de details 
	                    </div>
	                  </div>
	                </div>
	            </div>
	            <div>
	            	<h3>Détails charges de travails :</h3>
	            	<ul class="list-unstyled">
	            		<li><b>Projet actuel :</b> <%=feuille.getDesignation() %></li>
	            		
	            		<li style="margin-left : 35px;"><b>Les taches qui lui sont attribués :</b>
	            			<ul class="list-unstyled" >
                                            <% 
                                                int nbjour  = 0;
                                                for (Tache tache : tacheList) {
                                                    int nbTemp = Utils.espaceJour2Date(tache.getDateDebut(), tache.getDateFin());
                                                    nbjour+=nbTemp;
                                            %>
                                            <li  style="margin-left : 35px;"><b><%=tache.getDesignation() %> :</b> <%=nbTemp %> jours (du <%=tache.getDateDebut() %> au <%=tache.getDateFin() %>)</li>
                                                
                                            <% } %>
	            			</ul>
	            		</li>
                                <li><b>Total jours de travail : </b>  <%=nbjour %> jours</li>
	            	</ul>
	            </div>
	        </div>
	    </div>
    </body>
</html>
