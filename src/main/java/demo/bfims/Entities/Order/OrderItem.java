//package demo.bfims.Entities.Order;
//
//
//import demo.bfims.Entities.Inventory.Item;
//import demo.bfims.Enums.ItemOrderType;
//import jakarta.persistence.*;
//
//@Entity
//public class OrderItem {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long orderItemId;
////    @Enumerated(EnumType.STRING)
////    private ItemOrderType itemOrderType;
//    // Item already exists. The Item converter finds the existing item or
//    // creates a new subclass since item is abstract
//    // no need for cascades
//    @ManyToOne()
//    private Item item;
////    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
////    @JoinColumn(name="order_id")
////    private Order order;
//
//    public Item getItem() {
//        return item;
//    }
//
//    public void setItem(Item item) {
//        this.item = item;
//    }
//
//
//    public ItemOrderType getItemOrderType() {
//        return itemOrderType;
//    }
//
//    public void setItemOrderType(ItemOrderType itemOrderType) {
//        this.itemOrderType = itemOrderType;
//    }
//
//    public Long getOrderItemId() {
//        return orderItemId;
//    }
//
//    public void setOrderItemId(Long orderItemId) {
//        this.orderItemId = orderItemId;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderItem{" +
//                "orderItemId=" + orderItemId +
//                ", itemOrderType=" + itemOrderType +
//                ", item=" + item +
//                ", order=" + order +
//                '}';
//    }
//}
