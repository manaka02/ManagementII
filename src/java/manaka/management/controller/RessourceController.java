/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manaka.management.controller;

import com.google.gson.Gson;
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
import manaka.management.modele.Utilisateur;
import manaka.management.services.FeuilleServices;
import manaka.management.services.TacheServices;

/**
 *
 * @author Toavina RALAMBOSOA
 */
public class RessourceController extends HttpServlet{
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
            PrintWriter out = res.getWriter();
            try {
               HibernateDao hibernate = new HibernateDao();
               List<BaseModele> userList = hibernate.findAll(new Utilisateur());
               
               Gson gson = new Gson();
                String classe = gson.toJson(userList);
                System.out.println(classe);
                out.println(classe);
                
            } catch (Exception ex) {
                Logger.getLogger(AfficheController.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex.toString());
            }
	}
	
	 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            PrintWriter out = res.getWriter();
            try {
                
                Feuille feuille = FeuilleServices.initData(1);

                ServletContext context = getServletContext();
                context.setAttribute("feuille", feuille);
                req.setAttribute("feuille", feuille);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/affiche.jsp");
                dispatcher.forward(req, res);
            
            } catch (Exception ex) {
                Logger.getLogger(AfficheController.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex.toString());
            }
	}
}   
