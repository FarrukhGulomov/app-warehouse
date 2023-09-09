package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.InputProduct;
import uz.pdp.online.appwarehouse.payload.InputProductDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    InputProductService inputProductService;

    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto dto){
       return inputProductService.addInputProduct(dto);
    }
    @GetMapping
    public List<InputProduct> getAllInputProducts(){
       return inputProductService.getAllInputProducts();
    }
    @GetMapping("/page")
    public Page<InputProduct> getInputProductByPage(@RequestParam int page){
        return inputProductService.getInputProductByPage(page);
    }

    @GetMapping("/byId/{id}")
    public InputProduct getInputProductById(@PathVariable Integer id){
        return inputProductService.getInputProductById(id);
    }
}
