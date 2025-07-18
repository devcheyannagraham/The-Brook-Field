package demo.bfims.Config;

import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.TransactionType;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements Converter<TransactionDto, Transaction> {

    @Override
    public Transaction convert(MappingContext<TransactionDto, Transaction> mappingContext) {
        TransactionDto dto = mappingContext.getSource();
        ModelMapper mapper = new ModelMapper();

        if (dto.getTransactionType().equals(TransactionType.RENTAL)) {
            return mapper.map(dto, Rental.class);
        } else if (dto.getTransactionType().equals(TransactionType.PURCHASE)) {
            return mapper.map(dto, Purchase.class);
        }
        return null;
    }
}
