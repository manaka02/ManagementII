
import com.google.gson.Gson;
import dao.HibernateDao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manaka.management.modele.BaseModele;
import manaka.management.modele.Feuille;
import manaka.management.modele.Statut;
import manaka.management.modele.Tache;
import manaka.management.modele.Utilisateur;
import manaka.management.services.FeuilleServices;
import manaka.management.services.TacheServices;
import manaka.management.utils.HibernateUtil;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class Main {

    /**
     *
     * @param data
     */
    public static void main(String[] data){
        try {
            HibernateDao hibernate = new HibernateDao();
               List<BaseModele> userList = hibernate.findAll(new Utilisateur());
                Gson gson = new Gson();
                String classe = gson.toJson(userList);
                System.out.println(classe);
               
            
        }catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createAndStoreUser(String nom){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Utilisateur user = new Utilisateur();
        user.setMail(nom.toLowerCase() + "@gmail.com");
        user.setNom(nom);
        user.setPass(nom.toLowerCase() +"123456");
        session.save(user);
        session.getTransaction().commit();
    }
    
    private List<BaseModele> list(BaseModele baseModele) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from "+ baseModele.getClass().getSimpleName()).list();
        session.getTransaction().commit();
        return result;
    }
    
    private void addTacheToUser(int idTache, int idUser){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Utilisateur aPerson = (Utilisateur) session.load(Utilisateur.class, idTache);
        Tache aTache = (Tache) session.load(Tache.class, idTache);
        //aTache.getUtilisateurs().add(aPerson);
        
        session.getTransaction().commit();
    }
}
