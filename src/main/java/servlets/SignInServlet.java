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
public class SignInServlet extends HttpServlet {
    DBService DB;
    UserProfile usr;

    public SignInServlet(DBService accountService) {
        this.DB = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        java.util.logging.Logger.getGlobal().info(req.getParameter("login") + " " + req.getParameter("password") + " IN");

        if (DB.userExist(req.getParameter("login"))){

            res.setContentType("text/html;charset=utf-8");
            res.getWriter().print("Authorized");
            res.setStatus(HttpServletResponse.SC_OK);

        }else{
            res.setContentType("text/html;charset=utf-8");
            res.getWriter().print("Unauthorized");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
