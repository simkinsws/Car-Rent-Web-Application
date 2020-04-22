import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {
  user: User = new User();
  errorMessage: string;
  constructor(private userService: UserService, private router: Router, private authService: AuthServiceService) { }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem("editUser"));
  }
  updateUser() {
    this.userService.updateUser(this.user).subscribe(response => {
      alert(response['message']);
    }, error => {
      alert(error['message']);
    });
  }
  deleteUser() {
    this.userService.deleteUser().subscribe(response => {
      alert(response['message']);
      this.authService.logOut();
      this.router.navigate(['/']);
    }, error => {
      alert(error['message']);
    });
  }

}
