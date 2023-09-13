package uz.pdp.online.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.online.appwarehouse.entity.Measurement;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.service.MeasurementService;

import java.util.List;


@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }
    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        return measurementService.addMeasurementService(measurement);
    }

    @GetMapping
    public List<Measurement> getAllMeasurement(){
        return measurementService.getAllMeasurement();
    }
    @GetMapping("/getMeasurementById/{id}")
    public Result getMeasurement(@PathVariable Integer id){
        return measurementService.getMeasurementById(id);
    }
    @PutMapping("/editMeasurement/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Measurement measurement){
        return measurementService.editMeasurement(id,measurement);
    }
    @DeleteMapping("/deleteMeasurement/{id}")
    public Result delete(@PathVariable Integer id){
        return measurementService.deleteMeasurement(id);
    }
}
