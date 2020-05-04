package com.car.rentservice.service.impl;

import com.car.rentservice.dto.ResponseModel;
import com.car.rentservice.modal.tags.*;
import com.car.rentservice.repositories.tags.*;
import com.car.rentservice.service.FiltersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FiltersServiceIMpl implements FiltersService {

    @Autowired
    private MakeTagsRepository carTagsRepository;

    @Autowired
    private PlaceNameTagsRepository placeNameTagsRepository;

    @Autowired
    private ModelTagRepository modelTagRepository;

    @Autowired
    private YearTagRepository yearTagRepository;

    @Autowired
    private WDTagRepository wdTagRepository;
//    @Autowired
//    private TransmissionTagRepository  transmissionTagRepository;
    @Autowired
    private HorsePowerTagRepository horsePowerTagRepository;
    @Autowired
    private FuelTagRepository fuelTagRepository;
    @Autowired
    private FuelConsumptionTagRepository fuelConsumptionTagRepository;
    @Autowired
    private EngineTagRepository engineTagRepository;

    @Override
    public ResponseModel getMakesTags() {
        List<MakeTags> makeTags = carTagsRepository.findAll();
        return generateResponse(HttpStatus.OK.toString(), "Makes found :", new ArrayList<>(makeTags));
    }


    @Override
    public ResponseModel addMakeTag(String make) {
        MakeTags makeTags = new MakeTags();
        try {
            makeTags.setMake(make);
            carTagsRepository.save(makeTags);

            return generateResponse(HttpStatus.OK.toString(), "Make Tag is added.",
                    new ArrayList<>(Arrays.asList(makeTags)));

        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(), "Make Already Exists", null);
        }
    }

    @Override
    public ResponseModel getPlaceNames() {
        List<PlaceNameTag> placeNameTags = placeNameTagsRepository.findAll();
        return generateResponse(HttpStatus.OK.toString(), "PlaceNames found", new ArrayList<>(placeNameTags));
    }

    @Override
    public ResponseModel addPlaceName(String city) {
        PlaceNameTag placeNameTag = new PlaceNameTag();
        try {
            placeNameTag.setCity(city);
            placeNameTagsRepository.save(placeNameTag);
            return generateResponse(HttpStatus.OK.toString(),
                    "Place Name Added", new ArrayList<>(Arrays.asList(placeNameTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(), "City Already Exists", null);
        }
    }

    @Override
    public ResponseModel getModels() {
        List<ModelTag> modelTags = modelTagRepository.findAll();
        return generateResponse(HttpStatus.OK.toString(), "Models Found", new ArrayList<>(modelTags));
    }

    @Override
    public ResponseModel addModel(String model) {
        ModelTag modelTag = new ModelTag();
        try {
            modelTag.setModel(model);
            modelTagRepository.save(modelTag);
            return generateResponse(HttpStatus.OK.toString(), "Model Added",
                    new ArrayList<>(Arrays.asList(modelTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(), "Model Already Exists", null);
        }
    }

    @Override
    public ResponseModel getYears() {
        return generateResponse(HttpStatus.OK.toString(),"Years found",
                new ArrayList<>(yearTagRepository.findAll()));
    }

    @Override
    public ResponseModel addYear(String year) {
        YearTag yearTag = new YearTag();
        try {
            yearTag.setYear(year);
            yearTagRepository.save(yearTag);
            return generateResponse(HttpStatus.OK.toString(), "Year Added",
                    new ArrayList<>(Arrays.asList(yearTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(), "Year already exists", null);
        }
    }

    @Override
    public ResponseModel getEngines() {
        return generateResponse(HttpStatus.OK.toString(),"Engines found",
                new ArrayList<>(engineTagRepository.findAll()));
    }

    @Override
    public ResponseModel addEngine(String engine) {
        EngineTag engineTag = new EngineTag();
        try {
            engineTag.setEngine(engine);
            engineTagRepository.save(engineTag);
            return generateResponse(HttpStatus.OK.toString(),"Engine Added",
                    new ArrayList<>(Arrays.asList(engineTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(), "Engine exists", null);
        }
    }

    @Override
    public ResponseModel getFuels() {
        return generateResponse(HttpStatus.OK.toString(),"Fuels found",
                new ArrayList<>(fuelTagRepository.findAll()));
    }

    @Override
    public ResponseModel addFuel(String fuel) {
        FuelTag fuelTag = new FuelTag();
        try {
            fuelTag.setFuel(fuel);
            fuelTagRepository.save(fuelTag);
            return generateResponse(HttpStatus.OK.toString(),"Fuel Added",
                    new ArrayList<>(Arrays.asList(fuelTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(), "fuel exists", null);
        }
    }

    @Override
    public ResponseModel getFuelConsumption() {
        return generateResponse(HttpStatus.OK.toString(),"Fuel Consumptions found",
                new ArrayList<>(fuelConsumptionTagRepository.findAll()));
    }

    @Override
    public ResponseModel addFuelConsumption(String fuelCons) {
        FuelConsumptionTag fuelConsumptionTag = new FuelConsumptionTag();
        try {
            fuelConsumptionTag.setFuelConsumption(fuelCons);
            fuelConsumptionTagRepository.save(fuelConsumptionTag);
            return generateResponse(HttpStatus.OK.toString(),"FuelConsm added",
                    new ArrayList<>(Arrays.asList(fuelConsumptionTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(),"FuelConsm already exists", null);
        }
    }

    @Override
    public ResponseModel getHorsePowers() {
        return generateResponse(HttpStatus.OK.toString(),"Horse Powers found",
                new ArrayList<>(horsePowerTagRepository.findAll()));
    }

    @Override
    public ResponseModel addHorsePower(String horsePower) {
        HorsePowerTag horsePowerTag = new HorsePowerTag();
        try {
            horsePowerTag.setHorsepower(horsePower);
            horsePowerTagRepository.save(horsePowerTag);
            return generateResponse(HttpStatus.OK.toString(),"Horse Power added",
                    new ArrayList<>(Arrays.asList(horsePowerTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(),"Horse Power already exists", null);
        }
    }

    @Override
    public ResponseModel getWD() {
        return generateResponse(HttpStatus.OK.toString(),"WD's found",
                new ArrayList<>(wdTagRepository.findAll()));
    }

    @Override
    public ResponseModel addWD(String wd) {
        WdTag wdTag = new WdTag();
        try {
            wdTag.setWd(wd);
            wdTagRepository.save(wdTag);
            return generateResponse(HttpStatus.OK.toString(),"WD added",
                    new ArrayList<>(Arrays.asList(wdTag)));
        } catch (Exception e) {
            return generateResponse(HttpStatus.CONFLICT.toString(),"Wd already exists", null);
        }
    }

//    @Override
//    public ResponseModel getTransmission() {
//        return generateResponse(HttpStatus.OK.toString(),"Transmissions found",
//                new ArrayList<>(transmissionTagRepository.findAll()));
//    }
//
//    @Override
//    public ResponseModel addTransmission(String transmission) {
//        TransmissionTag transmissionTag = new TransmissionTag();
//        try {
//            transmissionTag.setTransmission(transmission);
//            transmissionTagRepository.save(transmissionTag);
//            return generateResponse(HttpStatus.OK.toString(),"transmission added",
//                    new ArrayList<>(Arrays.asList(transmissionTag)));
//        } catch (Exception e) {
//            return generateResponse(HttpStatus.CONFLICT.toString(),"transmission already exists",null);
//        }
//    }

    private ResponseModel generateResponse(String status, String message, List<Object> data) {
        return new ResponseModel(status, message, data);
    }
}
