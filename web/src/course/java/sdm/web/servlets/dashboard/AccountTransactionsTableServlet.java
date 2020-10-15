package course.java.sdm.web.servlets.dashboard;

import com.google.gson.Gson;
import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.engine.engine.accounts.Transaction;
import course.java.sdm.web.utils.ServletUtils;
import course.java.sdm.web.utils.SessionUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


//@WebServlet(name = "AccountTransactionsTableServlet", urlPatterns = {"/accountTable"})
public class AccountTransactionsTableServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //returning JSON objects, not HTML
        response.setContentType("application/json");

        String usernameFromSession = SessionUtils.getUsername(request);
        AccountManager accountManager = ServletUtils.getAccountManager(getServletContext());

        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            List<Transaction> transactions = accountManager.getUserTransactions(usernameFromSession);
            String json = gson.toJson(transactions);
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
