/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.modele;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import manaka.management.utils.Utils;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class Tache extends BaseModele implements Cloneable{
    /**
     * list des attributs dans la base 
     */
    private int idFeuille;
    private Integer idParent;
    private Statut statut;
    private String designation;
    private Date dateCreation;
    private Date dateDebut;
    private Date dateFin;
    private Integer suivant;
    private Integer niveau;
    private Integer ligne;
    private String ressource;
    
    /**
     * list des attributs pas dans la base 
     */
    private Date dateMin;
    private Date dateMax;
    private List<Utilisateur> UtilisateurList;
    private List<Tache> listeSousTache;
    private boolean nouveau=false;
    private boolean modifie=false;

    public Tache(int id) {
        super(id);
    }

    public Tache() {
    }

    @Override
    public Tache clone() throws CloneNotSupportedException{
        return (Tache) super.clone();
    }
    
    public Tache(String designation, String dateDebut, String dateFin,String statut, String id) throws Exception {
        this.setId2(id);
        this.setStatut(statut);
        this.setDesignation(designation);
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
        this.setDateCreation(Utils.stringToDate(Utils.getDateAuj()));
    }
    
    public Tache(String designation, String dateDebut, String dateFin,String statut) throws Exception {
        this.setStatut(statut);
        this.setDesignation(designation);
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
        this.setDateCreation(Utils.stringToDate(Utils.getDateAuj()));
    }

    /**
     * Setters for String with controller
     * @return 
     */
    public void setId2(String id){
        this.setId(Integer.parseInt(id));
    }
    
    private void setStatut(String statut) {
        System.out.println("ty le statut :" + statut);
        String[] data = statut.split(":");
        Statut s = new Statut(data[1], Integer.parseInt(data[0]));
        this.setStatut(s);
    }

    private void setDateDebut(String dateDebut) throws Exception {
        try {
            Date d = Utils.stringToDate(dateDebut);
            this.setDateDebut(d);
        } catch (Exception ex) {
            throw new Exception ("la date de début que vous avez composée n'est pas correcte'");
        }
    }

    private void setDateFin(String dateFin) throws Exception {
        try {
            Date d = Utils.stringToDate(dateFin);
            if(Utils.espaceJour2Date(this.getDateDebut(),d)<0){
                throw new Exception("La date fin doit être supérieure ou égale à la date de début");
            }
            this.setDateFin(d);
        } catch (ParseException ex) {
            throw new Exception ("la date fin que vous avez composée n'est pas correcte'");
        }
    }
    
    
    
    
    @Override
    public String toString() {
        return "Tache{" + "idFeuille=" + idFeuille + ", idParent=" + idParent + ", statut=" + statut + ", designation=" + designation + ", dateCreation=" + dateCreation + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", suivant=" + suivant + ", niveau=" + niveau + ", ligne=" + ligne + ", ressource=" + ressource + ", dateMin=" + dateMin + ", dateMax=" + dateMax + ", listeSousTache=" + listeSousTache + ", isNouveau=" + nouveau + ", isModifie=" + modifie + '}';
    }
    
    public String toStringJson(){
        return this.getId()+";"+this.getDesignation()+";"+dateMin+";"+dateMax+";"+this.tacheDuration()+";"+statut+";";
    } 
    
    public String donneeDeBase(){
        return this.getId()+";"+this.getDesignation()+";"+dateDebut+";"+dateFin+";"+statut.getDesignation()+";";
    }

    public int getIdFeuille() {
        return idFeuille;
    }

    public void setIdFeuille(int idFeuille) {
        this.idFeuille = idFeuille;
    }

    public Integer getLigne() {
        return ligne;
    }

    public void setLigne(Integer ligne) {
        this.ligne = ligne;
    }

    public Date getDateMin() {
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    public List<Tache> getListeSousTache() {
        return listeSousTache;
    }

    public void setListeSousTache(List<Tache> listSousTache) {
        this.listeSousTache = listSousTache;
    }

    public boolean isNouveau() {
        return nouveau;
    }

    public void setNouveau(boolean nouveau) {
        this.nouveau = nouveau;
    }

    public boolean isModifie() {
        return modifie;
    }

    public void setModifie(boolean modifie) {
        this.modifie = modifie;
    }

    
    
    
    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getSuivant() {
        return suivant;
    }

    public void setSuivant(Integer suivant) {
        this.suivant = suivant;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public List<Utilisateur> getUtilisateurList() {
        return UtilisateurList;
    }
    
    
    public void setUtilisateurList(List<Utilisateur> UtilisateurList) {
        this.UtilisateurList = UtilisateurList;
    }
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    /***
     * Debut des fontions
     * @return 
     */
    
    public int tacheDuration(){
        return Utils.espaceJour2Date(dateMin, dateMax)+1;
    }
    
    public boolean isOldThan(Tache anotherTache){
        if(this.getNiveau() < anotherTache.getNiveau()) return true;
        return false;
    }
    
    public boolean isOldOrSameThan(Tache anotherTache){
        if(this.getNiveau() <= anotherTache.getNiveau()) return true;
        return false;
    }
    
    public String setLeftMargin(int niveau){
        int taille  = 15*niveau;
        return " style='padding-left:"+ taille +"px;'";
    }
    
    public String generateRessourceLink(){
        String ret = "";
        for (Utilisateur user : UtilisateurList) {

            ret+= "<a href='ressourcesData?id="+user.getId()+"'>" +  user.getNom() + "</a>  ";
        }
        return ret;
    }
    
    public String toRowTable(){
        return "<tr>"+
                    "<td" +this.setLeftMargin(this.getNiveau())+">"+this.getDesignation()+"</td>"+
                    "<td>"+this.getDateMin()+"</td>"+
                    "<td>"+this.getDateMax()+"</td>"+
                    "<td>"+this.generateRessourceLink()+"</td>"+
                    "<td>"+this.getStatut().getDesignation()+"</td>"+
                    "<td>"+
                         "<a href='#' onclick=\"modalInsert('"+this.getId() +"')\"><i class='fa fa-plus'>  </i></a> "+
                         " <a href='#' onclick=\"modalUpdate('"+this.getId() +"')\" data-value='' style='color:red;'><i class='fa fa-edit'>  </i></a>"+
                     "</td>\n"+
               "</tr>";   
    }
    
    public String addIcon(){
        if(isParent()){
            return "<i class='fa fa-folder' style=\"" +"color: orange;" +"\"></i>";
        }else{
            return "<i class='fa fa-file groove'style=\"" +"color: rebeccapurple;" +"\"></i>";
        }
    }
    
    public boolean isParent(){
        
        return true;
    }

    
    
}
