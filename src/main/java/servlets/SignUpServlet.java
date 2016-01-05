package servlets;


import DBService.DBService;
import main.AccountService;
import main.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by matt on 26.12.2015.
 */
public class SignUpServlet extends HttpServlet {
    DBService DB;

    public SignUpServlet(DBService accountService) {
        this.DB = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        java.util.logging.Logger.getGlobal().info(req.getParameter("login") + " " + req.getParameter("password") + " UP");

        UserProfile usr = new UserProfile(req.getParameter("login"), req.getParameter("password"));
        DB.addUser(usr.GetLogin(), usr.GetPassword());


    }
}
