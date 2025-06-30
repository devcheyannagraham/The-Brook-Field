package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.Genre;
import demo.bfims.Repo.PublicationRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookTest {

    @Autowired
    PublicationRepo publicationRepo;

     @Test
    void bookCreationTest(){



    }

}