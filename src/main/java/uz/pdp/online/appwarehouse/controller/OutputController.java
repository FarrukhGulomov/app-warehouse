package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
