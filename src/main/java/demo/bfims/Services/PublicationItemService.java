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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private PublicationRepo publicationRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Attatch existing publication to book if exists or create new publication
    @Transactional
    public PublicationItemDto newPublicationItem(PublicationItem publicationItem) {
        if (publicationItem.getPublication().getPublicationId() != null) {
            Publication publication = publicationRepo.findById(publicationItem.getPublication().getPublicationId()).orElse(null);
            publicationItem.setPublication(publication);
        }

        //New Publication Group or existing publication group not found
        //Creates new publication group
        return publicationItemDtoHelper(itemRepo.save(publicationItem));
    }

    public PublicationItemDto getPublicationItem(Long id) {
        Item item = itemRepo.findById(id).orElse(null);
        if (item != null) {
            //configure if statements for publication type.
            return publicationItemDtoHelper(item);
        }
        return null;
    }

    public List<PublicationItemDto> getPublicationItems() {
        List<Item> items = itemRepo.findItemsByItemType(ItemType.PUBLICATION).orElse(null);
        if (items != null) {
            return items.stream().map(this::publicationItemDtoHelper).toList();
        }
        return null;
    }

    public PublicationItemDto publicationItemDtoHelper(Item item) {
        if (item.getClass().getSimpleName().equals(Book.class.getSimpleName())) {
            return modelMapper.map(item, BookDto.class);
        } else if (item.getClass().getSimpleName().equals(LiteraryPiece.class.getSimpleName())) {
            return modelMapper.map(item, LiteraryPieceDto.class);
        } else if (item.getClass().getSimpleName().equals(Journal.class.getSimpleName())) {
            return modelMapper.map(item, JournalDto.class);
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
