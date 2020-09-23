package nonageshop.controller.handler.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;

public class AdminLogoutHandler implements Command {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "adminLoginForm.do";

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            request.setAttribute("message", "");
        }

        return url;
    }

}
