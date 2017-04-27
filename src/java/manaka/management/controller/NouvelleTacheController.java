/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.controller;

import dao.HibernateDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manaka.management.modele.BaseModele;
import manaka.management.modele.Feuille;
import manaka.management.modele.Tache;
import manaka.management.services.TacheServices;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class NouvelleTacheController extends HttpServlet{
    private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
            PrintWriter out = res.getWriter();
            String feuilleId = req.getParameter("feuille");
            out.println(feuilleId);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nouvelleTacheAffiche.jsp");
            dispatcher.forward(req, res); 
	}
	
	 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            try {
                PrintWriter out = res.getWriter();
                String designation = req.getParameter("designation");
                String parentId = req.getParameter("parentId");
                String dateDebut = req.getParameter("dateDebut");
                String dateFin = req.getParameter("dateFin");
                String statut = req.getParameter("statut");
                ServletContext context = getServletContext();
                Feuille feuille = (Feuille) context.getAttribute("feuille");
                System.out.println(designation + " " + parentId + " " + dateDebut + " " + dateFin + " " + statut);
                TacheServices.createTache(feuille, parentId, designation, dateDebut, dateFin,statut, true);
                HibernateDao hibernate = new HibernateDao();
                
                req.setAttribute("feuille", feuille);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/affiche");
                dispatcher.forward(req, res);
            } catch (Exception ex) {
                Logger.getLogger(NouvelleTacheController.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}   
