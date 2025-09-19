import { Injectable, signal } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UserDto } from '../DTOs/User/UserDto';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080/';;
  user = signal<UserDto>(null);

  constructor(private http: HttpClient) {
  }

  newUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}newuser`, user, {responseType:'text', withCredentials: true});
  }


  authenticateUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}authenticateuser`, user, {responseType:'text', withCredentials: true});
  }


  isAdmin(user: UserDto) {
    return this.http.post(`${this.baseUrl}isadmin`, user, {responseType:'text', withCredentials: true});
  }


  logout(user: UserDto) {
    return this.http.get(`${this.baseUrl}logout`);
  }
}
