package nonageshop.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class ProductDetailHandler implements Command {
    private ProductService service = new ProductService();
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            int no = Integer.parseInt(request.getParameter("no").trim());
            Product product = service.getProduct(no);
            request.setAttribute("product", product);
            
            System.out.println("no > " + no + " product > " + product);
            return "product/productDetail.jsp";
        }else {
            
        }
        return null;
    }

}
