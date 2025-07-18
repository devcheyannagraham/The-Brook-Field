package demo.bfims.DTOs.OrderDTOs;

public class PurchaseDto extends TransactionDto {
    private Double purchasePrice;

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public PurchaseDto() {
    }

    @Override
    public String toString() {
        return "PurchaseDto{" +
                "purchasePrice=" + purchasePrice +
                "} " + super.toString();
    }
}
