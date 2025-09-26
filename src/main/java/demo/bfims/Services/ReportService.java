package demo.bfims.Services;

import demo.bfims.DTOs.ReportDTOs.InventoryCountDto;
import demo.bfims.DTOs.ReportDTOs.PopularItemDto;
import demo.bfims.DTOs.ReportDTOs.RecentOrderDto;
import demo.bfims.DTOs.ReportDTOs.ShopPopularItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Repo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final OrderRepo orderRepo;
    private final PublicationRepo publicationRepo;
    private final PublicationItemRepo publicationItemRepo;
    private final TransactionRepo transactionRepo;
    private final AccessoryRepo accessoryRepo;
    private final AccessoryItemRepo accessoryItemRepo;
    private final CustomerRepo customerRepo;
    private final UserRepo userRepo;

    public ReportService(OrderRepo orderRepo, PublicationRepo publicationRepo, PublicationItemRepo publicationItemRepo, TransactionRepo transactionRepo, AccessoryRepo accessoryRepo, AccessoryItemRepo accessoryItemRepo, CustomerRepo customerRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.publicationRepo = publicationRepo;
        this.publicationItemRepo = publicationItemRepo;
        this.transactionRepo = transactionRepo;
        this.accessoryRepo = accessoryRepo;
        this.accessoryItemRepo = accessoryItemRepo;
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
    }

    public List<ShopPopularItemDto> getShopPopularItems() {
        List<PopularItemDto> popularItemDtos = this.getPopularItems();
        return popularItemDtos.stream().map(ShopPopularItemDto::new).toList();
    }

    public List<PopularItemDto> getPopularItems() {
        // Get date for last 3 months
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);

        List<Transaction> transactions = transactionRepo.findAllTransactionsByTransactionDateAfter(threeMonthsAgo).orElse(null);
//        Get total transactions and sum of all transactions for each item
        Map<Long, Integer> publicationCountMap = new HashMap<>();
        Map<Long, Double> publicationProfitMap = new HashMap<>();
        Map<Long, Integer> accessoryCountMap = new HashMap<>();
        Map<Long, Double> accessoryProfitMap = new HashMap<>();

        if (transactions != null && !transactions.isEmpty()) {
            transactions.forEach(trans -> {
                ItemType itemType = trans.getItem().getItemType();
                if (itemType.equals(ItemType.PUBLICATION_ITEM)) {
                    PublicationItem publicationItem = (PublicationItem) trans.getItem();
                    Long publicationId = publicationItem.getPublication().getPublicationId();

                    // times sold or rented id:totaltransactions
                    publicationCountMap.put(publicationId, publicationCountMap.getOrDefault(publicationId, 0) + 1);
                    //id:total sales
                    publicationProfitMap.put(publicationId, publicationProfitMap.getOrDefault(publicationId, 0.0) + trans.getTransactionPrice());

                } else if (itemType.equals(ItemType.ACCESSORY_ITEM)) {
                    AccessoryItem accessoryItem = (AccessoryItem) trans.getItem();
                    Long accessoryId = accessoryItem.getAccessory().getAccessoryId();

                    // times sold
                    accessoryCountMap.put(accessoryId, accessoryCountMap.getOrDefault(accessoryId, 0) + 1);
                    accessoryProfitMap.put(accessoryId, accessoryProfitMap.getOrDefault(accessoryId, 0.0) + trans.getTransactionPrice());
                }
            });
        }

        List<PopularItemDto> popularItemDtos = new ArrayList<>();

        // get top 5 items by units sold
        publicationRepo.findAllById(getTopItems(publicationCountMap)).forEach(pub -> {
            PopularItemDto popularItemDto = new PopularItemDto(pub);
            popularItemDto.setTotalUnitsSold(publicationCountMap.get(pub.getPublicationId()));
            popularItemDto.setTotalProfit(publicationProfitMap.get(pub.getPublicationId()));
            popularItemDtos.add(popularItemDto);
        });

        accessoryRepo.findAllById(getTopItems(accessoryCountMap)).forEach(acc -> {
            PopularItemDto popularItemDto = new PopularItemDto(acc);
            popularItemDto.setTotalUnitsSold(accessoryCountMap.get(acc.getAccessoryId()));
            popularItemDto.setTotalProfit(accessoryProfitMap.get(acc.getAccessoryId()));
            popularItemDtos.add(popularItemDto);
        });
        return popularItemDtos;
    }

    public List<InventoryCountDto> getLowInventoryItems() {
        List<InventoryCountDto> lowInventoryItems = new ArrayList<>();

        List<Publication> publications = publicationRepo.findAll();
        List<Accessory> accessories = accessoryRepo.findAll();

        publications.forEach(pub -> {
            int total = publicationItemRepo.countPublicationItemsByPublication_publicationIdAndPublicationItemStatus(pub.getPublicationId(), PublicationItemStatus.AVAILABLE);
            if (total <= 5) {
                lowInventoryItems.add(new InventoryCountDto(pub, total));
            }
        });

        accessories.forEach(acc -> {
            int total = accessoryItemRepo.countAccessoryItemsByAccessory_AccessoryIdAndAccessoryItemStatus(acc.getAccessoryId(), AccessoryItemStatus.AVAILABLE);
            if (total <= 5) {
                lowInventoryItems.add(new InventoryCountDto(acc, total));
            }
        });
        return lowInventoryItems;
    }

    public List<RecentOrderDto> getRecentOrders() {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        List<Order> results = orderRepo.findOrdersByOrderDateAfter(threeMonthsAgo).orElse(null);
        if (results != null && !results.isEmpty()) {
            return results.stream().map(RecentOrderDto::new).collect(Collectors.toList());
        }
        return null;
    }

    public List<RecentOrderDto> getRecentOrders(Long userId) {
        if (userId == null) return null;
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);

        // UserId != CustomerId
        User user = userRepo.findUserByUserId(userId).orElse(null);
        if (user != null && user.getCustomer() != null && user.getCustomer().getId() != null) {

            List<Order> results = orderRepo.findOrdersByOrderDateAfterAndCustomerId(threeMonthsAgo, user.getCustomer().getId()).orElse(null);
            if (results != null && !results.isEmpty()) {
                return results.stream().map(RecentOrderDto::new).collect(Collectors.toList());
            }
        }
        return null;
    }

    // Helper
    // returns top 5 items
    public List<Long> getTopItems(Map<Long, Integer> map) {
        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return entryList.subList(0, Math.min(entryList.size(), 5)).stream().map(Map.Entry::getKey).toList();
    }
}
