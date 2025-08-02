package demo.bfims.Config;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Enums.*;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Repo.AuthorRepo;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    AccessoryRepo accessoryRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    PublicationRepo publicationRepo;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstrap");

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

        Author au1 = authorRepo.save(new Author("Jane", "Austen"));
        Author au2 = authorRepo.save(new Author("Mark", "Twain"));
        Author au3 = authorRepo.save(new Author("Agatha", "Christie"));
        Author au4 = authorRepo.save(new Author("George", "Orwell"));
        Author au5 = authorRepo.save(new Author("J.K.", "Rowling"));
        Author au6 = authorRepo.save(new Author("Stephen", "King"));
        Author au7 = authorRepo.save(new Author("Harper", "Lee"));
        Author au8 = authorRepo.save(new Author("F. Scott", "Fitzgerald"));
        Author au9 = authorRepo.save(new Author("Ernest", "Hemingway"));
        Author au10 = authorRepo.save(new Author("Leo", "Tolstoy"));
//        authorRepo.saveAll(List.of(au1, au2, au3, au4, au5, au6, au7, au8, au9, au10));

        Publication p1 = publicationRepo.save(new Publication(LocalDate.of(1813, 1, 28), Genre.COMEDY, "9780141439518", "Pride and Prejudice", au1));
        Publication p2 = publicationRepo.save(new Publication(LocalDate.of(1884, 12, 10), Genre.YOUTH, "9780486280615", "Adventures of Huckleberry Finn", au2));
        Publication p3 = publicationRepo.save(new Publication(LocalDate.of(1920, 1, 1), Genre.SCIFI, "9780062073488", "The Mysterious Affair at Styles", au3));
        Publication p4 = publicationRepo.save(new Publication(LocalDate.of(1949, 6, 8), Genre.SCIFI, "9780451524935", "1984", au4));
        Publication p5 = publicationRepo.save(new Publication(LocalDate.of(1997, 6, 26), Genre.FANTASY, "9780747532699", "Harry Potter and the Philosopher's Stone", au5));
        Publication p6 = publicationRepo.save(new Publication(LocalDate.of(1977, 1, 1), Genre.FANTASY, "9780450040184", "The Shining", au6));
        Publication p7 = publicationRepo.save(new Publication(LocalDate.of(1960, 7, 11), Genre.YOUTH, "9780061120084", "To Kill a Mockingbird", au7));
        Publication p8 = publicationRepo.save(new Publication(LocalDate.of(1925, 4, 10), Genre.COMEDY, "9780743273565", "The Great Gatsby", au8));
        Publication p9 = publicationRepo.save(new Publication(LocalDate.of(1952, 9, 1), Genre.COMEDY, "9780684803357", "The Old Man and the Sea", au9));
        Publication p10 = publicationRepo.save(new Publication(LocalDate.of(1869, 1, 1), Genre.FANTASY, "9780199232765", "War and Peace", au10));
//        publicationRepo.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        // Book
        Book b1 = new Book("1st", PublicationItemFormat.HARDCOPY, 15.99, 2.99, PublicationItemStatus.AVAILABLE, p1);
        Book b2 = new Book("2nd",PublicationItemFormat.EBOOK, 9.99, 1.99, PublicationItemStatus.AVAILABLE, p1);
        Book b3 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 12.99, 1.49, PublicationItemStatus.AVAILABLE, p1);
        Book b4 = new Book("1st", PublicationItemFormat.HARDCOPY, 12.99, 2.49, PublicationItemStatus.AVAILABLE, p2);
        Book b5 = new Book("2nd",PublicationItemFormat.EBOOK, 8.99, 1.49, PublicationItemStatus.AVAILABLE, p2);
        Book b6 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 10.99, 1.29, PublicationItemStatus.AVAILABLE, p2);
        Book b14 = new Book("1st", PublicationItemFormat.HARDCOPY, 13.99, 2.19, PublicationItemStatus.AVAILABLE, p6);
        Book b15 = new Book("2nd",PublicationItemFormat.EBOOK, 10.99, 1.49, PublicationItemStatus.AVAILABLE, p6);
        Book b16 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 11.99, 1.29, PublicationItemStatus.AVAILABLE, p6);
        Book b20 = new Book("1st", PublicationItemFormat.HARDCOPY, 11.99, 1.99, PublicationItemStatus.AVAILABLE, p8);
        Book b21 = new Book("2nd",PublicationItemFormat.EBOOK, 8.99, 1.29, PublicationItemStatus.AVAILABLE, p8);
        Book b22 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 9.99, 1.09, PublicationItemStatus.AVAILABLE, p8);
        Book b26 = new Book("1st", PublicationItemFormat.HARDCOPY, 16.99, 2.49, PublicationItemStatus.AVAILABLE, p10);
        Book b27 = new Book("2nd",PublicationItemFormat.EBOOK, 12.99, 1.49, PublicationItemStatus.AVAILABLE, p10);
        Book b28 = new Book("Audio", PublicationItemFormat.AUDIOBOOK, 13.99, 1.19, PublicationItemStatus.AVAILABLE, p10);
        itemRepo.saveAll(List.of(b1, b2, b3, b4, b5, b6, b14, b15, b16, b20, b21, b22, b26, b27, b28));

// Journal
        Journal j7 = new Journal("Vol. 1", PublicationItemFormat.HARDCOPY, 10.99, 1.99, PublicationItemStatus.AVAILABLE, p3, LocalDate.of(1920, 1, 1), "Mystery Issue", 1, "I");
        Journal j8 = new Journal("Vol. 1", PublicationItemFormat.AUDIOBOOK, 8.99, 1.19, PublicationItemStatus.AVAILABLE, p3, LocalDate.of(1920, 1, 1), "Mystery Issue Audio", 2, "I");
        Journal j17 = new Journal("Vol. 1", PublicationItemFormat.HARDCOPY, 12.99, 1.99, PublicationItemStatus.AVAILABLE, p7, LocalDate.of(1960, 7, 11), "Mockingbird Issue", 1, "I");
        Journal j18 = new Journal("Vol. 1",PublicationItemFormat.EBOOK, 9.99, 1.29, PublicationItemStatus.AVAILABLE, p7, LocalDate.of(1960, 7, 11), "Mockingbird Issue Digital", 2, "I");
        Journal j19 = new Journal("Vol. 1", PublicationItemFormat.AUDIOBOOK, 10.99, 1.19, PublicationItemStatus.AVAILABLE, p7, LocalDate.of(1960, 7, 11), "Mockingbird Issue Audio", 3, "I");
        itemRepo.saveAll(List.of(j7, j8, j17, j18, j19));

// LiteraryPiece
        LiteraryPiece lp9 = new LiteraryPiece("Classic", PublicationItemFormat.HARDCOPY,  7.99, 1.29, PublicationItemStatus.AVAILABLE, p4, LiteraryType.STORY);
        LiteraryPiece lp10 = new LiteraryPiece("Classic", PublicationItemFormat.AUDIOBOOK,  6.99, 0.99, PublicationItemStatus.AVAILABLE, p4, LiteraryType.STORY);
        LiteraryPiece lp11 = new LiteraryPiece("Magic", PublicationItemFormat.HARDCOPY,  11.99, 1.99, PublicationItemStatus.AVAILABLE, p5, LiteraryType.STORY);
        LiteraryPiece lp12 = new LiteraryPiece("Magic",PublicationItemFormat.EBOOK,  8.99, 1.29, PublicationItemStatus.AVAILABLE, p5, LiteraryType.STORY);
        LiteraryPiece lp13 = new LiteraryPiece("Magic", PublicationItemFormat.AUDIOBOOK,  9.99, 1.49, PublicationItemStatus.AVAILABLE, p5, LiteraryType.STORY);
        LiteraryPiece lp23 = new LiteraryPiece("Classic", PublicationItemFormat.HARDCOPY,  10.99, 1.49, PublicationItemStatus.AVAILABLE, p9, LiteraryType.STORY);
        LiteraryPiece lp24 = new LiteraryPiece("Classic",PublicationItemFormat.EBOOK,  7.99, 1.09, PublicationItemStatus.AVAILABLE, p9, LiteraryType.STORY);
        LiteraryPiece lp25 = new LiteraryPiece("Classic", PublicationItemFormat.AUDIOBOOK,  8.99, 0.99, PublicationItemStatus.AVAILABLE, p9, LiteraryType.STORY);
        itemRepo.saveAll(List.of(lp9, lp10, lp11, lp12, lp13, lp23, lp24, lp25));

// Customer
//        Customer c1 = new Customer("alex.smith@email.com", "Alex", "Smith", "555-1010");
//        Customer c2 = new Customer("maria.garcia@email.com", "Maria", "Garcia", "555-2020");
//        Customer c3 = new Customer("john.doe@email.com", "John", "Doe", "555-3030");
//        Customer c4 = new Customer("emily.jones@email.com", "Emily", "Jones", "555-4040");
//        Customer c5 = new Customer("michael.brown@email.com", "Michael", "Brown", "555-5050");
//        Customer c6 = new Customer("sophia.martin@email.com", "Sophia", "Martin", "555-6060");
//        Customer c7 = new Customer("liam.wilson@email.com", "Liam", "Wilson", "555-7070");
//        Customer c8 = new Customer("olivia.moore@email.com", "Olivia", "Moore", "555-8080");
//        Customer c9 = new Customer("noah.taylor@email.com", "Noah", "Taylor", "555-9090");
//        Customer c10 = new Customer("ava.thomas@email.com", "Ava", "Thomas", "555-0101");
//        customerRepo.saveAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
//
//// Orders
//        Order o3 = new Order(LocalDateTime.of(2025, 7, 3, 9, 45), 2.99, c3);
//        Order o4 = new Order(LocalDateTime.of(2025, 7, 4, 16, 20), 3.49, c4);
//        Order o5 = new Order(LocalDateTime.of(2025, 7, 5, 11, 10), 2.49, c5);
//        Order o6 = new Order(LocalDateTime.of(2025, 7, 6, 13, 0), 12.99, c6);
//        Order o7 = new Order(LocalDateTime.of(2025, 7, 7, 15, 40), 1.99, c7);
//        Order o8 = new Order(LocalDateTime.of(2025, 7, 8, 17, 25), 2.99, c8);
//        Order o9 = new Order(LocalDateTime.of(2025, 7, 9, 12, 5), 1.49, c9);
//        Order o10 = new Order(LocalDateTime.of(2025, 7, 10, 18, 15), 2.49, c10);
//        Order o11 = new Order(LocalDateTime.of(2025, 7, 11, 9, 0), 2.99, c1);
//        Order o12 = new Order(LocalDateTime.of(2025, 7, 12, 10, 30), 1.99, c2);
//        Order o13 = new Order(LocalDateTime.of(2025, 7, 13, 11, 45), 2.99, c3);
//        Order o14 = new Order(LocalDateTime.of(2025, 7, 14, 13, 15), 1.99, c4);
//        Order o15 = new Order(LocalDateTime.of(2025, 7, 15, 14, 25), 2.99, c5);
//        Order o16 = new Order(LocalDateTime.of(2025, 7, 16, 15, 35), 2.99, c6);
//        Order o17 = new Order(LocalDateTime.of(2025, 7, 17, 16, 45), 2.99, c7);
//        Order o18 = new Order(LocalDateTime.of(2025, 7, 18, 10, 0), 25.47, c1);
//        Order o19 = new Order(LocalDateTime.of(2025, 7, 19, 11, 30), 19.47, c2);
//        Order o20 = new Order(LocalDateTime.of(2025, 7, 20, 13, 15), 22.97, c3);
//        orderRepo.saveAll(List.of(o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20));
//
//// Purchases (match item and order references)
//        Purchase pu1 = new Purchase(1L, LocalDate.of(2025, 7, 1), 15.99, "PURCHASE", b1, o1);
//        Purchase pu2 = new Purchase(2L, LocalDate.of(2025, 7, 2), 9.99, "PURCHASE", b2, o2);
//        Purchase pu3 = new Purchase(3L, LocalDate.of(2025, 7, 3), 2.99, "PURCHASE", b3, o3);
//        Purchase pu4 = new Purchase(4L, LocalDate.of(2025, 7, 4), 3.49, "PURCHASE", b4, o4);
//        Purchase pu5 = new Purchase(5L, LocalDate.of(2025, 7, 5), 2.49, "PURCHASE", b5, o5);
//        Purchase pu6 = new Purchase(6L, LocalDate.of(2025, 7, 6), 12.99, "PURCHASE", b6, o6);
//        Purchase pu7 = new Purchase(7L, LocalDate.of(2025, 7, 7), 1.99, "PURCHASE", b14, o7);
//        Purchase pu8 = new Purchase(8L, LocalDate.of(2025, 7, 8), 2.99, "PURCHASE", b15, o8);
//        Purchase pu9 = new Purchase(9L, LocalDate.of(2025, 7, 9), 1.49, "PURCHASE", b16, o9);
//        Purchase pu10 = new Purchase(10L, LocalDate.of(2025, 7, 10), 2.49, "PURCHASE", b20, o10);
//        Purchase pu11 = new Purchase(11L, LocalDate.of(2025, 7, 11), 2.99, "PURCHASE", b21, o11);
//        Purchase pu12 = new Purchase(12L, LocalDate.of(2025, 7, 12), 1.99, "PURCHASE", b22, o12);
//        Purchase pu13 = new Purchase(13L, LocalDate.of(2025, 7, 13), 2.99, "PURCHASE", b26, o13);
//        Purchase pu14 = new Purchase(14L, LocalDate.of(2025, 7, 14), 1.99, "PURCHASE", b27, o14);
//        Purchase pu15 = new Purchase(15L, LocalDate.of(2025, 7, 15), 2.99, "PURCHASE", b28, o15);
//// Add more purchases as needed for all items/orders
//        purchaseRepo.saveAll(List.of(
//                pu1, pu2, pu3, pu4, pu5, pu6, pu7, pu8, pu9, pu10, pu11, pu12, pu13, pu14, pu15
//        ));
//
//// Rentals (match item and order references)
//        Rental r1 = new Rental(17L, LocalDate.of(2025, 7, 1), 2.99, "RENTAL", b3, o1, LocalDate.of(2025, 7, 15), LocalDate.of(2025, 7, 1), "RENTED");
//        Rental r2 = new Rental(18L, LocalDate.of(2025, 7, 6), 12.99, "RENTAL", b6, o6, LocalDate.of(2025, 7, 20), LocalDate.of(2025, 7, 6), "RENTED");
//        Rental r3 = new Rental(19L, LocalDate.of(2025, 7, 18), 1.99, "RENTAL", b14, o7, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 7, 18), "RENTED");
//        Rental r4 = new Rental(20L, LocalDate.of(2025, 7, 19), 1.99, "RENTAL", b15, o8, LocalDate.of(2025, 8, 2), LocalDate.of(2025, 7, 19), "RENTED");
//        Rental r5 = new Rental(21L, LocalDate.of(2025, 7, 20), 1.29, "RENTAL", b16, o9, LocalDate.of(2025, 8, 3), LocalDate.of(2025, 7, 20), "RENTED");
//        Rental r6 = new Rental(22L, LocalDate.of(2025, 7, 21), 1.49, "RENTAL", b20, o10, LocalDate.of(2025, 8, 4), LocalDate.of(2025, 7, 21), "RENTED");
//        Rental r7 = new Rental(23L, LocalDate.of(2025, 7, 22), 1.19, "RENTAL", b21, o11, LocalDate.of(2025, 8, 5), LocalDate.of(2025, 7, 22), "RENTED");
//        Rental r8 = new Rental(24L, LocalDate.of(2025, 7, 18), 9.00, "RENTAL", b22, o12, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 7, 18), "RENTED");
//        Rental r9 = new Rental(28L, LocalDate.of(2025, 7, 19), 5.00, "RENTAL", b26, o13, LocalDate.of(2025, 8, 2), LocalDate.of(2025, 7, 19), "RENTED");
//        Rental r10 = new Rental(32L, LocalDate.of(2025, 7, 20), 5.00, "RENTAL", b27, o14, LocalDate.of(2025, 8, 3), LocalDate.of(2025, 7, 20), "RENTED");
//// Add more rentals as needed for all items/orders
//        rentalRepo.saveAll(List.of(
//                r1, r2, r3, r4, r5, r6, r7, r8, r9, r10
//        ));
    }
}
