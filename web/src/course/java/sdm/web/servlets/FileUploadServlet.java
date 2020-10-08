package course.java.sdm.web.servlets;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;


//@WebServlet(name = "FileUploadServlet", urlPatterns = {"/pages/dashboard/uploadFile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Collection<Part> parts = request.getParts();
        BusinessLogic businessLogic = ServletUtils.getBusinessLogic(getServletContext());
        Part part = parts.stream().findFirst().orElse(null);
        try {
            assert part != null;
            businessLogic.loadSystemData(part.getInputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.getWriter().print(e.getMessage());
        }
        response.getWriter().print("success");
    }
}