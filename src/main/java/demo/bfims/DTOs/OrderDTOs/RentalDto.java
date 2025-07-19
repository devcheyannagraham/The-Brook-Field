package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Enums.RentalStatus;

import java.time.LocalDate;

public class RentalDto extends TransactionDto {
    private LocalDate startDate;
    private LocalDate dueDate;
    private RentalStatus status;

    public RentalDto() {
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
