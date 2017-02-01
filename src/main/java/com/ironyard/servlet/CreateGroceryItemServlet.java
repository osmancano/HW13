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
import java.util.List;

/**
 * Created by jasonskipper on 1/26/17.
 */
@WebServlet(name = "CreateGroceryItemServlet", urlPatterns = "/create")
public class CreateGroceryItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get data from request
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String isle = request.getParameter("isle");
        int category = Integer.parseInt(request.getParameter("category"));

        double priceAsDouble = 0;
        int quantityAsInt = 0;
        int isleAsInt = 0;
        // convert data
        try{
            priceAsDouble = Double.parseDouble(price);
        }catch (Exception e){

        }
        try{
            quantityAsInt = Integer.parseInt(quantity);
        }catch (Exception e){

        }
        try{
            isleAsInt = Integer.parseInt(isle);
        }catch (Exception e){

        }


        // create grocery item from data
        GroceryItem item = new GroceryItem(name, priceAsDouble, quantityAsInt, isleAsInt, category);

        // save to list
        GroceryService gs = new GroceryService();
        item = gs.save((IronYardUser)request.getSession().getAttribute("ironyard_user"), item);
        List<GroceryItem> gItems = gs.getAll((IronYardUser)request.getSession().getAttribute("ironyard_user"));
        request.getSession().setAttribute("gItems",gItems);
        // add to session 'success' message flag
        request.setAttribute("msg_success", true);

        // forward back to create page
        String nextJSP = "/create.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
