package demo.bfims.Entities.Order;

import demo.bfims.Enums.ItemOrderType;
import demo.bfims.Interfaces.Purchaseable;
import jakarta.persistence.*;

import java.io.Serializable;


@Entity
public class Purchase extends Transaction {
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
