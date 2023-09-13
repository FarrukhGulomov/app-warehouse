package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.OutputProduct;
import uz.pdp.online.appwarehouse.payload.OutputProductDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    OutputProductService outputProductService;

    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @PostMapping
    public Result output(@RequestBody OutputProductDto dto){
        return outputProductService.output(dto);
    }

    @GetMapping("/byPage")
    public Page<OutputProduct> getAllOutputByPage(@RequestParam Integer page){
        return outputProductService.getOutputProductByPage(page);
    }

    @GetMapping("/byId/{id}")
    public Result getOutputProduct(@PathVariable Integer id){
        return outputProductService.getOutputProduct(id);
    }
    @PutMapping("/editOutputProduct/{id}")
    public Result editOutputProduct(@PathVariable Integer id,@RequestBody OutputProductDto dto){
        return outputProductService.editOutputProduct(id,dto);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return outputProductService.delete(id);
    }
}
