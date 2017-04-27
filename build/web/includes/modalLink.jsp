

<div id="modal" class="w3-modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="w3-closebtn w3-hover-red w3-container w3-padding-16 w3-display-topright">×</span>
  <div class="w3-modal-content w3-card-8 w3-animate-zoom" style="max-width:600px">
    <header class="w3-container"> 
        <span onclick="document.getElementById('modal').style.display='none'" class="w3-closebtn">×</span>
        <div class="w3-center"><br>
                  <h2 class="modal-title"><i class="fa fa-edit" id="modal-title-icon"></i> Modification de tache</h2>
        </div>
    </header>
    

    <div class="w3-container">
      <div class="w3-section">
          <form method="POST" action="modifier" class="modal-action">
              <input type="hidden" class="modal-utilisateur" name="utilisateur" value="bonsoir oh">  
            <input type="hidden" class="modal-id" name="id"> 
            <input type="hidden" class="modal-idTacheMere" name="parentId">
            <label><b>Nom du tache :</b></label>
            <input class="w3-input w3-margin-bottom modal-designation" type="text" name="designation"  required>
            <div>
                <p class="w3-text-red modal-warning" style="display: none;"><i class="fa fa-warning"></i> Attention : les dates insérés ne seront pas prises en compte si la tache comporte des sous-taches</p>
            </div>
            <label><b>Date début :</b></label>
            <input class="w3-input w3-margin-bottom modal-dateDebut maDate"type="text" name="dateDebut" placeholder="2017-02-14" required>

            <div class="w3-row-padding">
                <div class="w3-half">
                  <label>Date fin :</label>
                  <input class="w3-input w3-margin-bottom modal-dateFin maDate" type="text" placeholder="2017-02-14" name="dateFin">
                </div>
                <div class="w3-half">
                  <label>ou durée de la tache (Jours)</label>
                  <input class="w3-input w3-margin-bottom modal-duree maDate" type="number" min="1" placeholder="nombre de jour" name="duree">
                </div>              
            </div>
            <div>
                <input type="hidden" value="1:en attente" name="statut">
                <h4><b>Statut :</b> <i class="modal-statut"> En attente</i> <a href="#" class="w3-btn w3-white w3-margin-bottom"><i class="fa fa-edit"></i></a></h4>
            </div>
                
            <div>
                <h4>Liste des ressources : <a href="#" class="w3-btn w3-white w3-right w3-margin-bottom"><i class="fa fa-edit"></i></a></h4>
                <i class="w3-tag w3-yellow">x Toavina</i>
                <i class="w3-tag w3-yellow">x Fabienne</i>
                <i class="w3-tag w3-yellow">x Baliaka</i>
            </div>
                <input type="submit" class="w3-btn w3-btn-block w3-green w3-margin-top modal-submit" value="Sauvegarder changement">
<!--            <input class="w3-check w3-margin-top" type="checkbox" checked="checked"> Remember me-->
          </form>
      </div>
    </div>

    <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
      <button onclick="document.getElementById('modal').style.display='none'" type="button" class="w3-btn w3-red">Annuler</button>
<!--      <span class="w3-right w3-padding w3-hide-small">Forgot <a href="#">password?</a></span>-->
    </div>

  </div>
</div>
