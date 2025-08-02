package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.DTOs.ReportDTOs.ItemGroup;
import demo.bfims.DTOs.ReportDTOs.PopularItem;
import demo.bfims.DTOs.ReportDTOs.PopularItemsDto;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.ItemType;
import demo.bfims.Repo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    PublicationRepo publicationRepo;
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccessoryRepo accessoryRepo;

    public PopularItemsDto getPopularItems() {
        // Get date for last 6 months
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        List<Transaction> transactions = transactionRepo.findAllTransactionsByTransactionDateAfter(sixMonthsAgo).orElse(null);
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

        // get top 5 items by units sold
        PopularItemsDto popularItemsDto = new PopularItemsDto();

        publicationRepo.findAllById(getTopItems(publicationCountMap))
                .forEach(pub -> {
                    PublicationDto pubDto = modelMapper.map(pub, PublicationDto.class);
                    PopularItem<PublicationDto> popularPublicationDto = new PopularItem<>();
                    popularPublicationDto.setPopularItem(pubDto);
                    popularPublicationDto.setTotalUnitsSold(publicationCountMap.get(pub.getPublicationId()));
                    popularPublicationDto.setTotalProfit(publicationProfitMap.get(pub.getPublicationId()));
                    popularItemsDto.getPopularPublicationsDto().add(popularPublicationDto);
                });

        accessoryRepo.findAllById(getTopItems(accessoryCountMap))
                .forEach(acc -> {
                    AccessoryDto accDto = modelMapper.map(acc, AccessoryDto.class);
                    PopularItem<AccessoryDto> popularAccessoryDto = new PopularItem<>();
                    popularAccessoryDto.setPopularItem(accDto);
                    popularAccessoryDto.setTotalUnitsSold(accessoryCountMap.get(acc.getAccessoryId()));
                    popularAccessoryDto.setTotalProfit(accessoryProfitMap.get(acc.getAccessoryId()));
                    popularItemsDto.getPopularAccessoriesDto().add(popularAccessoryDto);

                });
        return popularItemsDto;
    }

    public ItemGroup getLowInventoryItems() {
//        ItemGroup itemGroup = new ItemGroup();
//        // Low inventory threshold is 10
//        itemGroup.setPublications(publicationRepo.findByPublicationQuantityLessThanEqual(50)
//                .stream().map(pub -> modelMapper.map(pub, PublicationDto.class)).toList());
//
//        //Need to add other itmeGroups
//        itemGroup.setAccessories(accessoryRepo.findByQuantityLessThanEqual(50)
//                .stream().map(acc -> modelMapper.map(acc, AccessoryDto.class)).toList());
//
//        return itemGroup;

        System.out.println("_".repeat(15));
        System.out.println("IMPLEMENT get low inventory items\n");
        return null;
    }

    public List<OrderDto> getRecentOrders() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<Order> results = orderRepo.findOrdersByOrderDateAfter(sixMonthsAgo).orElse(null);
        if (results != null && !results.isEmpty()) {
            return results.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        }
        return null;
    }

    // Helper
    public List<Long> getTopItems(Map<Long, Integer> map) {
        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
       return entryList.subList(0, Math.min(entryList.size(), 5)).stream().map(Map.Entry::getKey).toList();
    }
}
