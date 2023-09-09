package uz.pdp.online.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Warehouse;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.WarehouseRepository;

@Service
public class WarehouseService {
    WarehouseRepository warehouseRepository;


    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Result addWarehouseService(Warehouse warehouse){
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if(existsByName) return new Result("This warehouse is already exist!",false);
        System.out.println(warehouse);
        warehouseRepository.save(warehouse);
        return new Result("Warehouse is added!",true);
    }
}
