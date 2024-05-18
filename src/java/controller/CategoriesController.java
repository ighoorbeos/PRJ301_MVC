/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOCategories;
import entity.Categories;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author NgocHung_ighoorbeos
 */
public class CategoriesController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCategories dao = new DAOCategories();
        String service = request.getParameter("service");
        if(service == null){
            service ="listAllCategories";
        }
        
        //delete
        if(service.equals("deleteCategories")){
            int cateid = Integer.parseInt(request.getParameter("cateid"));
            int n = dao.removeCategory(cateid);
            response.sendRedirect("categoriescontroller?service=listAllCategories");
        }
        
        //update
        if(service.equals("updateCategories")){
            String submit = request.getParameter("submit");
            Vector<Categories> vector = null;
            if(submit == null){
               vector = dao.getCategorieses("SELECT * FROM Categories");
                request.setAttribute("vector", vector);
            
            vector = dao.getCategorieses("SELECT * FROM Categories"
                    + " WHERE CategoryID="+Integer.parseInt(request.getParameter("cateid")));
           request.setAttribute("vector", vector);
           request.getRequestDispatcher("/JSP/updateCategories.jsp");
            }else{
                 String id = request.getParameter("CategoryID");              
                String catename = request.getParameter("CategoryName");
                String description = request.getParameter("description");
                
                int cateid = Integer.parseInt(id);
                
                Categories cate = new Categories(cateid, catename, description);
            dao.updateCategory(cate);
            
            response.sendRedirect("categoriescontroller");
            }
        }
        
        //insert
        if(service.equals("insertCategories")){
            String submit = request.getParameter("submit");
            Vector<Categories> vector = null;
            
            if(submit == null){ //show form insert
                vector = dao.getCategorieses("SELECT * FROM Categories");
                
                request.setAttribute("vector", vector);
                
                request.getRequestDispatcher("/JSP/insertCategories.jsp").forward(request, response);
            }else{
                String categoryName = request.getParameter("categoryName");
                String description = request.getParameter("description");
                
                Categories cate = new Categories(0, categoryName, description);
                int n = dao.insertCategories(cate);
                
                response.sendRedirect("categoriescontroller");
            }
        }
        
        if(service.equals("listAllCategories")){
            //get data fromd dao
            String submit = request.getParameter("submit");
            Vector<Categories> vector = null;
            if (submit == null) {
                vector = dao.getCategorieses("SELECT * FROM Categories");
            } else {
                String categoryName = request.getParameter("categoryName");
                vector = dao.getCategorieses("SELECT * FROM Categories WHERE CategoryName like '%" + categoryName + "%'");
            }
            
            String title = "Categories Manage";
            String titleTable = "List of Categories";
            RequestDispatcher resd = request.getRequestDispatcher("/JSP/listCategories.jsp");
            //set data for jsp
            request.setAttribute("data", vector);
            request.setAttribute("title", title);
            request.setAttribute("titleTable", titleTable);
            
            //run
            resd.forward(request, response);
         
        }
        
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
