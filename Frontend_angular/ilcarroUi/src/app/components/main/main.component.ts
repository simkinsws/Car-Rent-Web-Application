import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import { SearchFilters } from '../../models/search';
import { Car } from 'src/app/models/car';

// noinspection AngularMissingOrInvalidDeclarationInModule
@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  filters: SearchFilters;
  cars: Car;
  postData = {};
  constructor(private userService: UserService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.filters = new SearchFilters();
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.postData = {
      placeName: this.filters.placeName,
      startDateTime: new Date(this.filters.startDateTime).toISOString().substring(0, 10),
      endDateTime: new Date(this.filters.endDateTime).toISOString().substring(0, 10)
    }
    console.log(this.postData);
    this.userService.searchCar(this.postData).subscribe(response => {
      this.cars = response['dataList'];
      console.log(this.cars);
    }, error => {
      alert(error['message']);
    })
  }
}
