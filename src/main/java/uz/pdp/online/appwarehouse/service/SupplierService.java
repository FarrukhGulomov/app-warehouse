package uz.pdp.online.appwarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.Supplier;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {

    SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Result addSupplier(Supplier supplier) {
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByPhoneNumber) return new Result("This phone number is already exist!", false);
        supplierRepository.save(supplier);
        return new Result("Supplier is added", true);
    }

    public Page<Supplier> getSupplierByPage(Integer number) {
        int size = 10;
        Pageable pageable = PageRequest.of(number, size);
        return supplierRepository.findAll(pageable);
    }

    public Result getSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.map(supplier ->
                        new Result("Success", true, supplier))
                .orElseGet(() -> new Result("Supplier is not found!", false));
    }

    public Result edit(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) return new Result("Supplier is not found!", false);
        boolean existsByPhoneNumber = supplierRepository
                .existsByPhoneNumber(supplier.getPhoneNumber());
        if(existsByPhoneNumber) return new Result("This phone number is already exist!",false);
        Supplier editingSupplier = optionalSupplier.get();
        editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        editingSupplier.setName(supplier.getName());
        Supplier savedSupplier = supplierRepository.save(editingSupplier);
        return new Result("Supplier is successfully edited!", true, savedSupplier);
    }

    public Result delete(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) return new Result("NOt found!", false);
        if (supplierRepository.existsSupplierInInput(id))
            return new Result("You can't delete this by relationship!", false);

        supplierRepository.deleteById(id);
        return new Result("Successfully deleted!", true);
    }
}
