package uz.pdp.online.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.appwarehouse.entity.Warehouse;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    WarehouseService warehouseService;


    public WarehouseController( WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouseService(warehouse);
    }


}
