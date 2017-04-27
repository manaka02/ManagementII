/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.services;

import dao.HibernateDao;
import java.util.ArrayList;
import manaka.management.modele.Feuille;
import manaka.management.modele.Tache;
import manaka.management.modele.Utilisateur;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class FeuilleServices {
//    public static Utilisateur searchUtilisateur(int idUser, Feuille feuille){
//        boolean find = false;
//        Utilisateur userOne = new Utilisateur(idUser);
//        for (Tache tache : feuille.getTacheList()) {
//            for (Utilisateur utilisateur : tache.getUtilisateurList()) {
//                if(utilisateur.equalsId(userOne)){                   
//                    userOne = utilisateur;
//                    find = true;
//                    break;
//                }
//            }
//            if(find) break;
//        }
//        return userOne;   
//    }
    
    public static Feuille initData(int idFeuille) throws Exception{
        HibernateDao hibernate = new HibernateDao();
        Feuille feuille = new Feuille(idFeuille);
        hibernate.findById(feuille);
        feuille.setTacheList(hibernate.getTacheListByFeuilleOrdered(feuille));
        TacheServices.setDateMinAndMaxAndUsersTacheList(feuille.getTacheList());
        feuille.setTacheToRemove(new ArrayList<Tache>());
        //feuille1.setListMinimData(TacheServices.DupliqueListeMinimData(feuille1.getTacheList()));
        //TacheServices.suppimeDoublure(feuille1.getTacheList());
       
        return feuille;
    }
}
