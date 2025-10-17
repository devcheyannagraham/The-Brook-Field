package demo.bfims.Entities.Order;

import demo.bfims.DTOs.OrderDTOs.RentalDto;
import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.TransactionType;
import demo.bfims.Enums.RentalStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Rental extends Transaction { //is publication
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @PrePersist
    private void prePersist() {
        //Calculate due date when the record is persisted
        this.setDueDate(LocalDate.now().plusWeeks(3));
    }


    @PostLoad
    private void updateRentalStatus() {
        if (this.rentalStatus != RentalStatus.RETURNED && LocalDate.now().isAfter(this.dueDate)) {
            this.setRentalStatus(RentalStatus.OVERDUE);
        }
    }

    public Rental() {
        this.setTransactionType(TransactionType.RENTAL);
        this.setRentalStatus(RentalStatus.RENTED);
    }

    public Rental(TransactionDto transDto) {
        super(transDto);
        if (transDto instanceof RentalDto rentalDto) {
            this.startDate = rentalDto.getStartDate();
            this.dueDate = rentalDto.getDueDate();
            this.rentalStatus = rentalDto.getStatus();
        }
    }

//    For Bootstrap
    public Rental(Item item, BigDecimal price) {
        this.setTransactionType(TransactionType.RENTAL);
        this.setTransactionDate(LocalDate.now());
        this.setRentalStatus(RentalStatus.RENTED);
        this.setTransactionPrice(price);
        this.setItem(item);
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus status) {
        this.rentalStatus = status;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", rentalStatus=" + rentalStatus +
                "} " + super.toString();
    }

}
