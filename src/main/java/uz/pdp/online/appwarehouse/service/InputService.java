package uz.pdp.online.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Input> getInputPage(Integer page){
        int size=10;
        Pageable pageable= PageRequest.of(page,size);
        return inputRepository.findAll(pageable);
    }
    public Result getInputById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
//        if(optionalInput.isEmpty()) return new Result("Input is not found!",false);
//        return new Result("Input is successfully found!",true,optionalInput.get());
        return optionalInput.map(input -> new Result("Input is successfully found!",
                true, input)).orElseGet(() -> new Result("Input is not found!", false));
    }
    public Result editInput(Integer id,InputDto dto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(optionalInput.isEmpty()) return new Result("Input is not found by this id!",false);
        Input input = optionalInput.get();

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(dto.getWarehouseId());
        if(optionalWarehouse.isEmpty()) return new Result("Warehouse is not found!",false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(dto.getSupplierId());
        if(optionalSupplier.isEmpty()) return new Result("Supplier is not found!",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(dto.getCurrencyId());
        if(optionalCurrency.isEmpty()) return new Result("Currency is not found!",false);

        input.setWarehouse(optionalWarehouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setFactureNumber(dto.getFactureNumber());
        inputRepository.save(input);
        return new Result("Input is successfully edited!",true);
    }
    public Result deleteInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(optionalInput.isEmpty()) return new Result("Input is not found!",false);

        boolean existsInputProductInInput = inputRepository.existsInputProductInInput(id);

        if(existsInputProductInInput ) return new Result("You can't delete because of relationship entities!",false);

        inputRepository.deleteById(id);
        return new Result("Input is successfully deleted!",true);
    }



}
