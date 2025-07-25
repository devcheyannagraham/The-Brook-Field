package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.DTOs.ReportDTOs.ItemGroup;
import demo.bfims.DTOs.ReportDTOs.PopularItem;
import demo.bfims.DTOs.ReportDTOs.PopularItemsDto;
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

    public PopularItemsDto getPopularItems() {
        // Get date for last 6 months
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        List<Transaction> transactions = transactionRepo.findAllTransactionsByTransactionDateAfter(sixMonthsAgo).orElse(null);
        Map<Long, Integer> publicationMap = new HashMap<>();
        Map<Long, Double> publicationProfitMap = new HashMap<>();
        Map<Long, Integer> accessoryMap = new HashMap<>();
        Map<Long, Double> accessoryProfitMap = new HashMap<>();

        System.out.println("Popular Items" + transactions);

        if (transactions != null && !transactions.isEmpty()) {
            transactions.forEach(trans -> {
                // this is returning null
                ItemType itemType = trans.getItem().getItemType();
                if (itemType.equals(ItemType.PUBLICATION_ITEM)) {
                    PublicationItem publicationItem = (PublicationItem) trans.getItem();
                    Long publicationId = publicationItem.getPublication().getPublicationId();

                    publicationMap.put(publicationId, publicationMap.getOrDefault(publicationId, 0) + 1);
                    publicationProfitMap.put(publicationId, publicationProfitMap.getOrDefault(publicationId, 0.0) + trans.getTransactionPrice());

                } else if (itemType.equals(ItemType.ACCESSORY_ITEM)) {
                }
            });
        }

        // get top 10 items
        System.out.println("\nPublication Profit Map" + publicationProfitMap);
        System.out.println("\nPublicationMap" + publicationMap);

        PopularItemsDto popularItemsDto = new PopularItemsDto();

        publicationRepo.findAllById(publicationMap.keySet())
                .forEach(pub -> {
                    PublicationDto pubDto = modelMapper.map(pub, PublicationDto.class);
                    PopularItem<PublicationDto> popularPublicationDto = new PopularItem<>();
                    popularPublicationDto.setPopularItem(pubDto);
                    popularPublicationDto.setTotalUnitsSold(publicationMap.get(pub.getPublicationId()));
                    popularPublicationDto.setTotalProfit(publicationProfitMap.get(pub.getPublicationId()));
                    System.out.println("\n" + popularPublicationDto);
                    popularItemsDto.getPopularPublicationsDto().add(popularPublicationDto);
                });

        System.out.println("Publication Map: " + publicationMap);

//        System.out.println("UnSorted Publication Items" + publicationItemCounts);
//
//        sortMap(publicationItemCounts);
//        System.out.println("Sorted Publication Items" + publicationItemCounts);

        System.out.println("popularItemsDto: " + popularItemsDto);

        return popularItemsDto;
    }

    public ItemGroup getLowInventoryItems() {
        ItemGroup itemGroup = new ItemGroup();
        itemGroup.setPublications(publicationRepo.findByPublicationQuantityLessThanEqual(5)
                .stream().map(pub -> modelMapper.map(pub, PublicationDto.class))
                .collect(Collectors.toList()));

        //Need to add other itmeGroups
        return itemGroup;
    }

    public List<OrderDto> getRecentOrders() {
        LocalDateTime  sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<Order> results = orderRepo.findOrdersByOrderDateAfter(sixMonthsAgo).orElse(null);
        if(results != null && !results.isEmpty()) {
            return results.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        }
        return null;
    }

    // Helper
    public void sortMap(HashMap<Long, Integer> map) {
        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(map.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<Long, Integer>>() {
            @Override
            public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

    }


}
