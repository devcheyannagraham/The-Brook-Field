package demo.bfims.Entities.Orders;

import demo.bfims.Enums.RentalStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class Rental extends OrderItem { //is publication
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    private Double rentalRate;

    //Calculate due date when the record is persisted
    @PrePersist
    private void calculateRentalDueDate(){
        this.setDueDate(LocalDate.now().minusWeeks(2));
    }

    // update rental status when retrieving record
    // Not sure if it will be persisted
    @PostLoad
    private void updateRentalStatus(){
        if(this.status != RentalStatus.RETURNED && LocalDate.now().isAfter(this.dueDate)){
            this.setStatus(RentalStatus.OVERDUE);
        }
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

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", status=" + status +
                "} " + super.toString();
    }

}
