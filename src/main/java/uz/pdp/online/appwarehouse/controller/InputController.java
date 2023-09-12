package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Input;
import uz.pdp.online.appwarehouse.payload.InputDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {
    InputService inputService;

    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);

    }
    @GetMapping
    public Page<Input> getInputByPage(@RequestParam Integer page){
       return inputService.getInputPage(page);
    }
    @GetMapping("/getInputById/{id}")
    public Result getInput(@PathVariable Integer id){
       return inputService.getInputById(id);
    }
    @PutMapping("/editInput/{id}")
    public Result editInput(@PathVariable Integer id,@RequestBody InputDto dto){
        return inputService.editInput(id,dto);
    }
    @DeleteMapping("/deleteInput/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }
}
