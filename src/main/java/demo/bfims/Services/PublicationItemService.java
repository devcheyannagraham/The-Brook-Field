package demo.bfims.Services;

import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Inventory.Publication;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicationItemService {
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    PublicationRepo publicationRepo;


    // Attatch existing publication to book if exists or create new publication
    @Transactional
    public void newPublicationItem(PublicationItem publicationItem){
        if(publicationItem.getPublication().getPublicationId() != null){
            Publication publication = publicationRepo.findById(publicationItem.getPublication().getPublicationId()).orElse(null);
            publicationItem.setPublication(publication);
        }

        //New Publication Group or existing publication group not found
        itemRepo.save(publicationItem);
    }

    public void updateItem(Item item){
        itemRepo.save(item);
    }

    public List<Item> getAll(){
        return itemRepo.findAll();
    }

}
