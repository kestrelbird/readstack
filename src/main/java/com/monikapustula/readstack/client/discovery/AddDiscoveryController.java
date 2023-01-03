package com.monikapustula.readstack.client.discovery;

import com.monikapustula.readstack.domain.api.CategoryName;
import com.monikapustula.readstack.domain.api.CategoryService;
import com.monikapustula.readstack.domain.api.DiscoverySaveRequest;
import com.monikapustula.readstack.domain.api.DiscoveryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/discovery/add")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER"),
                @HttpMethodConstraint(value = "POST", rolesAllowed = "USER")
        }
)

public class AddDiscoveryController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private DiscoveryService discoveryService = new DiscoveryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryName> allCategories = categoryService.findAllCategories();
        request.setAttribute("categories", allCategories);
        request.getRequestDispatcher("/WEB-INF/views/add-discovery.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiscoverySaveRequest saveRequest = createSaveRequest(request);
        discoveryService.add(saveRequest);
        response.sendRedirect(request.getContextPath());
    }

    private DiscoverySaveRequest createSaveRequest(HttpServletRequest request) {
        String loggedInUser = request.getUserPrincipal().getName();
        return new DiscoverySaveRequest(
                request.getParameter("title"),
                request.getParameter("url"),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("categoryId")),
                loggedInUser);
    }
}
