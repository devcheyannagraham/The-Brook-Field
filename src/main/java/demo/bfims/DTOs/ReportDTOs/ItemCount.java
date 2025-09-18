package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Publication.Publication;

public class ItemCount{
    Long id;
    String title;
    Integer count;

    public ItemCount() {
    }

    public ItemCount(Publication pub, Integer count) {
        this.id = pub.getPublicationId();
        this.title = pub.getTitle();
        this.count = count;
    }

    public ItemCount(Accessory acc,Integer count){
        this.id = acc.getAccessoryId();
        this.title = acc.getAccessoryName();
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ItemCount{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }
}




