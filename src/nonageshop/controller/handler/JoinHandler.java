package nonageshop.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.service.MemberService;

public class JoinHandler implements Command {
    private MemberService service = new MemberService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            return "member/join.jsp";
        }else {
            Member member = getMember(request);
            System.out.println("member > " + member);
            HttpSession session = request.getSession();
            session.setAttribute("id", member.getId()); 
            service.joinMember(member);
            return "member/login.jsp";
        }
    }

    private Member getMember(HttpServletRequest request) {
        Member member = new Member();
        member.setId(request.getParameter("id"));
        member.setPwd(request.getParameter("pwd"));
        member.setName(request.getParameter("name"));
        member.setEmail(request.getParameter("email"));
        member.setZipNum(request.getParameter("zipNum"));
        member.setAddress(request.getParameter("addr1") +" "+ request.getParameter("addr2"));
        member.setPhone(request.getParameter("phone")); 
        return member;
    }

}
