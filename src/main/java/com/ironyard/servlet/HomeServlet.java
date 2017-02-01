package com.ironyard.servlet;

import com.ironyard.com.ironyard.service.GroceryService;
import com.ironyard.data.GroceryItem;
import com.ironyard.data.IronYardUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by osmanidris on 1/29/17.
 */
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category = Integer.parseInt(request.getParameter("category"));
        GroceryService gs = new GroceryService();
        List<GroceryItem> filteredItemsList = new ArrayList<GroceryItem>();
        List<GroceryItem> gItems = gs.getAll((IronYardUser)request.getSession().getAttribute("ironyard_user"));
        if(category != 0) {
            for (GroceryItem item : gItems) {
                if (item.getCategory() == category) {
                    filteredItemsList.add(item);
                }
            }
            request.getSession().setAttribute("gItems", filteredItemsList);
        }else{
            request.getSession().setAttribute("gItems", gItems);
        }
        String nextJSP = "/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroceryService gs = new GroceryService();
        List<GroceryItem> gItems = gs.getAll((IronYardUser)request.getSession().getAttribute("ironyard_user"));
        request.getSession().setAttribute("gItems",gItems);

        // forward back to create page
        String nextJSP = "/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }
}
