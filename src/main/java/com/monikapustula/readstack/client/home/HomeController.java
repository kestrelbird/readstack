package com.monikapustula.readstack.client.home;

import com.monikapustula.readstack.domain.api.CategoryName;
import com.monikapustula.readstack.domain.api.CategoryService;
import com.monikapustula.readstack.domain.api.DiscoveryBasicInfo;
import com.monikapustula.readstack.domain.api.DiscoveryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
     private DiscoveryService discoveryService = new DiscoveryService();
     private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DiscoveryBasicInfo> discoveries = discoveryService.findAll();
        request.setAttribute("discoveries", discoveries);
        List<CategoryName> categories = categoryService.findAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
