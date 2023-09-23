package uz.pdp.online.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Warehouse;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    WarehouseService warehouseService;


    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouseService(warehouse);
    }

    @GetMapping
    public Page<Warehouse> getWarehousesByPage(@RequestParam Integer page) {
        return warehouseService.getWarehousesByPage(page);
    }

    @GetMapping("/byWarehouseId/{id}")
    public Result getWarehouse(@PathVariable Integer id) {
        return warehouseService.getWarehouse(id);
    }

    @PutMapping("/edit/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.edit(id, warehouse);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return warehouseService.delete(id);
    }

}
