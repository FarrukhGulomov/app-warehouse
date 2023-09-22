package uz.pdp.online.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.appwarehouse.entity.User;
import uz.pdp.online.appwarehouse.entity.Warehouse;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.payload.UserDto;
import uz.pdp.online.appwarehouse.repository.UserRepository;
import uz.pdp.online.appwarehouse.repository.WarehouseRepository;
import uz.pdp.online.appwarehouse.service.helper.Operation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService extends Operation {

    UserRepository userRepository;
    WarehouseRepository warehouseRepository;

    @Autowired
    public UserService(UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Result addUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setCode(getCode(userRepository.getMaxId()));
        Set<Warehouse> set = new HashSet<>();
        for (Integer id : userDto.getWarehousesId()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
            if(optionalWarehouse.isEmpty()) return new Result("Warehouse is not found!",false);
            set.add(optionalWarehouse.get());
        }
        user.setWarehouses(set);
        userRepository.save(user);
        return new Result("User is added!",true);
    }
    public Page<User> getUsersByPage(Integer page){
        int size=10;
        Pageable pageable= PageRequest.of(page,size);
        return userRepository.findAll(pageable);
    }
    public Result getUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) return new Result("User not found!",false);
        return new Result("Success",true,optionalUser.get());
    }
    public Result edit(Integer id,UserDto dto){

    }
}

