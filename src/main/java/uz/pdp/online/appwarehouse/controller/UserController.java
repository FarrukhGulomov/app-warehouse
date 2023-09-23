package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.User;
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
    public Result addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping
    public Page<User> getUsersByPage(@RequestParam Integer page) {
        return userService.getUsersByPage(page);
    }

    @GetMapping("/byUserId/{id}")
    public Result getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PutMapping("/edit/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody UserDto dto) {
        return userService.edit(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
