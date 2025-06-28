package demo.bfims;

import demo.bfims.Entities.Inventory.*;
import demo.bfims.Enums.*;
import demo.bfims.Repo.*;
import demo.bfims.Services.PublicationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class BfimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfimsApplication.class, args);
    }


}
