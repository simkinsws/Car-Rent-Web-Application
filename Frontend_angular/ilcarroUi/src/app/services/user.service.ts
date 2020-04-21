import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  authToken;
  constructor(private http: HttpClient) {
    this.authToken = sessionStorage.getItem('authToken');
  }

  getUser() {
    return this.http.get(environment.apiTarget + '/user', {
      headers: new HttpHeaders(
        {
          'Authorization': this.authToken,
          'Accept': '*/*',
        }),
    });
  }
}
