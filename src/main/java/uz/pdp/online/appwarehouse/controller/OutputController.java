package uz.pdp.online.appwarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Output;
import uz.pdp.online.appwarehouse.payload.OutputDto;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {
    OutputService outputService;

    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }
    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        return outputService.output(outputDto);
    }

    @GetMapping
    public Page<Output> getOutputsByPage(@RequestParam Integer page){
        return outputService.getOutputByPage(page);
    }

    @GetMapping("/byId/{id}")
    public Result getOutput(@PathVariable Integer id){
        return outputService.getOutput(id);
    }

    @PutMapping("/edit/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputDto dto){
        return outputService.editOutput(id,dto);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return outputService.delete(id);
    }
}
