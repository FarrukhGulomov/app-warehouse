package uz.pdp.online.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Currency;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @PostMapping
    public Result addCurrency(@RequestBody Currency currency){
       return currencyService.addCurrency(currency);
    }
    @GetMapping
    public List<Currency> getALlCurrency(){
        return currencyService.getAllCurrency();
    }
    @GetMapping("/getCurrencyById/{id}")
    public Result getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }

    @PutMapping("/editCurrency/{id}")
    public Result editCurrency(@PathVariable Integer id,@RequestBody Currency currency){
        return currencyService.editCurrency(id,currency);
    }
    @DeleteMapping("/deleteCurrency/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        return currencyService.deleteCurrency(id);
    }
}
