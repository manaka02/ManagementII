/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.modele;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class Feuille extends BaseModele{
    private String designation;
    private Date dateajout;
    private List<Tache> tacheList;
    private List<Tache> listMinimData;
    private List<Tache> tacheToRemove;

    public Feuille(int id) {
        super(id);
    }
    
    public Feuille minimaData(){
        return new Feuille(this.getDesignation(),this.getDateajout(),this.getId());
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

    public List<Tache> getListMinimData() {
        return listMinimData;
    }

    public void setListMinimData(List<Tache> listMinimData) {
        this.listMinimData = listMinimData;
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
