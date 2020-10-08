package course.java.sdm.web.servlets;

import com.google.gson.Gson;
import course.java.sdm.engine.dto.ZoneDetailsDto;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


//@WebServlet(name = "FileUploadServlet", urlPatterns = {"/pages/dashboard/uploadFile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Collection<Part> parts = request.getParts();
        String usernameFromSession = SessionUtils.getUsername(request);
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        Part part = parts.stream().findFirst().orElse(null);
        try {
            assert part != null;
            businessLogic.loadSystemData(part.getInputStream());
            businessLogic.setZoneOwnerName(usernameFromSession);

            response.getWriter().print("success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.getWriter().print(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
}