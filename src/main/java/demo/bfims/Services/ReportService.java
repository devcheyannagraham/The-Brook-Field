package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.PublicationDto;
import demo.bfims.DTOs.ReportDTOs.ItemGroup;
import demo.bfims.DTOs.ReportDTOs.PopularItem;
import demo.bfims.DTOs.ReportDTOs.PopularItemsDto;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.ItemOrderType;
import demo.bfims.Enums.ItemType;
import demo.bfims.Interfaces.Purchaseable;
import demo.bfims.Interfaces.Rentable;
import demo.bfims.Repo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        System.out.println("Popular Items");
        List<Transaction> transactions = transactionRepo.findAll();
        Map<Long, Integer> publicationMap = new HashMap<>();
        Map<Long, Double> publicationProfitMap = new HashMap<>();
        Map<Long, Integer> accessoryMap = new HashMap<>();
        Map<Long, Double> accessoryProfitMap = new HashMap<>();
        Map<Long, Integer> stationaryMap = new HashMap<>();
        Map<Long, Double> stationaryProfitMap = new HashMap<>();


        transactions.forEach(trans -> {
            ItemType itemType = trans.getItem().getItemType();
            if (itemType.equals(ItemType.PUBLICATION_ITEM)) {
                PublicationItem publicationItem = (PublicationItem) trans.getItem();
                Long publicationId = publicationItem.getPublication().getPublicationId();

                publicationMap.put(publicationId, publicationMap.getOrDefault(publicationId, 0) + 1);

                // store profits
                Double price = 0.0;
                if (trans.getTransactionType().equals(ItemOrderType.RENTAL)) {
                    price = ((Rentable) trans.getItem()).getRentalRate();
                } else if (trans.getTransactionType().equals(ItemOrderType.PURCHASE)) {
                    price = ((Purchaseable) trans.getItem()).getPurchasePrice();
                }
                publicationProfitMap.put(publicationId, publicationProfitMap.getOrDefault(publicationId, 0.0) + price);

            } else if (itemType.equals(ItemType.ACCESSORY_ITEM)) {
            } else if (itemType.equals(ItemType.STATIONARY_ITEM)) {
            }
        });

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
        System.out.println("\nLow Inventory Items");
        System.out.println("itemGroup: " + itemGroup);
        return itemGroup;
    }

    //low selling/renting items
    public List<ItemDto> getLowSalesItems() {
        System.out.println("Low Sales Items");
        return null;
    }

    //Items that make the most money
    public List<ItemDto> getProfitableItems() {
        System.out.println("Profitable Items");
        return null;
    }

    public List<ItemDto> getRecentOrders() {
        System.out.println("Recent Orders");
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
