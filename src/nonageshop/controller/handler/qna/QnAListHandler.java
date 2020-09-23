package nonageshop.controller.handler.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.QnA;
import nonageshop.service.QnAService;

public class QnAListHandler implements Command {
    private QnAService service = new QnAService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "qna/qnaList.jsp";

        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser == null) {
            url = "loginform.do";
        } else {
            ArrayList<QnA> qnaList = service.listQna(loginUser.getId());
            request.setAttribute("qnaList", qnaList);
        }
        return url;
    }

}
