import { Injectable } from '@angular/core';
import {BehaviorSubject, observable, Observable, throwError} from "rxjs";
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {LoginModel} from "../models/login-model";
import {Auth} from "../models/auth";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  public currentUser: Observable<User>;
  private currentUserSubject: BehaviorSubject<User>;
  constructor(private http:HttpClient) {
    // this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    // this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  register(user: User): Observable<any> {
    return this.http.post('http://localhost:8080/registration', user, { observe: 'response'});
}

  public login(loginModel: LoginModel): Observable<any> {
    return this.http.post<Auth>('http://localhost:8080/login', loginModel, {observe: 'response'});
  }

  public callLogin(loginModel: LoginModel) {

    // @ts-ignore
    this.login(loginModel).subscribe(this.setSession, this.handleError);
  }

  private setSession(authResult: Auth) {
    if (authResult.status === 'true') {
      localStorage.setItem('Authorization', authResult.token);
    } else {
      return throwError('invalid username/password');
    }
  }

  handleError(err) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${err.error.message}`;
    } else {
      // server-side error
      errorMessage = ` ${err.status}: ${err.message}`;
    }
    alert('invalid username/password');
    return throwError(errorMessage);
  }
}
