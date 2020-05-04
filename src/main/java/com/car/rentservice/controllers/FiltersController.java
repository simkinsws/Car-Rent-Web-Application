package com.car.rentservice.controllers;

import com.car.rentservice.dto.ResponseModel;
import com.car.rentservice.service.FiltersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class FiltersController {

    @Autowired
    private FiltersService filtersService;

    @PostMapping("/filters/makes/add")
    public ResponseModel addMakes(@RequestBody String make) {
        return filtersService.addMakeTag(make);
    }

    @GetMapping("/filters/makes")
    public ResponseModel getMakes() {
        return filtersService.getMakesTags();
    }

    @PostMapping("/filters/cities/add")
    public ResponseModel addPlace(@RequestBody String city) {
        return filtersService.addPlaceName(city);
    }

    @GetMapping("/filters/cities")
    public ResponseModel getPlaces() {
        return filtersService.getPlaceNames();
    }

    @PostMapping("/filters/models/add")
    public ResponseModel addModel(@RequestBody String model) {
        return filtersService.addModel(model);
    }

    @GetMapping("/filters/models")
    public ResponseModel getModels() {
        return filtersService.getModels();
    }

    @PostMapping("/filters/years/add")
    public ResponseModel addYears(@RequestBody String year) {
        return filtersService.addYear(year);
    }

    @GetMapping("/filters/years")
    public ResponseModel getYears(){
        return filtersService.getYears();
    }

    @PostMapping("/filters/wd/add")
    public ResponseModel addWd(@RequestBody String wd) {
        return filtersService.addWD(wd);
    }

    @GetMapping("/filters/wd")
    public ResponseModel getWd() {
        return filtersService.getWD();
    }

//    @PostMapping("/filters/transmission/add")
//    public ResponseModel addTransmission(@RequestBody String transmission) {
//        return filtersService.addTransmission(transmission);
//    }
//
//    @GetMapping("/filters/transmission")
//    public ResponseModel getTransmission() {
//        return filtersService.getTransmission();
//    }

    @PostMapping("/filters/horsepower/add")
    public ResponseModel addHorsePower(@RequestBody String horsepower) {
        return filtersService.addHorsePower(horsepower);
    }

    @GetMapping("/filters/horsepower")
    public ResponseModel getHorsePower() {
        return  filtersService.getHorsePowers();
    }

    @PostMapping("/filters/fuel/add")
    public ResponseModel addFuel(@RequestBody String fuel) {
        return filtersService.addFuel(fuel);
    }

    @GetMapping("/filters/fuel")
    public ResponseModel getFuel() {
        return filtersService.getFuels();
    }

    @PostMapping("/filters/fuelcons")
    public ResponseModel addFuelCons(@RequestBody String fuelCons) {
        return filtersService.addFuelConsumption(fuelCons);
    }

    @GetMapping("/filters/fuelcons")
    public ResponseModel getFuelCons() {
        return filtersService.getFuelConsumption();
    }

    @PostMapping("/filters/engine/add")
    public ResponseModel addEngine(@RequestBody String engine) {
        return filtersService.addEngine(engine);
    }

    @GetMapping("/filters/engine")
    public ResponseModel getEngine() {
        return filtersService.getEngines();
    }
}
