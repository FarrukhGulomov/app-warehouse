package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.online.appwarehouse.entity.Measurement;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Result addMeasurementService(Measurement measurement){
        Boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if(existsByName) return new Result("Bunday o'lchov birligi mavjud",false);
        measurementRepository.save(measurement);
        return new Result("Muvaffzqiyatli sqlandi",true);

    }
    public List<Measurement> getAllMeasurement(){
        return measurementRepository.findAll();
    }
    public Result getMeasurementById(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.map(measurement ->
                new Result("Measurement is successfully found!", true, measurement))
                .orElseGet(() -> new Result("Measurement is not found!", false));

    }
    public Result editMeasurement(Integer id,Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if(optionalMeasurement.isEmpty()) return new Result("Measurement is not found!",false);
        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        return new Result("Measurement is successfully edited!",true);
    }
    public Result deleteMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if(optionalMeasurement.isEmpty()) return new Result("Measurement is not found!",false);
        boolean existsMeasurementInProduct = measurementRepository.existsMeasurementInProduct(id);
        if(existsMeasurementInProduct) return new Result(" Error ,This measurement is belongs to another entity by relationship!",false);
        measurementRepository.deleteById(id);
        return new Result("Measurement is successfully deleted",true);
    }



}
