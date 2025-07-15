package demo.bfims.DTOs.ReportDTOs;

public class PopularItem<T> {
    T popularItem;
    Integer totalUnitsSold;
    Double totalProfit;

    public T getPopularItem() {
        return popularItem;
    }

    public void setPopularItem(T popularItem) {
        this.popularItem = popularItem;
    }

    public Integer getTotalUnitsSold() {
        return totalUnitsSold;
    }

    public void setTotalUnitsSold(Integer totalUnitsSold) {
        this.totalUnitsSold = totalUnitsSold;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    @Override
    public String toString() {
        return "PopularItem{" +
                "popularItem=" + popularItem +
                ", totalUnitsSold=" + totalUnitsSold +
                ", totalProfit=" + totalProfit +
                '}';
    }
}
