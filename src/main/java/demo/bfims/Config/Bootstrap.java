package demo.bfims.Config;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.*;
import demo.bfims.Repo.*;
import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {
    AccessoryRepo accessoryRepo;
    ItemRepo itemRepo;
    AuthorRepo authorRepo;
    PublicationRepo publicationRepo;
    EntityManager entityManager;
    UserRepo userRepo;
    CustomerRepo customerRepo;
    OrderRepo orderRepo;


    public Bootstrap(OrderRepo orderRepo, CustomerRepo customerRepo, AccessoryRepo accessoryRepo, ItemRepo itemRepo, AuthorRepo authorRepo, PublicationRepo publicationRepo, EntityManager entityManager, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.accessoryRepo = accessoryRepo;
        this.itemRepo = itemRepo;
        this.authorRepo = authorRepo;
        this.publicationRepo = publicationRepo;
        this.entityManager = entityManager;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.bootstrap();
    }

    //    @Transactional
    public void bootstrap() {
        System.out.println("Bootstrap");

        //Create admin user
        User admin = new User();
        admin.setEmail("admin");
        admin.setSalt(User.generateSalt());
        admin.setPassword(User.hashPassword("admin", admin.getSalt()));
        admin.setUserRole(UserRole.ADMIN);
        userRepo.save(admin);


        Accessory a1 = new Accessory("Classic Bookmark", AccessoryType.BOOKMARK, 2.99);
        Accessory a2 = new Accessory("Ceramic Mug", AccessoryType.MUG, 9.99);
        Accessory a3 = new Accessory("Gel Pen", AccessoryType.PEN, 1.49);
        Accessory a4 = new Accessory("Leather Bookmark", AccessoryType.BOOKMARK, 4.99);
        Accessory a5 = new Accessory("Travel Mug", AccessoryType.MUG, 12.99);
        Accessory a6 = new Accessory("Ballpoint Pen", AccessoryType.PEN, 0.99);
        Accessory a7 = new Accessory("Magnetic Bookmark", AccessoryType.BOOKMARK, 3.49);
        Accessory a8 = new Accessory("Insulated Mug", AccessoryType.MUG, 14.99);
        Accessory a9 = new Accessory("Rollerball Pen", AccessoryType.PEN, 2.49);
        Accessory a10 = new Accessory("Vintage Bookmark", AccessoryType.BOOKMARK, 5.99);
        accessoryRepo.saveAll(List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));

        AccessoryItem ai1 = new AccessoryItem(a1, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai2 = new AccessoryItem(a2, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai3 = new AccessoryItem(a3, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai4 = new AccessoryItem(a4, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai5 = new AccessoryItem(a5, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai6 = new AccessoryItem(a6, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai7 = new AccessoryItem(a7, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai8 = new AccessoryItem(a8, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai9 = new AccessoryItem(a9, AccessoryItemStatus.AVAILABLE);
        AccessoryItem ai10 = new AccessoryItem(a10, AccessoryItemStatus.AVAILABLE);
        itemRepo.saveAll(List.of(ai1, ai2, ai3, ai4, ai5, ai6, ai7, ai8, ai9, ai10));

        Author au1 = entityManager.merge(authorRepo.save(new Author("Jane", "Austen")));
        Author au2 = entityManager.merge(authorRepo.save(new Author("Mark", "Twain")));
        Author au3 = entityManager.merge(authorRepo.save(new Author("Agatha", "Christie")));
        Author au4 = entityManager.merge(authorRepo.save(new Author("George", "Orwell")));
        Author au5 = entityManager.merge(authorRepo.save(new Author("J.K.", "Rowling")));
        Author au6 = entityManager.merge(authorRepo.save(new Author("Stephen", "King")));
        Author au7 = entityManager.merge(authorRepo.save(new Author("Harper", "Lee")));
        Author au8 = entityManager.merge(authorRepo.save(new Author("F. Scott", "Fitzgerald")));
        Author au9 = entityManager.merge(authorRepo.save(new Author("Ernest", "Hemingway")));
        Author au10 = entityManager.merge(authorRepo.save(new Author("Leo", "Tolstoy")));

        Publication p1 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1813, 1, 28), Genre.COMEDY, "9780141439518", "Pride and Prejudice", au1)));
        Publication p2 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1884, 12, 10), Genre.YOUTH, "9780486280615", "Adventures of Huckleberry Finn", au2)));
        Publication p3 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1920, 1, 1), Genre.SCIFI, "9780062073488", "The Mysterious Affair at Styles", au3)));
        Publication p4 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1949, 6, 8), Genre.SCIFI, "9780451524935", "1984", au4)));
        Publication p5 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1997, 6, 26), Genre.FANTASY, "9780747532699", "Harry Potter and the Philosopher's Stone", au5)));
        Publication p6 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1977, 1, 1), Genre.FANTASY, "9780450040184", "The Shining", au6)));
        Publication p7 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1960, 7, 11), Genre.YOUTH, "9780061120084", "To Kill a Mockingbird", au7)));
        Publication p8 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1925, 4, 10), Genre.COMEDY, "9780743273565", "The Great Gatsby", au8)));
        Publication p9 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1952, 9, 1), Genre.COMEDY, "9780684803357", "The Old Man and the Sea", au9)));
        Publication p10 = entityManager.merge(publicationRepo.save(new Publication(LocalDate.of(1869, 1, 1), Genre.FANTASY, "9780199232765", "War and Peace", au10)));

        // Book
        Book b1 = new Book("1st", PublicationItemFormat.HARDCOPY, 15.99, 2.99, PublicationItemStatus.AVAILABLE, p1);
        Book b2 = new Book("2nd", PublicationItemFormat.EBOOK, 9.99, 1.99, PublicationItemStatus.AVAILABLE, p1);
        Book b3 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 12.99, 1.49, PublicationItemStatus.AVAILABLE, p1);
        Book b4 = new Book("1st", PublicationItemFormat.HARDCOPY, 12.99, 2.49, PublicationItemStatus.AVAILABLE, p2);
        Book b5 = new Book("2nd", PublicationItemFormat.EBOOK, 8.99, 1.49, PublicationItemStatus.AVAILABLE, p2);
        Book b6 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 10.99, 1.29, PublicationItemStatus.AVAILABLE, p2);
        Book b14 = new Book("1st", PublicationItemFormat.HARDCOPY, 13.99, 2.19, PublicationItemStatus.AVAILABLE, p6);
        Book b15 = new Book("2nd", PublicationItemFormat.EBOOK, 10.99, 1.49, PublicationItemStatus.AVAILABLE, p6);
        Book b16 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 11.99, 1.29, PublicationItemStatus.AVAILABLE, p6);
        Book b20 = new Book("1st", PublicationItemFormat.HARDCOPY, 11.99, 1.99, PublicationItemStatus.AVAILABLE, p8);
        Book b21 = new Book("2nd", PublicationItemFormat.EBOOK, 8.99, 1.29, PublicationItemStatus.AVAILABLE, p8);
        Book b22 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 9.99, 1.09, PublicationItemStatus.AVAILABLE, p8);
        Book b26 = new Book("1st", PublicationItemFormat.HARDCOPY, 16.99, 2.49, PublicationItemStatus.AVAILABLE, p10);
        Book b27 = new Book("2nd", PublicationItemFormat.EBOOK, 12.99, 1.49, PublicationItemStatus.AVAILABLE, p10);
        Book b28 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 13.99, 1.19, PublicationItemStatus.AVAILABLE, p10);
        itemRepo.saveAll(List.of(b1, b2, b3, b4, b5, b6, b14, b15, b16, b20, b21, b22, b26, b27, b28));

        // Journal
        Journal j7 = new Journal("Vol. 1", PublicationItemFormat.HARDCOPY, 10.99, 1.99, PublicationItemStatus.AVAILABLE, p3, LocalDate.of(1920, 1, 1), "Mystery Issue", "1", "I");
        Journal j8 = new Journal("Vol. 1", PublicationItemFormat.AUDIOBOOK, 8.99, 1.19, PublicationItemStatus.AVAILABLE, p3, LocalDate.of(1920, 1, 1), "Mystery Issue Audio", "2", "I");
        Journal j17 = new Journal("Vol. 1", PublicationItemFormat.HARDCOPY, 12.99, 1.99, PublicationItemStatus.AVAILABLE, p7, LocalDate.of(1960, 7, 11), "Mockingbird Issue", "1", "I");
        Journal j18 = new Journal("Vol. 1", PublicationItemFormat.EBOOK, 9.99, 1.29, PublicationItemStatus.AVAILABLE, p7, LocalDate.of(1960, 7, 11), "Mockingbird Issue Digital", "2", "I");
        Journal j19 = new Journal("Vol. 1", PublicationItemFormat.AUDIOBOOK, 10.99, 1.19, PublicationItemStatus.AVAILABLE, p7, LocalDate.of(1960, 7, 11), "Mockingbird Issue Audio", "3", "I");
        itemRepo.saveAll(List.of(j7, j8, j17, j18, j19));

        // LiteraryPiece
        LiteraryPiece lp9 = new LiteraryPiece("Classic", PublicationItemFormat.HARDCOPY, 7.99, 1.29, PublicationItemStatus.AVAILABLE, p4, LiteraryType.STORY);
        LiteraryPiece lp10 = new LiteraryPiece("Classic", PublicationItemFormat.AUDIOBOOK, 6.99, 0.99, PublicationItemStatus.AVAILABLE, p4, LiteraryType.STORY);
        LiteraryPiece lp11 = new LiteraryPiece("Magic", PublicationItemFormat.HARDCOPY, 11.99, 1.99, PublicationItemStatus.AVAILABLE, p5, LiteraryType.STORY);
        LiteraryPiece lp12 = new LiteraryPiece("Magic", PublicationItemFormat.EBOOK, 8.99, 1.29, PublicationItemStatus.AVAILABLE, p5, LiteraryType.STORY);
        LiteraryPiece lp13 = new LiteraryPiece("Magic", PublicationItemFormat.AUDIOBOOK, 9.99, 1.49, PublicationItemStatus.AVAILABLE, p5, LiteraryType.STORY);
        LiteraryPiece lp23 = new LiteraryPiece("Classic", PublicationItemFormat.HARDCOPY, 10.99, 1.49, PublicationItemStatus.AVAILABLE, p9, LiteraryType.STORY);
        LiteraryPiece lp24 = new LiteraryPiece("Classic", PublicationItemFormat.EBOOK, 7.99, 1.09, PublicationItemStatus.AVAILABLE, p9, LiteraryType.STORY);
        LiteraryPiece lp25 = new LiteraryPiece("Classic", PublicationItemFormat.AUDIOBOOK, 8.99, 0.99, PublicationItemStatus.AVAILABLE, p9, LiteraryType.STORY);
        itemRepo.saveAll(List.of(lp9, lp10, lp11, lp12, lp13, lp23, lp24, lp25));


        // Customer
        Customer c1 = new Customer("alex.smith@email.com", "Alex", "Smith", "555-1010", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c2 = new Customer("maria.garcia@email.com", "Maria", "Garcia", "555-2020", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c3 = new Customer("john.doe@email.com", "John", "Doe", "555-3030", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c4 = new Customer("emily.jones@email.com", "Emily", "Jones", "555-4040", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c5 = new Customer("michael.brown@email.com", "Michael", "Brown", "555-5050", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c6 = new Customer("sophia.martin@email.com", "Sophia", "Martin", "555-6060", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c7 = new Customer("liam.wilson@email.com", "Liam", "Wilson", "555-7070", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c8 = new Customer("olivia.moore@email.com", "Olivia", "Moore", "555-8080", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c9 = new Customer("noah.taylor@email.com", "Noah", "Taylor", "555-9090", "123 N Main st", "Some City", "SS", "12345", "A Country");
        Customer c10 = new Customer("ava.thomas@email.com", "Ava", "Thomas", "555-0101", "123 N Main st", "Some City", "SS", "12345", "A Country");
        customerRepo.saveAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));


        Purchase pu1 = new Purchase(ai1, ai1.getPurchasePrice());
        Purchase pu2 = new Purchase(ai2, ai2.getPurchasePrice());
        Purchase pu3 = new Purchase(ai3, ai3.getPurchasePrice());
        Purchase pu4 = new Purchase(lp9, lp9.getPurchasePrice());
        Purchase pu5 = new Purchase(lp10, lp10.getPurchasePrice());
        Purchase pu6 = new Purchase(b1, b1.getPurchasePrice());
        Purchase pu7 = new Purchase(b2, b2.getPurchasePrice());
        Purchase pu8 = new Purchase(j7, j7.getPurchasePrice());
        Purchase pu9 = new Purchase(j8, j8.getPurchasePrice());
        Purchase pu10 = new Purchase(ai4, ai4.getPurchasePrice());
        Purchase pu11 = new Purchase(ai5, ai4.getPurchasePrice());
        Purchase pu12 = new Purchase(b3, b3.getPurchasePrice());
        Purchase pu13 = new Purchase(b4, b4.getPurchasePrice());
        Purchase pu14 = new Purchase(b6, b6.getPurchasePrice());
        Purchase pu15 = new Purchase(ai6, ai6.getPurchasePrice());


        Rental r1 = new Rental(b5, b5.getPurchasePrice());
        Rental r2 = new Rental(b6, b6.getPurchasePrice());
        Rental r3 = new Rental(lp25, lp25.getPurchasePrice());
        Rental r4 = new Rental(lp11, lp11.getPurchasePrice());
        Rental r5 = new Rental(lp12, lp12.getPurchasePrice());
        Rental r6 = new Rental(lp13, lp13.getPurchasePrice());
        Rental r7 = new Rental(j17, j17.getPurchasePrice());
        Rental r8 = new Rental(j18, j18.getPurchasePrice());
        Rental r9 = new Rental(b28, b28.getPurchasePrice());
        Rental r10 = new Rental(b27, b27.getPurchasePrice());
        Rental r11 = new Rental(b26, b26.getPurchasePrice());
        Rental r12 = new Rental(b22, b22.getPurchasePrice());


        //// Orders
        Order o3 = new Order(Arrays.asList(pu1, pu2, pu3), c3);
        Order o4 = new Order(Arrays.asList(pu4, pu5, pu6), c4);
        Order o5 = new Order(Arrays.asList(pu7, pu8, pu9), c5);
        Order o6 = new Order(Arrays.asList(pu10, pu11, pu12), c6);
        Order o7 = new Order(Arrays.asList(pu13, pu14, pu15), c7);
        Order o8 = new Order(Arrays.asList(r1, r2, r3), c8);
        Order o9 = new Order(Arrays.asList(r4, r5, r6), c9);
        Order o10 = new Order(Arrays.asList(r7, r8, r9), c10);
        Order o1 = new Order(Arrays.asList(r11, r12, pu1), c1);
        Order o2 = new Order(Arrays.asList(pu2, r12, pu4, r3), c2);
        orderRepo.saveAll(List.of(o1,o2,o3, o4, o5, o6, o7, o8, o9, o10));
    }
}
