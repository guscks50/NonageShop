package nonageshop.controller.handler.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Kind;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class AdminProductDetailHandler implements Command {
private ProductService service = new ProductService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";
		String no = request.getParameter("pno").trim();
		
		Product product = service.getProduct (Integer.parseInt(no));
		request.setAttribute("Product", product);
		
		String tpage = "1";
		if (request.getParameter("tpage") != null) {
			tpage = request.getParameter("tpage");
		} 
		
		List<Kind> kindList=Arrays.asList(
				new Kind(1, "Heels"),
				new Kind(2, "Boots"),
				new Kind(3, "Sandals"),
				new Kind(4, "Sneakers"),
				new Kind(5, "On Sale")
				
				);
		request.setAttribute("tpage", tpage);
		
		int index=Integer.parseInt(product.getKind().trim());
		request.setAttribute("kind", kindList.get(index));
		return url;
	}

}
