package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Result addSupplier(@RequestBody Supplier supplier){
       return supplierService.addSupplier(supplier);
    }
}
