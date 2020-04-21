import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from "../../services/auth-service.service";
import { LoginModel } from "../../models/login-model";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginModel: LoginModel;
  constructor(private authService: AuthServiceService, private router: Router) {
    this.loginModel = new LoginModel();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.authService.callLogin(this.loginModel).subscribe(
      res => {
        if (res.status === 200) {
          sessionStorage.setItem('authToken', res.body.token); //Store those in browser cookies, so user can go back to this session as long as they don't clear cookies
          sessionStorage.setItem('userEmail', this.loginModel.email);
          this.router.navigate(['/profile']).then(() => {
            window.location.reload();
          }); //navigate to next page         
        } else {
          alert("Invalid Login");
        }
      }, error => {
        alert("Invalid Login");
      }
    );
  }
}