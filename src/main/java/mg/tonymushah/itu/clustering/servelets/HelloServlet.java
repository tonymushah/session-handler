package mg.tonymushah.itu.clustering.servelets;


import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().setAttribute("data", "some");
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello heroku".getBytes());
        out.flush();
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Object> data = Optional.ofNullable(req.getSession().getAttribute("data"));
        String dataString = data.map(data_ -> {
            if(data_ instanceof String) {
                return (String) data_;
            }else{
                return data_.toString();
            }
        }).orElseGet(() -> "nothing");
        ServletOutputStream out = resp.getOutputStream();
        out.write(String.format("Session data : '%s'", dataString).getBytes());
        out.flush();
        out.close();
    }
}