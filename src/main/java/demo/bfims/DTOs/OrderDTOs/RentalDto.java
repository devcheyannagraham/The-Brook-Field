package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Entities.Order.Rental;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.RentalStatus;
import demo.bfims.Enums.TransactionType;

import java.time.LocalDate;

public class RentalDto extends TransactionDto {
    private LocalDate startDate;
    private LocalDate dueDate;
    private RentalStatus status;

    public RentalDto() {
        this.setTransactionType(TransactionType.RENTAL);
    }

    public RentalDto(Transaction trans) {
        super(trans);
        if (trans instanceof Rental rental) {
            this.startDate = rental.getStartDate();
            this.dueDate = rental.getDueDate();
            this.status = rental.getRentalStatus();
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

    @Override
    public String toString() {
        return "RentalDto{" +
                "startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", status=" + status +
                "} " + super.toString();
    }
}
