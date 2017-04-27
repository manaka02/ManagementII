<%@page import="manaka.management.modele.Utilisateur"%>
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
    List<Utilisateur> userList = feuille.getUtilisateurList();
%>