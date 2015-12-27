package servlets;

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
    AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        java.util.logging.Logger.getGlobal().info(req.getParameter("login") + " " + req.getParameter("password") + " IN");
        if (this.accountService.userExist(req.getParameter("login")) &&
                this.accountService.getUser(req.getParameter("login")).GetPassword().equals(req.getParameter("password"))){

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
