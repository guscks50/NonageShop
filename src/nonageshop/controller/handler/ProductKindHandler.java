package nonageshop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class ProductKindHandler implements Command {
    private ProductService service = new ProductService();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            String kind = request.getParameter("kind").trim();
            ArrayList<Product> productKindList = service.kindProducts(kind);
            request.setAttribute("productKindList", productKindList);
            
            System.out.println("kind > " + kind);
            productKindList.stream().forEach(System.out::println);
            
            return "product/productKind.jsp";
        }else {
            return null;
        }
        
    }

}
