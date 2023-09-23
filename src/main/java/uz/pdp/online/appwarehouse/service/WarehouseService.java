package uz.pdp.online.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Warehouse;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.WarehouseRepository;

import java.util.Optional;

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
    public Page<Warehouse> getWarehousesByPage(Integer page){
        int size=10;
        Pageable pageable= PageRequest.of(page,size);
        return warehouseRepository.findAll(pageable);
    }
    public Result getWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.map(warehouse ->
                new Result("Success", true, warehouse))
                .orElseGet(() -> new Result("Warehouse is not found!", false));
    }

    public Result edit(Integer id,Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isEmpty()) return new Result("Warehouse is not found!",false);
        Warehouse editingWarehouse = optionalWarehouse.get();
        editingWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editingWarehouse);
        return new Result("Warehouse is edited!",true);
    }
    public Result delete(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isEmpty()) return new Result("Warehouse is not found!",false);

        boolean existsWarehouseInInput = warehouseRepository.existsWarehouseInInput(id);
        boolean existsWarehouseInOutput = warehouseRepository.existsWarehouseInOutput(id);
        boolean existsWarehouseInUsersWarehouses = warehouseRepository.existsWarehouseInUsersWarehouses(id);
        if(existsWarehouseInInput || existsWarehouseInOutput || existsWarehouseInUsersWarehouses)
            return new Result("You can't delete this warehouse because of relationship to another entity",false);

        warehouseRepository.deleteById(id);
        return new Result("Warehouse is deleted",true);
    }
}
