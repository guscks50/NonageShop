package nonageshop.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class IndexHandler implements Command {
    private ProductService service;
    
    public IndexHandler() {
        service = new ProductService();
    }

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> newProductList = service.newProducts();
        ArrayList<Product> bestProductList = service.bestProducts();
        
        request.setAttribute("newProductList", newProductList);
        request.setAttribute("bestProductList", bestProductList);
        
        return "index.jsp";
    }

}
