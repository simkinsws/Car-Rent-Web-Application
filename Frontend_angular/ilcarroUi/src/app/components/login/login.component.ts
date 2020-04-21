import { Component, OnInit } from '@angular/core';
import {AuthServiceService} from "../../services/auth-service.service";
import {LoginModel} from "../../models/login-model";
import {Router} from "@angular/router";

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
    this.authService.callLogin(this.loginModel);
    this.router.navigate(['/profile']);
  }

}
