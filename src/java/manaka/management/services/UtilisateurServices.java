/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.services;

import java.util.ArrayList;
import java.util.List;
import manaka.management.modele.BaseModele;
import manaka.management.modele.Feuille;
import manaka.management.modele.Tache;
import manaka.management.modele.Utilisateur;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class UtilisateurServices {

    public static void ajoutSansDoublure(List<Utilisateur> listUtilisateur, List<Utilisateur> nouvelleList) {
        for (Utilisateur baseModele : nouvelleList) {
            boolean isThere = false;
            for (Utilisateur dataAlreadyIn : listUtilisateur) {
                if(baseModele.equalsId(dataAlreadyIn)) isThere = true;
            }
            if(!isThere)    
                listUtilisateur.add(baseModele);
                isThere = false;
        }
    }
    
    
    public void generateUtilisateurList(List<Tache> tacheList){
        for (int i = 0; i <tacheList.size(); i--) {
            if(i != tacheList.size()-1){
                tacheList.get(i).setUtilisateurList(UtilisateurServices.generateUtilisateurByOneString(tacheList.get(i).getRessource()));
            }else{
                if(!tacheList.get(i).isOldThan(tacheList.get(i+1))){
                    tacheList.get(i).setUtilisateurList(UtilisateurServices.generateUtilisateurByOneString(tacheList.get(i).getRessource()));
                }else{
                    List<Utilisateur> temporaireList = null;
                }
            }
        }
    }
    /**
     * 
     * @return list des utilisateurs sur une tache
     */
    public static List<Utilisateur> generateUtilisateurByOneString(String userString){
        List<Utilisateur> userList = new ArrayList<Utilisateur>();
        String [] userArray = userString.split(";");
        for (String string : userArray) {
            String [] value = string.split(":");
            Utilisateur user = new Utilisateur(value[1], Integer.parseInt(value[0]));
            userList.add(user);
        }
        return userList;
    }
}
