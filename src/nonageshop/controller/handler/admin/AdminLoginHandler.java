package nonageshop.controller.handler.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Worker;
import nonageshop.service.WorkerService;

public class AdminLoginHandler implements Command {
    private WorkerService service = new WorkerService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "adminLoginForm.do";
        String msg = "";
        String workerId = request.getParameter("workerId").trim();
        String workerPwd = request.getParameter("workerPwd").trim();
        Worker worker = new Worker(workerId, workerPwd);
        
        int result = service.workerCheck(worker);
        
        if (result == 1) {// 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("workerId", workerId);
            url = "adminProductList.do";
        } else {
            msg = "아이디 혹은 비밀번호를 확인하세요.";
        } 
        request.setAttribute("message", msg);

        return url;
    }

}
