package uz.pdp.online.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Currency;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.CurrencyRepository;

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
}
