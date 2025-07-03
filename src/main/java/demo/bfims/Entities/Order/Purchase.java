package demo.bfims.Entities.Order;

import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Enums.ItemOrderType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class Purchase extends Transaction{
    private Double purchasePrice;

        public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Purchase() {
            this.setTransactionType(ItemOrderType.PURCHASE);
    }
}
