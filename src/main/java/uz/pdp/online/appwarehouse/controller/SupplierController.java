package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Supplier;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @GetMapping
    public Page<Supplier> getSuppliersByPage(@RequestParam Integer number){
        return supplierService.getSupplierByPage(number);
    }
    @GetMapping("/getSupplier/{id}")
    public Result getSupplier(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }
    @PutMapping("/edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Supplier supplier){
        return supplierService.edit(id, supplier);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return supplierService.delete(id);
    }
}
