<%-- 
    Document   : affiche
    Created on : 30 mars 2017, 22:37:31
    Author     : Toavina RALAMBOSOA
--%>

    <%@include file="includes/menuBarLink.jsp" %>

<body>
    <%@include file="includes/menuBar.jsp" %>

    <%@include file="includes/modalLink.jsp" %>

    <div id="modalMobile" class="w3-modal">
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-closebtn w3-hover-red w3-container w3-padding-16 w3-display-topright">×</span>
        <div class="w3-modal-content w3-card-8 w3-animate-zoom" style="max-width:600px">
          <header class="w3-container"> 
              <span onclick="document.getElementById('modalMobile').style.display='none'" class="w3-closebtn">×</span>
              <div class="w3-center"><br>
                        <h4 class="modalMobile-title"><i class="fa fa-edit" id="modal-title-icon"></i> Liste des ressources disponibles</h4>
                    <div>
                        <p class="w3-text-red"><i class="fa fa-warning"></i> Attention : les ressources choisies ne seront pas prises en compte si la tache comporte des sous-taches</p>
                    </div>
              </div>
          </header>
          <div class="w3-container modalMobile-content">
              <form method="POST" action="ressourceList" id="modalMobile">
                  <div class="w3-group w3-left col-sm-12">
                      <a href="#" class="w3-btn w3-blue-grey w3-left"> <i class="fa fa-user-plus"></i> Nouvelle ressource</a>
                  </div>
                  <% for (Utilisateur elem : userList) { %> 
                  <div class="w3-form w3-half" id="checkboxes">
                      <input type="checkbox" id="modalMobile_<%=elem.getId()%>" name="<%=elem.getId()+":"+elem.getNom()%>"/> <label><%=elem.getNom() %></label>
                    </div>
                  <% } %>
                  
                  
                  <div class="w3-form col-sm-12">
                      <input type="submit" class="w3-btn w3-green w3-right w3-margin-left">
                        <button onclick="document.getElementById('modalMobile').style.display='none'" type="button" class="w3-btn w3-red w3-right">Annuler</button>
                  </div>
                  
              </form>

          </div>

          <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
            
      <!--      <span class="w3-right w3-padding w3-hide-small">Forgot <a href="#">password?</a></span>-->
          </div>

        </div>
      </div>
    
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
