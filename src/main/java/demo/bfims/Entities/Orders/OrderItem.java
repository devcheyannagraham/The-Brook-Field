package demo.bfims.Entities.Orders;

import jakarta.persistence.*;


/**
 * Associates the item with a customer for
 * the order records in rental and purchase tables
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class OrderItem extends Item {
    @ManyToOne(cascade = CascadeType.ALL)
    Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    Orders orders;

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
