/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.services;

import dao.HibernateDao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manaka.management.modele.BaseModele;
import manaka.management.modele.Feuille;
import manaka.management.modele.Tache;
import manaka.management.modele.Utilisateur;
import manaka.management.modeleDao.TacheDao;
import manaka.management.utils.Utils;


/**
 *
 * @author Toavina RALAMBOSOA
 */
public class TacheServices {
    

    public static void createTache(Feuille feuille, String idParent, String designation, String dateDebut, String dateFin, String statut, boolean isInsertToDB){
        try {
            Tache newTache = new Tache(designation,dateDebut,dateFin,statut);
            
            Tache tacheParent = new Tache(Integer.parseInt(idParent));
            tacheParent = TacheServices.searchTache(tacheParent, feuille.getTacheList());
            newTache.setIdFeuille(feuille.getId());
            newTache.setIdParent(tacheParent.getId());
            newTache.setNiveau(tacheParent.getNiveau()+1);
            newTache.setSuivant(1);
            newTache.setNouveau(true);
            newTache.setRessource("1:Toavina");
            
            TacheServices.insertTacheIntoList(newTache, tacheParent, feuille.getTacheList());

            FeuilleServices.updateDataIntoDB(feuille);
 
        } catch (Exception ex) {
            Logger.getLogger(TacheServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * inertion d'une nouvelle tache fille en la mettant comme junior de la tache mère designée
     * @param newTache
     * @param tacheMere
     * @param tacheList 
     */
    public static void insertTacheIntoList(Tache newTache,Tache tacheMere, List<Tache> tacheList){
        Tache lastChild = TacheServices.getLastChildFirstDegree(tacheMere,tacheList);
        TacheServices.insertTacheByLine(newTache, lastChild.getLigne(), tacheList);
    }
    
    /**
     * insertion d'une tache à une position définit et en décalant le reste
     * @param newTache
     * @param numLigne
     * @param tacheList 
     */
    public static void insertTacheByLine(Tache newTache, int numLigne, List<Tache> tacheList){
        TacheServices.decaler(tacheList, numLigne+1, -1, 1);
        tacheList.add(numLigne, newTache); 
        newTache.setLigne(numLigne+1);
    }   
    
    /**
     * déplacer une tache et ses descendants de la TacheList vers listTacheToRemove (dans feuille)
     * @param tacheToRemove
     * @param feuille 
     */
    public static void removeTacheFromList(Tache tacheToRemove, Feuille feuille){
        int debutChangement = 1;
        for (int i = 0; i < feuille.getTacheList().size(); i++) {
            if(tacheToRemove.equalsId(feuille.getTacheList().get(i))){
                List<Tache> listToRemove = TacheServices.removeTacheWithChild(feuille.getTacheList().get(i), feuille.getTacheList());
                feuille.setTacheToRemove(listToRemove); 
                debutChangement  = listToRemove.get(listToRemove.size()-1).getLigne()+1;
                break;
            }      
        }
        TacheServices.decaler(feuille.getTacheList(),debutChangement , -1,-feuille.getTacheToRemove().size() );
    }
    
    /**
     * Supprime une tache de la liste avec ses descendants
     * @param tacheToRemove
     * @param tacheList
     * @return la liste des taches à supprimé
     */
    public static List<Tache> removeTacheWithChild(Tache tacheToRemove, List<Tache> tacheList){
        int numAuDessous = 1;
        List<Tache> tacheListToRemove = new ArrayList<Tache>();
        tacheListToRemove.add(tacheToRemove);
        for (int i = tacheToRemove.getLigne(); i < tacheList.size(); i++) {
            if(tacheToRemove.isOldThan(tacheList.get(i))){
                tacheListToRemove.add(tacheList.get(i));
                numAuDessous = tacheList.get(i).getLigne();
            }
        }
        tacheList.remove(tacheToRemove);
        for (Tache tache : tacheListToRemove) {
            tacheList.remove(tache);
        }
        return tacheListToRemove;
    }
    
    /**
     * om parle ici des filles de 1ere degré, retourne la mere si elle n'a pas de fille ou se trouve à la dernière position
     * @param tacheMere
     * @param tacheList 
     */
    public static Tache getLastChildFirstDegree(Tache tacheMere, List<Tache> tacheList){
        Tache tacheToReturn = null;
        if(tacheList.size() == tacheMere.getLigne()) return tacheMere;
        
        if(!tacheMere.isOldThan(tacheList.get(tacheMere.getLigne()+1))){
                tacheToReturn = tacheMere;
        } 
        for (int i = tacheMere.getLigne(); i < tacheList.size(); i++) {
            if(tacheMere.isOldThan(tacheList.get(i))){
                tacheToReturn = tacheList.get(i);
            }                     
            if(tacheList.get(i).isOldThan(tacheMere)){
                break;
            }      
        }
        return tacheToReturn;
    }
    
    /**
     * le numero de ligne de la derniere ligne de ses filles et ses descendants
     * @param tacheMere
     * @param tacheList
     * @return 
     */
    public static int getLineOfLastChild(Tache tacheMere, List<Tache> tacheList){
        int number = 0;
        if(tacheList.size() == tacheMere.getLigne()) return tacheMere.getLigne();
        for (int i = tacheMere.getLigne(); i < tacheList.size(); i++) {
            if(tacheList.get(i).getLigne() > tacheMere.getLigne()) 
                number = tacheList.get(i).getLigne();
            if(tacheList.get(i).getLigne()  <= tacheMere.getLigne()) 
                break;
        }
        return number;
    }
    
    public static void decaler(List<Tache> tacheList, int ligneDebut, int ligneFin, int nombrePas){      
        if(ligneFin == -1) ligneFin = tacheList.get(tacheList.size()-1).getLigne();
        System.out.println("ligneDebut :" + ligneDebut + " ligneFin :" + ligneFin + " nombrePas :" + nombrePas);
        for (Tache tache : tacheList) {
            if(tache.getLigne() >=ligneDebut && tache.getLigne() <=ligneFin){
                tache.setLigne(tache.getLigne() + nombrePas);
                tache.setModifie(true);
            }
        }
        int a = 0;
    }

    /**
     * Fonction qui sert à calculer la date minimale et maximale au dépend des sous taches
     * Avec la mise en place du liste d'utilisateur
     * Meme parcours pour les 3 fonctionnalités donc groupé en 1;
     * @param tacheList 
     */
    public static void setDateMinAndMaxAndUsersTacheList(List<Tache> tacheList){
        int numeroActuel = 0;
        for (int i = 0; i < tacheList.size(); i++) {
            if(i != tacheList.size()-1){
                if(!tacheList.get(i).isOldThan(tacheList.get(i+1))){ // si la tache n'a pas de sousTache
                    tacheList.get(i).setDateMax(tacheList.get(i).getDateFin());
                    tacheList.get(i).setDateMin(tacheList.get(i).getDateDebut());
                    tacheList.get(i).setUtilisateurList(UtilisateurServices.generateUtilisateurByOneString(tacheList.get(i).getRessource()));
                
                } else{ //s'il existe des sous-taches
                    Date datePrise = tacheList.get(numeroActuel+1).getDateFin();
                    List<Utilisateur> userList = new ArrayList<Utilisateur>(); 
                    
                    for (int j = numeroActuel+1; j < tacheList.size(); j++) { 
                        if(tacheList.get(i).isOldThan(tacheList.get(j))){
                            if(Utils.espaceJour2Date(datePrise, tacheList.get(j).getDateFin())>0){
                                datePrise = tacheList.get(j).getDateFin();
                            }
                            if(Utils.espaceJour2Date(datePrise, tacheList.get(j).getDateDebut())<0){
                                datePrise = tacheList.get(j).getDateDebut();
                            }
                            List<Utilisateur> userTemporaireList = UtilisateurServices.generateUtilisateurByOneString(tacheList.get(j).getRessource());
                            UtilisateurServices.ajoutSansDoublure(userList,userTemporaireList);
                        }               
                    }
                    tacheList.get(i).setDateMin(datePrise);
                    tacheList.get(i).setDateMax(datePrise);
                    tacheList.get(i).setUtilisateurList(userList);
                }
            }else{ //ici on parle du dernier tache donc attribution directe
                tacheList.get(i).setDateMax(tacheList.get(i).getDateFin());
                tacheList.get(i).setDateMin(tacheList.get(i).getDateDebut());
                tacheList.get(i).setUtilisateurList(UtilisateurServices.generateUtilisateurByOneString(tacheList.get(i).getRessource()));
            }  
        }
    }

    /**
     * On cherche la tache dans la liste pas par reference mais par Id égaux
     * @param tacheATrouve
     * @param tacheList
     * @return 
     */
    public static Tache searchTache(Tache tacheATrouve, List<Tache> tacheList) {
        for (Tache tache : tacheList) {
            if(tache.equalsId(tacheATrouve)){
                return tache;
            }
        }
        return null;
    }

    public static void updateTache(Feuille feuille, String id, String designation, String dateDebut, String dateFin, String statut) throws Exception {
        Tache newTache = new Tache(designation,dateDebut,dateFin,statut,id);
        TacheServices.UpdateTacheIntoList(newTache, feuille.getTacheList());
        FeuilleServices.updateDataIntoDB(feuille);
    }

    /**
     * On parle d'une modification au niveau d'une liste de tache sans prendre compte de la base de donnée
     * @param newTache
     * @param tacheList 
     */
    public static void UpdateTacheIntoList(Tache newTache, List<Tache> tacheList) {
        System.out.println(tacheList.size());
        for (Tache tache : tacheList) {
            if(tache.equalsId(newTache))
                TacheServices.updateTacheSimpleData(newTache, tache);
        }  
    }
    
    
    /**
     * Cette fonction consiste à modifier seuement les fonctionnnalités de base
     * @param newTacheData  la tache avec les nouvelles données
     * @param oldData les anciens données
     */
    public static void updateTacheSimpleData(Tache newTacheData, Tache oldTache){
        oldTache.setDesignation(newTacheData.getDesignation());
        oldTache.setDateDebut(newTacheData.getDateDebut());
        oldTache.setDateFin(newTacheData.getDateFin());
        oldTache.setStatut(newTacheData.getStatut());
        oldTache.setModifie(true);
    }
    
   
    
    
    

}
