package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Supplier;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.SupplierRepository;

@Service
public class SupplierService {

    SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    public Result addSupplier(Supplier supplier){
        boolean existsByPhoneNumberAndName = supplierRepository.existsByPhoneNumberAndName(supplier.getPhoneNumber(),supplier.getName());
        if(existsByPhoneNumberAndName) return new Result("This phone number is already exist!",false);
        supplierRepository.save(supplier);
        return new Result("Supplier is added",true);
    }
}
