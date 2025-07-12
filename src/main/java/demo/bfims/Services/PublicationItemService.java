package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.*;
import demo.bfims.Entities.Inventory.*;
import demo.bfims.Enums.ItemType;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicationItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private PublicationRepo publicationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public PublicationItemDto newPublicationItem(PublicationItemDto publicationItemDto) {
        PublicationItem publicationItem = itemRepo.save(modelMapper.map(publicationItemDto, PublicationItem.class));
        return modelMapper.map(publicationItem, PublicationItemDto.class);
    }

    public PublicationItemDto getPublicationItem(Long id) {
        Item item = itemRepo.findById(id).orElse(null);
        if (item != null) {
            return modelMapper.map(item, PublicationItemDto.class);
        }
        return null;
    }

    public List<PublicationItemDto> getPublicationItems() {
        List<Item> items = itemRepo.findItemsByItemType(ItemType.PUBLICATION_ITEM).orElse(null);
        if (items != null) {
            return items.stream().map(item -> modelMapper.map(item, PublicationItemDto.class)).toList();
        }
        return null;
    }

    @Transactional
    public Boolean deletePublicationItem(Long id) {
        try {
            itemRepo.deleteById(id);
            return true;
        }
        //Need to send error someway
        catch (Exception e) {
            return false;
        }
    }

}
