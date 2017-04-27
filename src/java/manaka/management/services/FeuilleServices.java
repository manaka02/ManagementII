/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.services;

import dao.HibernateDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manaka.management.modele.BaseModele;
import manaka.management.modele.Feuille;
import manaka.management.modele.Tache;
import manaka.management.modele.Utilisateur;
import manaka.management.modeleDao.TacheDao;
import manaka.management.utils.ConnectDB;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class FeuilleServices {
    
    public static void updateDataIntoDB(Feuille feuille) throws Exception{
        Connection connect = new ConnectDB().getConnection();
        HibernateDao hibernate = new HibernateDao();
        try {
            for (Tache tache : feuille.getTacheList()) {      
                if(tache.isModifie()){
                    TacheDao.updateTache(tache, connect);
                }
                if(tache.isNouveau()){
                    TacheDao.saveTache(tache, connect);
                }
            }
            System.out.println("tonga eto v?");
            connect.commit();
        } catch (Exception ex) {
            connect.rollback();
             Logger.getLogger(FeuilleServices.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("erreur durant la mise à jour de la base de donnée");   
        }   
    }
    
    /**
     * Initiation des données sur une feuille choisie
     * @param idFeuille
     * @return
     * @throws Exception 
     */
    public static Feuille initData(int idFeuille) throws Exception{
        HibernateDao hibernate = new HibernateDao();
        Feuille feuille = new Feuille(idFeuille);
        hibernate.findById(feuille);
        feuille.setTacheList(hibernate.getTacheListByFeuilleOrdered(feuille));
        TacheServices.setDateMinAndMaxAndUsersTacheList(feuille.getTacheList());
        
        List<BaseModele> listBase = hibernate.findAll(new Utilisateur());
        for (BaseModele baseModele : listBase) {
            feuille.getUtilisateurList().add((Utilisateur) baseModele);
        }

        return feuille;
    }
}
