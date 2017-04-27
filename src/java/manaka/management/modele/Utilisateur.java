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
public class Utilisateur extends BaseModele{
    private String nom;
    private String mail;
    private String pass;

    public Utilisateur(String nom, int id) {
        super(id);
        this.nom = nom;
    }

    
    
    public Utilisateur(int id) {
        super(id);
    }

    public Utilisateur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    
    
    
    
}
