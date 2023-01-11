package com.monikapustula.readstack.client.vote;

import com.monikapustula.readstack.domain.api.DiscoveryVote;
import com.monikapustula.readstack.domain.api.DiscoveryVoteService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/discovery/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value="GET", rolesAllowed="USER")
        }
)
public class DiscoveryVoteController extends HttpServlet {
    private DiscoveryVoteService voteService = new DiscoveryVoteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiscoveryVote discoveryVote = createDiscoveryVote(request);
        voteService.addVote(discoveryVote);
        response.sendRedirect(request.getContextPath());
    }

    private DiscoveryVote createDiscoveryVote(HttpServletRequest request) {
        Integer discoveryId = Integer.valueOf(request.getParameter("id"));
        String voteType = request.getParameter("type");
        String username = request.getUserPrincipal().getName();
        DiscoveryVote discoveryVote = new DiscoveryVote(username, discoveryId, voteType);
        return discoveryVote;
    }
}
