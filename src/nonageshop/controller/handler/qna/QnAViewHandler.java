package nonageshop.controller.handler.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.QnA;
import nonageshop.service.QnAService;

public class QnAViewHandler implements Command {
    private QnAService service = new QnAService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "qna/qnaView.jsp";
        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            url = "loginform.do";
        } else {
            int no = Integer.parseInt(request.getParameter("no"));
            QnA qna = service.getQnA(no);
            request.setAttribute("qna", qna);
        }
        return url;
    }

}
