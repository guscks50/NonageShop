package nonageshop.controller.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.service.MemberService;

public class IdCheckHandler implements Command {
    private MemberService service = new MemberService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("id").trim();
        int res = service.confirmId(userId);
        System.out.println("res >> " + res);
        PrintWriter pw = response.getWriter();
        pw.print(res==1?0:1);
        pw.flush();
        return null;
    }

}
