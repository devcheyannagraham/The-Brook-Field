package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Publication.*;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationItemRepo;
import demo.bfims.Repo.PublicationRepo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicationService {
    private final ItemRepo itemRepo;
    private final PublicationRepo publicationRepo;
    private final EntityManager entityManager;
    private final PublicationItemRepo publicationItemRepo;

    public PublicationService(ItemRepo itemRepo, PublicationRepo publicationRepo, EntityManager entityManager, PublicationItemRepo publicationItemRepo) {
        this.itemRepo = itemRepo;
        this.publicationRepo = publicationRepo;
        this.entityManager = entityManager;
        this.publicationItemRepo = publicationItemRepo;
    }

    @Transactional
    public PublicationItemDto newPublicationItem(PublicationItemDto publicationItemDto) {
        if (publicationItemDto == null) return null;
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
            if (publicationItem.getPublicationItemStatus() == null)
                publicationItem.setPublicationItemStatus(PublicationItemStatus.AVAILABLE);
            return new PublicationItemDto(itemRepo.save(publicationItem));
        }
        return null;
    }

    public PublicationItemDto getPublicationItemById(Long id) {
        if (id == null) return null;
        Item item = itemRepo.findById(id).orElse(null);
        if (item != null) {
            return PublicationItemDto.mapToPublicationItemDtoSubclass((PublicationItem) item);
        }
        return null;
    }

    @Transactional
    public Boolean deletePublicationItem(Long id) {
        if (id == null) return null;
        try {
            itemRepo.deleteItemByItemId(id);
            return true;
        } catch (Exception e) {
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
        if (publicationDto == null) return null;
        Publication publication = new Publication(publicationDto);
        return new PublicationDto(publicationRepo.save(publication));
    }

    @Transactional
    public Boolean deletePublicationById(Long id) {
        if (id == null) return null;
        try {
            publicationRepo.deletePublicationByPublicationId(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
