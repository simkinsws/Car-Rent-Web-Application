import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from "../../services/auth-service.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLoggedIn;
  authToken

  constructor(private authService: AuthServiceService, private router: Router) { }

  ngOnInit() {
    this.authToken = localStorage.getItem("authToken");
    if (this.authToken != null) {
      this.isLoggedIn = true;
    }
  }

  logout() {
    this.isLoggedIn = false;
    console.log(this.isLoggedIn);
    localStorage.removeItem("authToken");
    localStorage.removeItem("userEmail");
    this.router.navigate(['/login']).then(() => {
      window.location.reload();
    });
  }

}
