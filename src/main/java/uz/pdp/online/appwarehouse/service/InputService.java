package uz.pdp.online.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Currency;
import uz.pdp.online.appwarehouse.entity.Input;
import uz.pdp.online.appwarehouse.entity.Supplier;
import uz.pdp.online.appwarehouse.entity.Warehouse;
import uz.pdp.online.appwarehouse.payload.InputDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.CurrencyRepository;
import uz.pdp.online.appwarehouse.repository.InputRepository;
import uz.pdp.online.appwarehouse.repository.SupplierRepository;
import uz.pdp.online.appwarehouse.repository.WarehouseRepository;
import uz.pdp.online.appwarehouse.service.helper.Operation;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class InputService extends Operation {
    InputRepository inputRepository;
    WarehouseRepository warehouseRepository;
    CurrencyRepository currencyRepository;
    SupplierRepository supplierRepository;

    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository, CurrencyRepository currencyRepository, SupplierRepository supplierRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.currencyRepository = currencyRepository;
        this.supplierRepository = supplierRepository;
    }

    public Result addInput(InputDto inputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new Result("Warehouse is not found!", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new Result("Currency is not found!", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) return new Result("Supplier is not found!", false);

        long milliseconds = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(milliseconds);
        Input input = new Input();
        input.setDate(timestamp);
        input.setWarehouse(optionalWarehouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setCode(getCode(inputRepository.getMaxId()));
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new Result("Input is added!", true);


    }
}
