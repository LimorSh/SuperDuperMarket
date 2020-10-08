package course.java.sdm.web.servlets;

import course.java.sdm.engine.dto.ZoneDetailsDto;
import course.java.sdm.engine.engine.users.User;
import course.java.sdm.engine.engine.users.UserManager;
import course.java.sdm.web.utils.ServletUtils;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@WebServlet(name = "UsersListServlet", urlPatterns = {"/userslist"})
public class UsersListServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            UserManager userManager = ServletUtils.getUserManager(getServletContext());
            Set<User> usersList = userManager.getUsers();
            List<User> usersListSortedById = usersList.stream().sorted(
                    Comparator.comparing(User::getId))
                    .collect(Collectors.toList());
            String json = gson.toJson(usersListSortedById);
            System.out.println(json);
            out.println(json);
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
}
