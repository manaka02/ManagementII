/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.controller;

import com.google.gson.Gson;
import dao.HibernateDao;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manaka.management.modele.Feuille;
import manaka.management.modele.Tache;
import manaka.management.services.TacheServices;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class ModificationController extends HttpServlet{
    private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res){ 
            PrintWriter out = null;
            try {
                out = res.getWriter();
                ServletContext context = getServletContext();
                Feuille feuille = (Feuille) context.getAttribute("feuille");
                int tacheId = Integer.parseInt(req.getParameter("id"));
                Tache tacheATrouve = new Tache(tacheId);
                tacheATrouve= TacheServices.searchTache(tacheATrouve, feuille.getTacheList());
                Gson gson = new Gson();
                out.println(gson.toJson(tacheATrouve.toStringJson()));

            } catch (Exception ex) {
                Logger.getLogger(ModificationController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.close();
            }
            
	}
	
	 
	protected void doPost(HttpServletRequest req, HttpServletResponse res){
            PrintWriter out = null;
            try {
                HibernateDao hibernate = new HibernateDao();
                out = res.getWriter();
                ServletContext context = getServletContext();
                String id = req.getParameter("id");
                String designation = req.getParameter("designation");
                String dateDebut = req.getParameter("dateDebut");
                String dateFin = req.getParameter("dateFin");
                String statut = req.getParameter("statut");
                Feuille feuille = (Feuille) context.getAttribute("feuille");
                
                TacheServices.updateTache(feuille,id,designation,dateDebut,dateFin,statut);
//                TacheServices.updateTacheListToDB(feuille.getTacheList(),hibernate);
                
                req.setAttribute("feuille", feuille);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/affiche.jsp");
                dispatcher.forward(req, res);

            } catch (Exception ex) {
                Logger.getLogger(ModificationController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.close();
            }

	}

}   
