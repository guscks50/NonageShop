package nonageshop.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.service.MemberService;

public class LoginHandler implements Command {
    private MemberService service = new MemberService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("post")) {
            HttpSession session=request.getSession();
            
            String id=request.getParameter("id");
            String pwd=request.getParameter("pwd");
            
            Member member=service.getMember(id);
            System.out.println("member > " + member);
            if(member!=null){
                if(member.getPwd().equals(pwd)){ 
                    session.removeAttribute("id");
                    session.setAttribute("loginUser", member);
                    return "index.do";
                }
            }
            return "member/login_fail.jsp";
        }
        return "member/login_fail.jsp";
    }

}
