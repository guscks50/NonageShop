package nonageshop.controller.handler.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Kind;

public class AdminProductWriteFormHandler implements Command {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "admin/product/productWrite.jsp";
        List<Kind> kindList=Arrays.asList(
                new Kind(1, "Heels"),
                new Kind(2, "Boots"),
                new Kind(3, "Sandals"),
                new Kind(4, "Sneakers"),
                new Kind(5, "On Sale")
                );
		request.setAttribute("kindList", kindList);

		return url;
	}

}
