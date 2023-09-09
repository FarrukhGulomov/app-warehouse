package uz.pdp.online.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
