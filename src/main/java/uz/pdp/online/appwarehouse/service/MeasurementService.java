package uz.pdp.online.appwarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.online.appwarehouse.entity.Measurement;
import uz.pdp.online.appwarehouse.payload.Result;
import uz.pdp.online.appwarehouse.repository.MeasurementRepository;

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
}
