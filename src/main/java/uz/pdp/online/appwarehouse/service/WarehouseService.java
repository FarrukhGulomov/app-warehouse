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
        Warehouse warehouse1=new Warehouse();
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        System.out.println("boolean: "+existsByName);
        if(existsByName) return new Result("This warehouse is already exist!",false);
        System.out.println("obyekt: "+warehouse.getName());
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new Result("Warehouse is added!",true);
    }
}
