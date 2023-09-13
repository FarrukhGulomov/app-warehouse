package uz.pdp.online.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Client;
import uz.pdp.online.appwarehouse.entity.Currency;
import uz.pdp.online.appwarehouse.entity.Output;
import uz.pdp.online.appwarehouse.entity.Warehouse;
import uz.pdp.online.appwarehouse.payload.OutputDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.ClientRepository;
import uz.pdp.online.appwarehouse.repository.CurrencyRepository;
import uz.pdp.online.appwarehouse.repository.OutputRepository;
import uz.pdp.online.appwarehouse.repository.WarehouseRepository;
import uz.pdp.online.appwarehouse.service.helper.Operation;

import java.util.Optional;

@Service
public class OutputService extends Operation {
    OutputRepository outputRepository;
    WarehouseRepository warehouseRepository;
    CurrencyRepository currencyRepository;
    ClientRepository clientRepository;

    @Autowired
    public OutputService(OutputRepository outputRepository, WarehouseRepository warehouseRepository, CurrencyRepository currencyRepository, ClientRepository clientRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.currencyRepository = currencyRepository;
        this.clientRepository = clientRepository;
    }

    public Result output(OutputDto outputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(optionalWarehouse.isEmpty()) return new Result("This warehouse is not found!",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if(optionalCurrency.isEmpty()) return new Result("This currency is not found!",false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if(optionalClient.isEmpty()) return new Result("This client is not found!",false);

        Output output=new Output();
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setCode(getCode(outputRepository.getMaxId()));
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        System.out.println(warehouseRepository.findById(outputDto.getWarehouseId()));
        return new Result("Output is successfully added!",true);

    }
    public Page<Output> getOutputByPage(Integer page){
        int size=10;
        Pageable pageable= PageRequest.of(page,size);
        return outputRepository.findAll(pageable);
    }
    public Result getOutput(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(optionalOutput.isEmpty()) return new Result("Output is not found!",false);
        return new Result("Output is successfully found!",true,optionalOutput.get());
    }
    public Result editOutput(Integer id,OutputDto dto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(optionalOutput.isEmpty()) return new Result("Output is not found!",false);
        Output output = optionalOutput.get();

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(dto.getWarehouseId());
        Optional<Client> optionalClient = clientRepository.findById(dto.getClientId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(dto.getCurrencyId());
        if(optionalWarehouse.isEmpty() || optionalClient.isEmpty() || optionalCurrency.isEmpty() )
            return new Result("Entity is not found",false);

        output.setFactureNumber(dto.getFactureNumber());
        output.setCurrency(optionalCurrency.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        outputRepository.save(output);
        return new Result("Output is successfully edited!",true);
    }
    public Result delete(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(optionalOutput.isEmpty()) return new Result("Not found!",false);
        boolean existsOutputInOutputProduct = outputRepository.existsOutputInOutputProduct(id);
        if(existsOutputInOutputProduct) return new Result("You can't delete this entity just now bacause of relationship",false);

        outputRepository.deleteById(id);
        return new Result("Successfully deleted",true);
    }
}
