/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function addDataToModalUpdate(data){
    var dataArray = data.split(";");
    console.log(data);
    console.log(dataArray);
    $(".modal-submit").attr("value", "modifier");
    $(".modal-action").attr("action", "modifier");
    $(".modal-title").html('<i class="fa fa-edit" id="modal-title-icon"></i> Modification de tache');
    $(".modal-ressourcelink").attr("onclick", "modalUserList("+dataArray[0]+")");
    
    $(".modal-id").attr("value", dataArray[0]);
    $(".modal-designation").attr("value", dataArray[1]);
    $(".modal-dateDebut").attr("value", dataArray[2]);
    $(".modal-dateFin").attr("value", dataArray[3]);
    $(".modal-duree").attr("value", dataArray[4]);
    $(".modal-statut").attr("value", dataArray[5]);

    $(".modal-ressource").attr("value", "1");
    
    createUserList(dataArray[6]);
    alert(dataArray[6] )
    
    
}

function addDataToModalInsert(idTacheMere){
    $(".modal-title").html('<i class="fa fa-newspaper-o" id="modal-title-icon"></i> Ajout de nouvelle Tache');
    $(".modal-submit").attr("value", "Ajouter");
    $(".modal-idTacheMere").attr("value", idTacheMere);
    $(".modal-action").attr("action", "nouvelleTache");
    $(".modal-ressourcelink").attr("onclick", "modalUserList("+0+")");
    
    let today = new Date().toISOString().slice(0, 10);
    $(".modal-id").attr("value", "");
    $(".modal-designation").attr("value", "");
    $(".modal-dateDebut").attr("value", today);
    $(".modal-duree").attr("value", "1");
    $(".modal-dateFin").attr("value", today);
    $(".modal-ressource").attr("value", "");
}

function createUserList(userString){
    var rep = "";
    var listUser = userString.split("/");
    
    for (var i = 0; i < listUser.length; i++) { 
        if(listUser[i] != ""){
            var oneUser = listUser[i].split(":");
            $(".modal-ressource").attr("value", userString);
            rep+='<i class="w3-tag w3-yellow w3-margin-right">x '+ oneUser[1] +'</i>'
        }  
    }
    
    $(".modal-listuser").html(rep);
}

function createStatutTache(statutString){
    
}

function dureeToDate(dateDebut, duree){
    var debut = new Date("dateDebut");
    var fin = addDays(debut, parseInt(duree));
    $(".modal-dateFin").attr("value", fin);
}

function dateToDuree(dateDebut, dateFin){
    var debut = new Date("dateDebut");
    var fin = new Date("dateFin");
}

function addDays(date, days) {
    var result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
}

function difference2days(dayBefore, dayAfter){
    var timeDiff = Math.abs(dayBefore.getTime() - date1.dayAfter());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    return diffDays;
}


