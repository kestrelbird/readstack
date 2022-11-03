package com.monikapustula.readstack.client.category;

import com.monikapustula.readstack.domain.api.CategoryFullInfo;
import com.monikapustula.readstack.domain.api.CategoryService;
import com.monikapustula.readstack.domain.api.DiscoveryBasicInfo;
import com.monikapustula.readstack.domain.api.DiscoveryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final DiscoveryService discoveryService = new DiscoveryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        CategoryFullInfo category = categoryService.findById(categoryId)
                .orElseThrow();
        request.setAttribute("category", category);
        List<DiscoveryBasicInfo> discoveries = discoveryService.findByCategory(categoryId);
        request.setAttribute("discoveries", discoveries);
        request.getRequestDispatcher("/category.jsp").forward(request, response);
    }
}
