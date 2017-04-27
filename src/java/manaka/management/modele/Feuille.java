/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.modele;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class Feuille extends BaseModele{
    private String designation;
    private Date dateajout;
    private List<Tache> tacheList;
    private List<Tache> tacheToRemove;
    private List<Utilisateur> utilisateurList;

    public Feuille(int id) {
        super(id);
        utilisateurList = new ArrayList<Utilisateur>();
        tacheToRemove = new ArrayList<Tache>();
    }

    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }
    

    public Feuille(String designation, Date dateajout, int id) {
        super(id);
        this.designation = designation;
        this.dateajout = dateajout;
    }

    public List<Tache> getTacheToRemove() {
        return tacheToRemove;
    }

    public void setTacheToRemove(List<Tache> tacheToRemove) {
        this.tacheToRemove = tacheToRemove;
    }

    
    
    public Feuille() {
    }

    public List<Tache> getTacheList() {
        return tacheList;
    }

    public void setTacheList(List<Tache> tacheList) {
        this.tacheList = tacheList;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateajout() {
        return dateajout;
    }

    public void setDateajout(Date dateajout) {
        this.dateajout = dateajout;
    }

    @Override
    public String toString() {
        return "Feuille{" + "designation=" + designation + ", dateajout=" + dateajout + '}';
    }
    
    
    
}
