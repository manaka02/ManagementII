/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.utils;

/**
 *
 * @author Toavina RALAMBOSOA
 */
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import manaka.management.modele.BaseModele;

public class BasicsFunctions {
    public ResultSet execquery(String query, Connection con){
        Statement st = null;
        ResultSet rs = null;
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
        }
        catch(SQLException exp){
        }
        return rs;
    }  
    public static int moisToInt(String mois){
        if(mois =="Janvier") return 1;
        if(mois =="Fevrier") return 2;
        if(mois =="Mars") return 3;
        if(mois =="Avril") return 4;
        if(mois =="Mai") return 5;
        if(mois =="Juin") return 6;
        if(mois =="Juillet") return 7;
        if(mois =="Aout") return 8;
        if(mois =="Septembre") return 9;
        if(mois =="Octobre") return 10;
        if(mois =="Novembre") return 11;
        else return 12;
    }
    public  static  ResultSet find(String sql, Connection connect) {
        ResultSet res = null;
        Statement st;
        try {
            st = connect.createStatement();
            System.out.println(sql);
            res = st.executeQuery(sql);  

        } catch (SQLException e) {
                e.printStackTrace();
        }  
        return res;
    }
    
    public static void updateModele(BaseModele baseModele){
        
    }
    
    public static  String getColonnes(Object object){
        Class c = object.getClass();
        Field[] fl = c.getDeclaredFields();
        String colonnes = "";
        int i=0;
        for( i= 0; i < fl.length-1; i++)
        {
            colonnes = colonnes + fl[i].getName();
            if(i != fl.length-2){
                colonnes = colonnes + ",";
            }
        }
        colonnes = colonnes + "," + fl[i].getName();
        return colonnes;
    }
    public static void insertionObj(String nomTable, Object values,Connection connect){
        String query = "INSERT INTO "+nomTable+"("+getColonnes(values)+") VALUES("+values.toString()+")";
        
        System.out.println(query +" BasicFunction : 43");
        Statement stmt = null;
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BasicsFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void insertionModel(String nomTable, ModeleDetail values){
//        String query = "INSERT INTO "+nomTable+"("+values.getColomnActif()+") VALUES("+values.toString()+")";
//        
//        Connection c = DBConnect.getConnection();
//        System.out.println(query +" BasicFunction : 43");
//        Statement stmt = null;
//        try {
//            stmt = c.createStatement();
//            stmt.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(BasicsFunctions.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    public static  String getFirstColonnes(Object object){
        Class c = object.getClass();
        Field[] fl = c.getDeclaredFields();
        return fl[0].getName();
    }
    
    public static void rollback(Connection connect){
        try {
            connect.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void commit(Connection connect){
        try {
            connect.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection connect){
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(BasicsFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int insertionObjI(String nomTable, Object values, Connection c){
        int key = 0;
        try {
            String query = "INSERT INTO "+nomTable+"("+getColonnes(values)+") VALUES("+values.toString()+")";
            System.out.println(query);
            Statement stmt = c.createStatement();
            
            stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if ( rs.next() ) {
                // Retrieve the auto generated key(s).
                key = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return key;
    }
    
    public static  int insertKey(String sql, Connection connect) throws SQLException{
        Statement stmt = null;
        int key=0;
        System.out.println(sql);
            stmt = connect.createStatement();
            stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if ( rs.next() ) {
                // Retrieve the auto generated key(s).
                key = rs.getInt(1);
            }

        return key;
    }
}
