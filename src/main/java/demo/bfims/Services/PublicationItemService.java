package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Enums.ItemType;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationRepo;
import jakarta.persistence.EntityManager;
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
    @Autowired
    EntityManager entityManager;

    @Transactional
    public PublicationItemDto newPublicationItem(PublicationItemDto publicationItemDto) {
        PublicationItem publicationItem = modelMapper.map(publicationItemDto, PublicationItem.class);
        Long pubId = publicationItem.getPublication().getPublicationId();
        if (pubId != null) {
            Publication publication = publicationRepo.findById(pubId).orElse(null);
            Publication managedPublication = entityManager.merge(publication);
            publicationItem.setPublication(managedPublication);
        }
        return modelMapper.map(itemRepo.save(publicationItem), PublicationItemDto.class);
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

    public List<PublicationDto> getPublications(){
        List<Publication> publications = publicationRepo.findAll();
        if(!publications.isEmpty()) {
            return publications.stream().map(p -> modelMapper.map(p, PublicationDto.class)).toList();
        }
        else return null;
    }

}
