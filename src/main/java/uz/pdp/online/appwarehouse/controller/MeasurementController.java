package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.appwarehouse.entity.Measurement;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.MeasurementService;


@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

}
