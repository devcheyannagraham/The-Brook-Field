package demo.bfims.Config;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.*;
import demo.bfims.Interfaces.Purchaseable;
import demo.bfims.Interfaces.Rentable;
import demo.bfims.Repo.*;
import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Component
public class Bootstrap implements CommandLineRunner {
    private final TransactionRepo transactionRepo;
    AccessoryRepo accessoryRepo;
    ItemRepo itemRepo;
    AuthorRepo authorRepo;
    PublicationRepo publicationRepo;
    EntityManager entityManager;
    UserRepo userRepo;
    CustomerRepo customerRepo;
    OrderRepo orderRepo;


    public Bootstrap(OrderRepo orderRepo, CustomerRepo customerRepo, AccessoryRepo accessoryRepo, ItemRepo itemRepo, AuthorRepo authorRepo, PublicationRepo publicationRepo, EntityManager entityManager, UserRepo userRepo, TransactionRepo transactionRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.accessoryRepo = accessoryRepo;
        this.itemRepo = itemRepo;
        this.authorRepo = authorRepo;
        this.publicationRepo = publicationRepo;
        this.entityManager = entityManager;
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.bootstrap();
    }

    public void bootstrap() {
        System.out.println("Bootstrap");

        //Create admin user
        User admin = new User();
        admin.setEmail("bfadmin");
        admin.setSalt(User.generateSalt());
        admin.setPassword(User.hashPassword("bfadmin", admin.getSalt()));
        admin.setUserRole(UserRole.ADMIN);
        userRepo.save(admin);


        //Create authors
        String[][] authorNames = {
                {"Avery", "Sinclair"}, {"Riley", "Harper"}, {"Jordan", "Lee"},
                {"Morgan", "Blake"}, {"Casey", "Morgan"},
                {"Taylor", "Quinn"}, {"Peyton", "Brooks"}, {"Skyler", "Dawson"},
                {"Emerson", "Reed"}, {"Dakota", "Shaw"},
                {"Jamie", "Rowe"}, {"Morgan", "Vale"}, {"Drew", "Hollis"},
                {"Reese", "Callahan"}, {"Quinn", "Sawyer"},
                {"Jules", "Bennett"}, {"Rowan", "Ellis"}, {"Sage", "Marlow"},
                {"Blake", "Finch"}, {"Parker", "Sloane"},
                {"Finley", "Rowan"}, {"Sawyer", "Blake"}, {"Reagan", "Sage"},
                {"Kendall", "Avery"}, {"Parker", "Hayes"},
                {"Dylan", "Quinn"}, {"Rowan", "Taylor"}, {"Jordan", "Reese"}, {"Skyler", "Drew"}, {"Braxton", "Reed"}
        };
        List<Author> authors = authorRepo.saveAll(Arrays.stream(authorNames).map(author -> new Author(author[0], author[1])).toList());

        //Create publications
        List<Publication> publications = publicationRepo.saveAll(List.of(
                new Publication(LocalDate.of(2015, 1, 10), Genre.COMEDY, "9780001000", "Laugh Lines: A Satirical Anthology", authors.get(0)),
                new Publication(LocalDate.of(2016, 2, 11), Genre.YOUTH, "9780001001", "The Secret Treehouse Club", authors.get(1)),
                new Publication(LocalDate.of(2017, 3, 12), Genre.SCIFI, "9780001002", "Nebula Drift: Science Stories", authors.get(2)),
                new Publication(LocalDate.of(2018, 4, 13), Genre.SCIFI, "9780001003", "Starlight Protocol: Future Poems", authors.get(3)),
                new Publication(LocalDate.of(2019, 5, 14), Genre.FANTASY, "9780001004", "The Last Ember: A Fantasy Tale", authors.get(4)),
                new Publication(LocalDate.of(2020, 6, 15), Genre.FANTASY, "9780001005", "Wings of Myth: Fantasy Essays", authors.get(5)),
                new Publication(LocalDate.of(2021, 7, 16), Genre.YOUTH, "9780001006", "Reflections Unbound: Youthful Essays", authors.get(6)),
                new Publication(LocalDate.of(2022, 8, 17), Genre.COMEDY, "9780001007", "The Starlit Journal of Humor", authors.get(7)),
                new Publication(LocalDate.of(2023, 9, 18), Genre.COMEDY, "9780001008", "Echoes of Laughter: Comic Poems", authors.get(8)),
                new Publication(LocalDate.of(2024, 10, 19), Genre.FANTASY, "9780001009", "Fragments of Magic: Fantasy Reviews", authors.get(9)),
                new Publication(LocalDate.of(2015, 11, 20), Genre.FANTASY, "9780001010", "The Dragon's Oath", authors.get(10)),
                new Publication(LocalDate.of(2016, 12, 11), Genre.COMEDY, "9780001011", "Laughing at the Moon: Poems", authors.get(11)),
                new Publication(LocalDate.of(2017, 1, 12), Genre.YOUTH, "9780001012", "The Hidden Locker: A School Story", authors.get(12)),
                new Publication(LocalDate.of(2018, 2, 13), Genre.SCIFI, "9780001013", "Galactic Frontiers: Journal of Space", authors.get(13)),
                new Publication(LocalDate.of(2019, 3, 14), Genre.FANTASY, "9780001014", "Wings of the Forgotten: An Epic", authors.get(14)),
                new Publication(LocalDate.of(2020, 4, 15), Genre.COMEDY, "9780001015", "The Satirist's Review", authors.get(15)),
                new Publication(LocalDate.of(2021, 5, 16), Genre.YOUTH, "9780001016", "Summer of Secrets: Youth Essays", authors.get(16)),
                new Publication(LocalDate.of(2022, 6, 17), Genre.SCIFI, "9780001017", "NanoVerse: Science Articles", authors.get(17)),
                new Publication(LocalDate.of(2023, 7, 18), Genre.FANTASY, "9780001018", "The Enchanted Quill: Poems", authors.get(18)),
                new Publication(LocalDate.of(2024, 8, 19), Genre.COMEDY, "9780001019", "Comic Reflections: Essays and Reviews", authors.get(19)),
                new Publication(LocalDate.of(2025, 1, 1), Genre.FANTASY, "97800000301", "The Wandering Star", authors.get(20)),
                new Publication(LocalDate.of(2025, 1, 2), Genre.YOUTH, "97800000302", "Echoes of the Forest", authors.get(21)),
                new Publication(LocalDate.of(2025, 1, 3), Genre.COMEDY, "97800000303", "Midnight Verses", authors.get(22)),
                new Publication(LocalDate.of(2025, 1, 4), Genre.SCIFI, "97800000304", "The Infinite Path", authors.get(23)),
                new Publication(LocalDate.of(2025, 1, 5), Genre.FANTASY, "97800000305", "Shifting Sands", authors.get(24)),
                new Publication(LocalDate.of(2025, 1, 6), Genre.COMEDY, "97800000306", "The Glass Horizon", authors.get(25)),
                new Publication(LocalDate.of(2025, 1, 7), Genre.YOUTH, "97800000307", "River of Whispers", authors.get(26)),
                new Publication(LocalDate.of(2025, 1, 8), Genre.SCIFI, "97800000308", "The Forgotten Key", authors.get(27)),
                new Publication(LocalDate.of(2025, 1, 9), Genre.FANTASY, "97800000309", "Dreams of Ember", authors.get(28)),
                new Publication(LocalDate.of(2025, 1, 10), Genre.COMEDY, "97800000310", "The Painted Veil", authors.get(29))
        ));


        // Create publication Items
        List<Book> books = new ArrayList<>();
        List<Journal> journals = new ArrayList<>();
        List<LiteraryPiece> literaryPieces = new ArrayList<>();
        for (Publication pub : publications) {
            for (int i = 1; i <= 5; i++) {
                // Book
                books.add(new Book(i + "th Edition", PublicationItemFormat.HARDCOPY, 14.99 + i, 2.49 + i * 0.1, PublicationItemStatus.AVAILABLE, pub));

                // Journal
                journals.add(new Journal("Vol. " + i, PublicationItemFormat.EBOOK, 9.99 + i, 1.49 + i * 0.1, PublicationItemStatus.AVAILABLE, pub, LocalDate.of(2020 + (i % 5), (i % 12) + 1, (i % 28) + 1), String.valueOf(i), "ISSUE" + i, "Volume " + i % 3));

                // LiteraryPiece subtypes
                literaryPieces.add(new LiteraryPiece("Essay Edition " + i, PublicationItemFormat.EBOOK, 7.99 + i, 1.19 + i * 0.1, PublicationItemStatus.AVAILABLE, pub, LiteraryType.ESSAY));
                if (i == 1)
                    literaryPieces.add(new LiteraryPiece("Poem Edition " + i, PublicationItemFormat.EBOOK, 7.49 + i, 1.09 + i * 0.1, PublicationItemStatus.AVAILABLE, pub, LiteraryType.POEM));
                if (i == 2)
                    literaryPieces.add(new LiteraryPiece("Article Edition " + i, PublicationItemFormat.EBOOK, 7.29 + i, 1.05 + i * 0.1, PublicationItemStatus.AVAILABLE, pub, LiteraryType.ARTICLE));
                if (i == 3)
                    literaryPieces.add(new LiteraryPiece("Story Edition " + i, PublicationItemFormat.EBOOK, 7.89 + i, 1.15 + i * 0.1, PublicationItemStatus.AVAILABLE, pub, LiteraryType.STORY));
                if (i == 4)
                    literaryPieces.add(new LiteraryPiece("Review Edition " + i, PublicationItemFormat.EBOOK, 7.59 + i, 1.11 + i * 0.1, PublicationItemStatus.AVAILABLE, pub, LiteraryType.REVIEW));
            }
        }
        itemRepo.saveAll(books);
        itemRepo.saveAll(journals);
        itemRepo.saveAll(literaryPieces);

        //Create Accessories
        List<Accessory> accessories = accessoryRepo.saveAll(List.of(
                new Accessory("Classic Bookmark", AccessoryType.BOOKMARK, 1.99),
                new Accessory("Leather Bookmark", AccessoryType.BOOKMARK, 3.49),
                new Accessory("Ceramic Mug", AccessoryType.MUG, 7.99),
                new Accessory("Travel Mug", AccessoryType.MUG, 9.99),
                new Accessory("Signature Pen", AccessoryType.PEN, 2.99),
                new Accessory("Magnetic Bookmark", AccessoryType.BOOKMARK, 2.49),
                new Accessory("Insulated Mug", AccessoryType.MUG, 11.99),
                new Accessory("Gel Pen", AccessoryType.PEN, 1.49),
                new Accessory("Metal Pen", AccessoryType.PEN, 3.99),
                new Accessory("Artisan Mug", AccessoryType.MUG, 12.49),
                new Accessory("Vintage Pen", AccessoryType.PEN, 4.49),
                new Accessory("Eco Mug", AccessoryType.MUG, 10.49),
                new Accessory("Fabric Bookmark", AccessoryType.BOOKMARK, 2.79),
                new Accessory("Collector's Mug", AccessoryType.MUG, 15.99),
                new Accessory("Premium Bookmark", AccessoryType.BOOKMARK, 3.99)
        ));

        // Create 5 accessory items per accessory
        List<AccessoryItem> accessoryItems = new ArrayList<>();
        for (Accessory accessory : accessories) {
            for (int i = 0; i < 5; i++) {
                accessoryItems.add(new AccessoryItem(accessory, AccessoryItemStatus.AVAILABLE));
            }
        }
        itemRepo.saveAll(accessoryItems);

        //Create purchases and rentals
        // Purchases: any item
        List<Purchaseable> purchaseableItems = new ArrayList<>();
        purchaseableItems.addAll(books);
        purchaseableItems.addAll(journals);
        purchaseableItems.addAll(literaryPieces);
        purchaseableItems.addAll(accessoryItems);

        // Rentals: only publication items (books, journals, literary pieces)
        List<Rentable> rentableItems = new ArrayList<>();
        rentableItems.addAll(books);
        rentableItems.addAll(journals);
        rentableItems.addAll(literaryPieces);

        List<Purchase> purchases = new ArrayList<>();
        List<Rental> rentals = new ArrayList<>();

        Collections.shuffle(purchaseableItems);
        Collections.shuffle(rentableItems);

        for (int i = 0; i < 15; i++) {
            purchases.add(new Purchase((Item) purchaseableItems.get(i), purchaseableItems.get(i).getPurchasePrice()));
            rentals.add(new Rental((Item) rentableItems.get(i), rentableItems.get(i).getRentalRate()));
        }
        transactionRepo.saveAll(purchases);
        transactionRepo.saveAll(rentals);

        //Create customer
        List<Customer> customers = customerRepo.saveAll(List.of(
                new Customer("alex.smith@email.com", "Alex", "Smith", "+44 20 7946 1010", "12 Abbey Road", "London", "Greater London", "NW8 9AY", "UK"),
                new Customer("maria.garcia@email.com", "Maria", "Garcia", "+44 20 7946 2020", "221B Baker Street", "London", "Greater London", "NW1 6XE", "UK"),
                new Customer("john.doe@email.com", "John", "Doe", "+1-555-3030", "5 Rose Crescent", "Cambridge", "Cambridgeshire", "CB2 3LL", "UK"),
                new Customer("emily.jones@email.com", "Emily", "Jones", "+44 20 7946 5678", "10 Downing Street", "London", "Greater London", "SW1A 2AA", "UK"),
                new Customer("michael.brown@email.com", "Michael", "Brown", "+1-555-5050", "50 Castle Street", "Edinburgh", "Midlothian", "EH2 3LU", "UK"),
                new Customer("sophia.martin@email.com", "Sophia", "Martin", "+44 20 7946 6060", "88 Ocean View", "Brighton", "East Sussex", "BN2 1TB", "UK"),
                new Customer("liam.wilson@email.com", "Liam", "Wilson", "+44 20 7946 7070", "7 King Street", "Manchester", "Greater Manchester", "M2 6AW", "UK"),
                new Customer("olivia.moore@email.com", "Olivia", "Moore", "+44 20 7946 8080", "22 Queen's Road", "Bristol", "Bristol", "BS8 1RE", "UK"),
                new Customer("noah.taylor@email.com", "Noah", "Taylor", "+44 20 7946 9090", "34 Willow Lane", "Oxford", "Oxfordshire", "OX1 4LF", "UK"),
                new Customer("ava.thomas@email.com", "Ava", "Thomas", "+44 20 7946 0101", "19 Maplewood Avenue", "Leeds", "West Yorkshire", "LS6 1AP", "UK"),
                new Customer("benjamin.evans@email.com", "Benjamin", "Evans", "+1-555-1111", "742 Evergreen Terrace", "Springfield", "IL", "62704", "USA"),
                new Customer("chloe.harris@email.com", "Chloe", "Harris", "+1-555-2222", "1600 Pennsylvania Ave NW", "Washington", "DC", "20500", "USA"),
                new Customer("lucas.mitchell@email.com", "Lucas", "Mitchell", "+1-555-3333", "350 Fifth Avenue", "New York", "NY", "10118", "USA"),
                new Customer("mia.campbell@email.com", "Mia", "Campbell", "+1-555-4444", "1 Infinite Loop", "Cupertino", "CA", "95014", "USA"),
                new Customer("jackson.clark@email.com", "Jackson", "Clark", "+1-555-5555", "4059 Mt Lee Dr", "Los Angeles", "CA", "90068", "USA")
        ));

        //Create orders
        orderRepo.saveAll(List.of(
                new Order(List.of(purchases.get(0), rentals.get(0)), customers.get(0)),
                new Order(List.of(purchases.get(1), rentals.get(1)), customers.get(2)),
                new Order(List.of(purchases.get(2), rentals.get(2)), customers.get(3)),
                new Order(List.of(purchases.get(3), rentals.get(3)), customers.get(4)),
                new Order(List.of(purchases.get(4), rentals.get(4)), customers.get(5)),
                new Order(List.of(purchases.get(5), rentals.get(5)), customers.get(6)),
                new Order(List.of(purchases.get(6), rentals.get(6)), customers.get(7)),
                new Order(List.of(purchases.get(7), rentals.get(7)), customers.get(8)),
                new Order(List.of(purchases.get(8), rentals.get(8)), customers.get(9)),
                new Order(List.of(purchases.get(9), rentals.get(9)), customers.get(10)),
                new Order(List.of(purchases.get(10), rentals.get(10)), customers.get(11)),
                new Order(List.of(purchases.get(11), rentals.get(11)), customers.get(12)),
                new Order(List.of(purchases.get(12), rentals.get(12)), customers.get(13)),
                new Order(List.of(purchases.get(13), rentals.get(13)), customers.get(14)),
                new Order(List.of(purchases.get(14), rentals.get(14)), customers.get(1))
        ));
        System.out.println("Data bootstrapping completed.");

    }
}
