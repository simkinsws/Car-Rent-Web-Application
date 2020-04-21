import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/user.service";
import { User } from "../../models/user";
import { Observable } from "rxjs";
import { Router } from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: User;
  constructor(private userService: UserService, private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
    this.userService.getUser().subscribe(
      response => {
        this.user.firstName = response['dataList'][0].firstName;
        this.user.secondName = response['dataList'][0].secondName;
        this.user.email = localStorage.getItem("userEmail");
      }, error => {
        console.log(error);
        this.router.navigate(['/profile'])
        alert("Some error while getting User profile");
      })
  }

}
