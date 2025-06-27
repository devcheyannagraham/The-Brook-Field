package demo.bfims;

import demo.bfims.Entities.Inventory.*;
import demo.bfims.Entities.Orders.Customer;
import demo.bfims.Entities.Orders.Orders;
import demo.bfims.Entities.Orders.Purchase;
import demo.bfims.Entities.Orders.Rental;
import demo.bfims.Enums.*;
import demo.bfims.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class BfimsApplication {
    @Autowired
    private PublicationRepo publicationRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private RentalRepo rentalRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BfimsApplication.class, args);
        BfimsApplication app = (BfimsApplication) context.getBean("bfimsApplication");
        app.newOrder();
        app.newOrder();
        app.newOrder();

    }
    @Transactional
    void  newOrder() {
        Orders order = new Orders();

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@gmail.com");
        customer.setPhoneNumber(1234567890L);
        order.setCustomer(customer);

        Author author = new Author();
        author.setFirstName("Dr.");
        author.setLastName("Suess");

        Publication publication = new Publication();
        publication.setTitle("Green Eggs & Ham");
        publication.setGenre(Genre.COMEDY);
        publication.addAuthor(author);


        Book book = new Book();
        book.setPublication(publication);
        book.setFormat(PublicationFormat.EBOOK);
        book.setItemType(ItemType.PUBLICATION);
        book.setRentalRate(3.99);
        book.setPurchasePrice(8.99);
        book.setEdition("Standard");
        book.setStatus(PublicationStatus.RENTED);
        book.getPublication().increaseQuantity();

        order.addOrderItem(book);

//        Rental rental = new Rental();
//        rental.setItem(book);
//        rental.setRentalRate(book.getRentalRate());
//        rental.setOrder(order);
//        rentalRepo.save(rental);

        Purchase purchase = new Purchase();
        purchase.setPurchasePrice(book.getPurchasePrice());
        purchase.setItem(book);
        purchase.setOrder(order);
        purchaseRepo.save(purchase);


        publicationRepo.save(publication);

        //rental/purchase saves order and book
        //book saves publication and author
        // order saves customer

//        orderRepo.save(order);
//        itemRepo.save(book);

    }

}
