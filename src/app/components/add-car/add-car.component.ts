import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {MatFormFieldControl} from '@angular/material';
import {map, startWith, take, takeUntil} from 'rxjs/operators';
import {SearchFilters} from '../../models/search';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.scss'],
  providers: [
    { provide: MatFormFieldControl, useExisting: AddCarComponent }
  ]
})
export class AddCarComponent implements OnInit {
  formGroup: FormGroup;
  formGroupNext: FormGroup;

  filters: SearchFilters;
  // make tags .
  makeValue = '';
  makeControl = new FormControl();
  makeOptions: any[]  = [];
  filteredMakeOptions: Observable<any[]>;

  // placeNames tags.
  placeValue = '';
  placeControl = new FormControl(null, Validators.required);
  placeOptions: any[] = [];
  filteredPlaceOptions: Observable<any[]>;

  // models tags.
  modelValue = '';
  modelControl = new FormControl(null, Validators.required);
  modelOptions: any[] = [];
  filteredModelOptions: Observable<any[]>;

  // years tags
  yearValue = '';
  yearControl = new FormControl(null, Validators.required);
  yearOptions: any[] = [];
  filteredYearOptions: Observable<any[]>;

  // State/Province/Region tags.
  states = ['Northern District', 'Southern District', 'Haifa District', 'Tel Aviv District'
  , 'Central District', 'Jerusalem District'];

  // Transmission tags
  transmissions = ['Manual', 'Automatic', 'Robotic', 'Semi-automatic', 'CVT'];
  constructor(private userService: UserService) {

  }



  addMake(make: any) {
    this.userService.addMakeTag(make).subscribe((data: any) => {
      console.log(data.body.dataList[0].make);
      this.getMakesTags();
    });
  }

  getMakesTags() {
    this.userService.getMakesTags().subscribe(response => {
      // @ts-ignore
      this.makeOptions = response.dataList;
      this.filteredMakeOptions = this.makeControl.valueChanges
        .pipe(startWith(''),
          map(value => typeof value === 'string' ? value : value.make),
          map(make => make ? this._filter(make) : this.makeOptions.slice()));
    });
  }

  addPlaceName(city: any) {
    this.userService.addPlaceName(city).subscribe((data: any) => {
      console.log(data.body.dataList[0].city);
      this.getPlaceNames();
    });
  }

  getPlaceNames() {
    this.userService.getPlaceNames().subscribe(response => {
      // @ts-ignore
      this.placeOptions = response.dataList;
      this.filteredPlaceOptions = this.placeControl.valueChanges
        .pipe(startWith(''),
          map(value => typeof value === 'string' ? value : value.city),
          map(city => city ? this._filterPlace(city) : this.placeOptions.slice()));
    });
  }

  addModel(model: any) {
    this.userService.addModel(model).subscribe((data: any) => {
      console.log(data.body.dataList[0].model);
      this.getModels();
    });
  }

  getModels() {
    this.userService.getModels().subscribe(response => {
      // @ts-ignore
      this.modelOptions = response.dataList;
      this.filteredModelOptions = this.modelControl.valueChanges
        .pipe(startWith(''),
          map(value => typeof value === 'string' ? value : value.model),
          map(model => model ? this._filterModel(model) : this.modelOptions.slice()));
    });
  }

  addYear(year: any) {
    this.userService.addYear(year).subscribe((data: any) => {
      console.log(data.body.dataList[0].year);
      this.getYear();
    });
  }

  getYear() {
    this.userService.getYear().subscribe(response => {
      // @ts-ignore
      this.yearOptions = response.dataList;
      this.filteredYearOptions = this.yearControl.valueChanges
        .pipe(startWith(''),
          map(value => typeof value === 'string' ? value : value.year),
          map(year => year ? this._filterYear(year) : this.yearOptions.slice()));
    });
  }
  ngOnInit() {

    this.getPlaceNames();
    this.getMakesTags();
    this.getModels();
    this.getYear();
    this.formGroup = new FormGroup({
      placeControl: this.placeControl
    });
    this.formGroupNext = new FormGroup({
      makeControl: this.makeControl,
      modelControl: this.modelControl,
      yearControl: this.yearControl
    });
  }

  displayFn(make?: any): string {
    this.makeValue = make.make;

    console.log(this.makeValue);
    return this.makeValue ? this.makeValue : undefined;
  }

  displayFnPlace(place?: any): string {
    this.placeValue = place.city;
    console.log(place.city);
    return place.city ? place.city : undefined;
  }

  displayFnModel(model?: any): string {
    this.modelValue = model.model;
    console.log(this.modelValue);
    return model.model ? model.model : undefined;
  }

  displayFnYear(year?: any): string {
    this.yearValue = year.year;
    console.log(this.yearValue);
    return year.year ? year.year : undefined;
  }

  private _filter(make: string): any[] {
    const filterValue = make.toLowerCase();
    return this.makeOptions.filter(option => option.make.toLowerCase().indexOf(filterValue) === 0);
  }

  private _filterPlace(city: string): any[] {
    const filterValue = city.toLowerCase();
    return this.placeOptions.filter(place => place.city.toLowerCase().indexOf(filterValue) === 0);
  }

  private _filterModel(model: string): any[] {
    const filterValue = model.toLowerCase();
    return this.modelOptions.filter(mod => mod.model.toLowerCase().indexOf(filterValue) === 0);
  }

  private _filterYear(year: string): any[] {
    const filterValue = year.toLowerCase();
    return this.yearOptions.filter(y => y.year.toLowerCase().indexOf(filterValue) === 0);
  }
}





