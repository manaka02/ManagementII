/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.modeleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import manaka.management.modele.Tache;
import manaka.management.utils.ConnectDB;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class TacheDao {
    public static void updateTache(Tache tacheModifie, Connection connect) throws Exception{
        String updateSql = "update Tache set idparent=?, idfeuille=?, designation=?, datecreation=?, datedebut=?, datefin=?, ressource=?, idstatut=?, suivant=?, niveau=?, ligne=? where idtache=?";
        PreparedStatement ps = null;
        try{
            ps = connect.prepareStatement(updateSql);
            if(tacheModifie.getIdParent() != null){
                 ps.setInt(1, tacheModifie.getIdParent());
            }else{
                ps.setNull(1,java.sql.Types.INTEGER);
            }
            ps.setInt(2, tacheModifie.getIdFeuille());
            ps.setString(3, tacheModifie.getDesignation());
            ps.setDate(4, tacheModifie.getDateCreation());
            ps.setDate(5, tacheModifie.getDateMin());
            ps.setDate(6, tacheModifie.getDateMax());
            ps.setString(7, tacheModifie.getRessource());
            ps.setInt(8, tacheModifie.getStatut().getId());
            ps.setInt(9, tacheModifie.getSuivant());
            ps.setInt(10, tacheModifie.getNiveau());
            ps.setInt(11, tacheModifie.getLigne());
            ps.setInt(12, tacheModifie.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new  Exception(ex.getMessage());
        } finally{
            ps.close();
        }
    }

    public static void saveTache(Tache tache, Connection connect) throws Exception {
        String sql = "insert into tache (idtache,idfeuille,idparent,idstatut,designation,datecreation,datedebut,datefin,suivant,niveau,ressource,ligne) values (default,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connect.prepareStatement(sql);
        System.out.println(sql);
        try {
            if(tache.getIdParent() != null){
                 ps.setInt(2, tache.getIdParent());
            }else{
                ps.setNull(2,java.sql.Types.INTEGER);
            }
            ps.setInt(1, tache.getIdFeuille());
            ps.setInt(3, tache.getStatut().getId());
            ps.setString(4, tache.getDesignation());
            ps.setDate(5,tache.getDateCreation());
            ps.setDate(6,tache.getDateDebut());
            ps.setDate(7,tache.getDateFin());
            ps.setInt(8, tache.getSuivant());
            ps.setInt(9, tache.getNiveau());
            ps.setString(10, tache.getRessource());
            ps.setInt(11, tache.getLigne());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new  Exception(e.getMessage());
        }finally{
            ps.close();
        }
    }
}
