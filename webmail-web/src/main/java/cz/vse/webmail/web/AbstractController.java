/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.vse.webmail.web;

import cz.vse.webmail.domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Petr
 */
public abstract class AbstractController extends HttpServlet {
    
    public String getAction(HttpServletRequest request) {
        String path = request.getPathInfo();
        if (path != null && path.length() > 1) {
            return path.substring(1).split("/")[0];
        } 
        return getDefaultAction();
    }
    
    public void dispatch(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/" + action + ".jsp").forward(request, response);
    }
    
    public abstract String getDefaultAction();
    
    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    public User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
