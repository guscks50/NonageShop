package nonageshop.controller.handler.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import nonageshop.controller.Command;
import nonageshop.dto.Kind;

public class KindListHandler implements Command {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Kind> kindList=Arrays.asList(
                new Kind(1, "Heels"),
                new Kind(2, "Boots"),
                new Kind(3, "Sandals"),
                new Kind(4, "Sneakers"),
                new Kind(5, "On Sale")
                );
        
        Gson gson = new Gson(); 
        String result = gson.toJson(kindList, new TypeToken<List<Kind>>(){}.getType());
        System.out.println(result);
                
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        
        PrintWriter pw = response.getWriter();
        pw.print(result);
        pw.flush();
        
        return null;
    }

}
