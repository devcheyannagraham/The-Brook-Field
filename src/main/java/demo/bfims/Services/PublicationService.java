package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Publication.*;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationItemRepo;
import demo.bfims.Repo.PublicationRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicationService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private PublicationRepo publicationRepo;
    @Autowired
    EntityManager entityManager;
    @Autowired
    private PublicationItemRepo publicationItemRepo;

    @Transactional
    public PublicationItemDto newPublicationItem(PublicationItemDto publicationItemDto) {
        PublicationItem publicationItem = PublicationItem.mapToPublicationItemSubclass(publicationItemDto);
        Long pubId = null;
        if (publicationItem != null) {
            pubId = publicationItem.getPublication().getPublicationId();
        }
        //shouldnt be null
        if (pubId != null) {
            Publication publication = publicationRepo.findById(pubId).orElse(null);
            Publication managedPublication = entityManager.merge(publication);
            publicationItem.setPublication(managedPublication);
            publicationItem.setPublicationItemStatus(PublicationItemStatus.AVAILABLE);
            return new PublicationItemDto(itemRepo.save(publicationItem));
        }
        return null;
    }

    //
    public PublicationItemDto getPublicationItemById(Long id) {
        Item item = itemRepo.findById(id).orElse(null);
        if (item != null) {
            return PublicationItemDto.mapToPublicationItemDtoSubclass((PublicationItem) item);
        }
        return null;
    }

    //
    public List<PublicationItemDto> getPublicationItems() {
        List<Item> items = itemRepo.findItemsByItemType(ItemType.PUBLICATION_ITEM).orElse(null);
        if (items != null) {
            return items.stream().map(PublicationItemDto::new).toList();
        }
        return null;
    }

    @Transactional
    public Boolean deletePublicationItem(Long id) {
        try {
            itemRepo.deleteItemByItemId(id);
            return true;
        }
        //Need to send error someway
        catch (Exception e) {
            return false;
        }
    }

    public List<PublicationDto> getPublications() {
        List<Publication> publications = publicationRepo.findAll();
        if (!publications.isEmpty()) {
            return publications.stream().map(PublicationDto::new).toList();
        } else return null;
    }

    public PublicationDto getPublicationById(Long id) {
        if (id == null) return null;
        Publication publication = publicationRepo.findById(id).orElse(null);
        if (publication != null) {
            return new PublicationDto(publication);
        }
        return null;
    }

    public List<PublicationItemDto> getPublicationItemsByPublicationId(Long id) {
        if (id == null) return null;
        List<PublicationItem> items = publicationItemRepo.findPublicationItemsByPublication_PublicationId(id);
        if (items == null) return null;
        return items.stream().map(PublicationItemDto::mapToPublicationItemDtoSubclass).toList();
    }

    public List<PublicationItemDto> getAvailablePublicationItemsByPublicationId(Long id) {
        if (id == null) return null;
        List<PublicationItem> items = publicationItemRepo.findPublicationItemByPublication_PublicationIdAndPublicationItemStatus(id, PublicationItemStatus.AVAILABLE);
        if (items == null) return null;
        return items.stream().map(PublicationItemDto::mapToPublicationItemDtoSubclass).toList();
    }

    public PublicationDto newPublication(PublicationDto publicationDto) {
        Publication publication = new Publication(publicationDto);
        return new PublicationDto(publicationRepo.save(publication));
    }

    @Transactional
    public Boolean deletePublicationById(Long id) {
        if (id == null) return false;
        try {
            Integer deleteResult = publicationRepo.deletePublicationByPublicationId(id);
            return true;
        }
        //Need to send error someway
        catch (Exception e) {
            return false;
        }

    }
}
