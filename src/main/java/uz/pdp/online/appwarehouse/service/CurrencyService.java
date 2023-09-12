package uz.pdp.online.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Currency;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    CurrencyRepository currencyRepository;


    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
    public Result addCurrency(Currency currency){
        boolean existsCurrency = currencyRepository.existsByName(currency.getName());

        if(existsCurrency) return new Result("This currency is already exist!",false);
        currencyRepository.save(currency);
        return new Result("Currency is added!",true);
    }
    public List<Currency> getAllCurrency(){
        return currencyRepository.findAll();
    }
    public Result getCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
/*        if(optionalCurrency.isEmpty()) return new Result("Currency is not found by this id",false);
        return new Result("Successfully",true,optionalCurrency.get()); */
        return optionalCurrency.map(currency -> new Result("Successfully", true, currency)).orElseGet(() -> new Result("Currency is not found by this id", false));
    }
    public Result editCurrency(Integer id,Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(optionalCurrency.isEmpty()) return new Result("Currency is not found by this id!",false);
        Currency editingCurrency = optionalCurrency.get();
        editingCurrency.setName(currency.getName());
        currencyRepository.save(editingCurrency);
        return new Result("Currency is successfully edited!",true);
    }

    public Result deleteCurrency(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(optionalCurrency.isEmpty()) return new Result("Currency is not found by this id!",false);
        boolean existsCurrencyInInput = currencyRepository.existsCurrencyInInput(id);
        boolean existsCurrencyInOutput = currencyRepository.existsCurrencyInOutput(id);
        if(existsCurrencyInInput || existsCurrencyInOutput) return new Result("Delete not allowed by relationship with input or output entity",false);
        currencyRepository.deleteById(id);
        return new Result("Currency is deleted by this id!",true);

    }

}
