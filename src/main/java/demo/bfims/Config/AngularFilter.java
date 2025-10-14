package demo.bfims.Config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AngularFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestPath = request.getServletPath();

        //tests have blank servlett path
        if (!requestPath.isBlank() && !requestPath.contains("api") && !requestPath.contains(".")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.forward(req, res);
            return;
        }
        chain.doFilter(req, res);
    }
}
