package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.payload.UserDto;
import uz.pdp.online.appwarehouse.service.UserService;

@RestController
@RequestMapping("/user")

public class UserController {
UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Result addUser(UserDto userDto){
       return userService.addUser(userDto);
    }

}
