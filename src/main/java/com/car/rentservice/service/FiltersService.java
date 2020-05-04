package com.car.rentservice.service;

import com.car.rentservice.dto.ResponseModel;

public interface FiltersService {
    ResponseModel getMakesTags();
    ResponseModel addMakeTag(String make);

    ResponseModel getPlaceNames();
    ResponseModel addPlaceName(String city);

    ResponseModel getModels();
    ResponseModel addModel(String model);

    ResponseModel getYears();
    ResponseModel addYear(String year);

    ResponseModel getEngines();
    ResponseModel addEngine(String engine);

    ResponseModel getFuels();
    ResponseModel addFuel(String fuel);

    ResponseModel getFuelConsumption();
    ResponseModel addFuelConsumption(String fuelCons);

    ResponseModel getHorsePowers();
    ResponseModel addHorsePower(String horsePower);

    ResponseModel getWD();
    ResponseModel addWD(String wd);

//    ResponseModel getTransmission();
//    ResponseModel addTransmission(String transmission);
}
