package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Enums.AccessoryType;
import demo.bfims.Repo.AccessoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryService {

    @Autowired
    AccessoryRepo accessoryRepo;

    @Autowired
    ModelMapper modelMapper;

    public AccessoryDto newAccessory() {
        System.out.println("newAccessory in service");
        Accessory accessory = new Accessory();
        accessory.setAccessoryType(AccessoryType.BOOKMARK);
        accessory.setAccessoryName("flower/blue");
        accessory.setPrice(1.29);


        return modelMapper.map(accessoryRepo.save(accessory), AccessoryDto.class);
    }
}
