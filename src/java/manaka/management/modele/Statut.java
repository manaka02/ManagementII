/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.modele;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class Statut extends BaseModele{
    private String designation;
    private String remarque;

    public Statut(int id) {
        super(id);
    }

    public Statut(String designation, int id) {
        super(id);
        this.designation = designation;
    }
    
    public Statut() {
    }

    @Override
    public String toString() {
        return this.getId() +":"+ this.getDesignation();
    }
    
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
    
    
}
