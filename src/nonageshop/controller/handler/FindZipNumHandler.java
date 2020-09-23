package nonageshop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Address;
import nonageshop.service.MemberService;

public class FindZipNumHandler implements Command {
    private MemberService service = new MemberService();
    private String url="member/findZipNum.jsp";
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            return url;
        }else {
            String dong=request.getParameter("dong").trim();
            if(dong!=null && dong.trim().equals("")==false){
                ArrayList<Address> addressList =  service.showAddressByDong(dong);
                request.setAttribute("addressList", addressList);
                return url;
            }
        }
        
        return null;
    }

}
