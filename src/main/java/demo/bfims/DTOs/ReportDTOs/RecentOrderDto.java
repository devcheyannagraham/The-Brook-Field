package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.*;

import java.time.LocalDate;
import java.util.List;

public class RecentOrderDto {
    private Long orderId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private double orderTotal;
    private List<RecentOrderTransaction> recentOrderTransactions;

    public RecentOrderDto() {
    }

    public RecentOrderDto(Order order) {
        this.orderId = order.getId();
        this.customerFirstName = order.getCustomer().getFirstName();
        this.customerLastName = order.getCustomer().getLastName();
        this.customerEmail = order.getCustomer().getEmail();
        this.orderTotal = order.getOrderTotal();
        this.recentOrderTransactions = order
                .getTransactions()
                .stream()
                .map(RecentOrderTransaction::new).toList();
    }

    public static class RecentOrderTransaction {

        private Long transactionId;
        private TransactionType transactionType;
        private Double transactionPrice;
        private LocalDate transactionDate;
        private Long itemId;
        private PublicationItemFormat publicationItemFormat;
        private Long publicationId;
        private Genre genre;
        private String publicationTitle;
        private PublicationItemType publicationItemType;
        private AccessoryType accessoryType;
        private String accessoryName;
        private Long AccessoryId;

        public RecentOrderTransaction(Transaction transaction) {
            this.transactionId = transaction.getTransactionId();
            this.transactionType = transaction.getTransactionType();
            this.transactionPrice = transaction.getTransactionPrice();
            this.transactionDate = transaction.getTransactionDate();
            if (transaction.getItem().getItemType().equals(ItemType.PUBLICATION_ITEM)) {
                PublicationItem pubItem = (PublicationItem) transaction.getItem();
                this.itemId = pubItem.getItemId();
                this.publicationId = pubItem.getPublication().getPublicationId();
                this.publicationItemFormat = pubItem.getFormat();
                this.publicationItemType = pubItem.getPublicationItemType();
                this.genre = pubItem.getPublication().getGenre();
                this.publicationTitle = pubItem.getPublication().getTitle();
            } else if (transaction.getItem().getItemType().equals(ItemType.ACCESSORY_ITEM)) {
                AccessoryItem accItem = (AccessoryItem) transaction.getItem();
                this.itemId = accItem.getItemId();
                this.accessoryType = accItem.getAccessory().getAccessoryType();
                this.accessoryName = accItem.getAccessory().getAccessoryName();
                this.AccessoryId = accItem.getAccessory().getAccessoryId();
            }
        }

        public Long getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(Long transactionId) {
            this.transactionId = transactionId;
        }

        public TransactionType getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
        }

        public Double getTransactionPrice() {
            return transactionPrice;
        }

        public void setTransactionPrice(Double transactionPrice) {
            this.transactionPrice = transactionPrice;
        }

        public LocalDate getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(LocalDate transactionDate) {
            this.transactionDate = transactionDate;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public PublicationItemFormat getPublicationItemFormat() {
            return publicationItemFormat;
        }

        public void setPublicationItemFormat(PublicationItemFormat publicationItemFormat) {
            this.publicationItemFormat = publicationItemFormat;
        }

        public Long getPublicationId() {
            return publicationId;
        }

        public void setPublicationId(Long publicationId) {
            this.publicationId = publicationId;
        }

        public Genre getGenre() {
            return genre;
        }

        public void setGenre(Genre genre) {
            this.genre = genre;
        }

        public String getPublicationTitle() {
            return publicationTitle;
        }

        public void setPublicationTitle(String publicationTitle) {
            this.publicationTitle = publicationTitle;
        }

        public PublicationItemType getPublicationItemType() {
            return publicationItemType;
        }

        public void setPublicationItemType(PublicationItemType publicationItemType) {
            this.publicationItemType = publicationItemType;
        }

        public AccessoryType getAccessoryType() {
            return accessoryType;
        }

        public void setAccessoryType(AccessoryType accessoryType) {
            this.accessoryType = accessoryType;
        }

        public String getAccessoryName() {
            return accessoryName;
        }

        public void setAccessoryName(String accessoryName) {
            this.accessoryName = accessoryName;
        }

        public Long getAccessoryId() {
            return AccessoryId;
        }

        public void setAccessoryId(Long accessoryId) {
            AccessoryId = accessoryId;
        }

        @Override
        public String toString() {
            return "RecentOrderTransaction{" +
                    "transactionId=" + transactionId +
                    ", transactionType=" + transactionType +
                    ", transactionPrice=" + transactionPrice +
                    ", transactionDate=" + transactionDate +
                    ", itemId=" + itemId +
                    ", publicationItemFormat=" + publicationItemFormat +
                    ", publicationId=" + publicationId +
                    ", genre=" + genre +
                    ", publicationTitle='" + publicationTitle + '\'' +
                    ", publicationItemType=" + publicationItemType +
                    ", accessoryType=" + accessoryType +
                    ", accessoryName='" + accessoryName + '\'' +
                    ", AccessoryId=" + AccessoryId +
                    '}';
        }
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<RecentOrderTransaction> getRecentOrderTransactions() {
        return recentOrderTransactions;
    }

    public void setRecentOrderTransactions(List<RecentOrderTransaction> recentOrderTransactions) {
        this.recentOrderTransactions = recentOrderTransactions;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "RecentOrderDto{" +
                "orderId=" + orderId +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", orderTotal=" + orderTotal +
                ", recentOrderTransactions=" + recentOrderTransactions +
                '}';
    }
}
