import {AfterViewInit, Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {Car} from '../../models/car';

@Component({
  selector: 'app-most-popular-cars',
  templateUrl: './most-popular-cars.component.html',
  styleUrls: ['./most-popular-cars.component.scss']
})
export class MostPopularCarsComponent implements OnInit {
  topCars: Car[];
  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.getMostPopular();
  }

  getMostPopular() {
    this.userService.getMostPopularCars().subscribe(data => {
      // @ts-ignore
      this.topCars = data.body.dataList;
      console.log(this.topCars);
    });
  }

}
