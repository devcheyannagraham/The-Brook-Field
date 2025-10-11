package demo.bfims.Config;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController implements ErrorController {


    @RequestMapping("/error")
    public String notFound(){
        System.out.println("\nSpaController.notFound()");
        return "forward:/index.html";
    }

    @RequestMapping("/**/{path:[^.]*}")
    public String redirectToIndex(){
        System.out.println("\nSpaController.redirectToIndex()");
        return "forward:index.html";
    }

}

