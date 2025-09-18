package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.DTOs.ReportDTOs.ItemCountDto;
import demo.bfims.DTOs.ReportDTOs.PopularItem;
import demo.bfims.DTOs.ReportDTOs.PopularItemsDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.ItemType;
import demo.bfims.Repo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private ItemRepo itemRepo;
    private OrderRepo orderRepo;
    private final PublicationRepo publicationRepo;
    private final PublicationItemRepo publicationItemRepo;
    private final TransactionRepo transactionRepo;
    private final AccessoryRepo accessoryRepo;
    private final AccessoryItemRepo accessoryItemRepo;

    public ReportService(ItemRepo itemRepo, OrderRepo orderRepo, PublicationRepo publicationRepo, PublicationItemRepo publicationItemRepo, TransactionRepo transactionRepo, AccessoryRepo accessoryRepo, AccessoryItemRepo accessoryItemRepo) {
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.publicationRepo = publicationRepo;
        this.publicationItemRepo = publicationItemRepo;
        this.transactionRepo = transactionRepo;
        this.accessoryRepo = accessoryRepo;
        this.accessoryItemRepo = accessoryItemRepo;
    }

    public List<PopularItem> getPopularItems() {
        // Get date for last 6 months
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        List<Transaction> transactions = transactionRepo.findAllTransactionsByTransactionDateAfter(sixMonthsAgo).orElse(null);
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

        List<PopularItem> popularItems = new ArrayList<>();

        // get top 5 items by units sold
        publicationRepo.findAllById(getTopItems(publicationCountMap)).forEach(pub -> {
            PopularItem popularItem = new PopularItem(pub);
            popularItem.setTotalUnitsSold(publicationCountMap.get(pub.getPublicationId()));
            popularItem.setTotalProfit(publicationProfitMap.get(pub.getPublicationId()));
            popularItems.add(popularItem);
        });

        accessoryRepo.findAllById(getTopItems(accessoryCountMap)).forEach(acc -> {
            PopularItem popularItem = new PopularItem(acc);
            popularItem.setTotalUnitsSold(accessoryCountMap.get(acc.getAccessoryId()));
            popularItem.setTotalProfit(accessoryProfitMap.get(acc.getAccessoryId()));
            popularItems.add(popularItem);
        });
        return popularItems;
    }

    public List<ItemCountDto> getLowInventoryItems() {
        List<ItemCountDto> lowInventoryItems = new ArrayList<>();

        List<Publication> publications = publicationRepo.findAll();
        List<Accessory> accessories = accessoryRepo.findAll();

        publications.forEach(pub -> {
            int total = publicationItemRepo.countPublicationItemsByPublication_publicationId(pub.getPublicationId());
            if (total <= 5) {
                lowInventoryItems.add(new ItemCountDto(pub, total));
            }
        });

        accessories.forEach(acc -> {
            int total = accessoryItemRepo.countAccessoryItemsByAccessory_AccessoryId(acc.getAccessoryId());
            if (total <= 5) {
                lowInventoryItems.add(new ItemCountDto(acc, total));
            }
        });
        return lowInventoryItems;
    }

    public List<OrderDto> getRecentOrders() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<Order> results = orderRepo.findOrdersByOrderDateAfter(sixMonthsAgo).orElse(null);
        if (results != null && !results.isEmpty()) {
            return results.stream().map(OrderDto::new).collect(Collectors.toList());
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
