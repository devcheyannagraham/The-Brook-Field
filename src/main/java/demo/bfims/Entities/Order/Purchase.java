package demo.bfims.Entities.Order;

import demo.bfims.Enums.TransactionType;
import jakarta.persistence.*;


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
            this.setTransactionType(TransactionType.PURCHASE);
    }
}
