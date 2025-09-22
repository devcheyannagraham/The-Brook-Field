import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDto } from '../DTOs/User/UserDto';
import { firstValueFrom } from 'rxjs';
import { UserRole } from '../Enums/UserRole';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080/';;
  user = signal<UserDto>(null);
  storageAvailable = false;
  static SESSION_STORAGE: string = "sessionStorage"

  constructor(private http: HttpClient, public router: Router) {
    this.checkStorage();
    this.getUser();

  }

  newUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}newuser`, user, { responseType: 'text', withCredentials: true });
  }


  authenticateUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}authenticateuser`, user, { responseType: 'text', withCredentials: true });
  }

  reinstateUser(user: UserDto) {
    return firstValueFrom(this.http.post(`${this.baseUrl}reinstateuser`, user, { responseType: 'text', withCredentials: true }));

  }


  async userIsAdmin() {
    return await firstValueFrom(this.http.post(`${this.baseUrl}isadmin`, this.user(), { responseType: 'text', withCredentials: true }))
      .then(role => {
        if (role == UserRole.ADMIN) return true;
        else return false;
      })
  }

  setUser(user: UserDto) {
    user.password = null;
    this.user.set(user);
    if (this.storageAvailable) {
      // @ts-ignore 7015
      let storage = window[AuthService.SESSION_STORAGE];
      storage.setItem("user", JSON.stringify(user));
    }
  }


  logout() {
    this.user.set(null);
    if (this.storageAvailable) {
      // @ts-ignore 7015
      let storage = window[AuthService.SESSION_STORAGE];
      storage.removeItem("user");
    }
    this.http.post(`${this.baseUrl}logout`, null, { responseType: 'text', withCredentials: true })
      .subscribe(result => {
        alert(result)
        this.router.navigateByUrl("/login");
      });
  }

  checkStorage() {
    try {
      let key = "storageTest";
      // @ts-ignore 7015
      let storage = window[AuthService.SESSION_STORAGE];
      storage.setItem(key, "success");
      storage.removeItem(key);
      this.storageAvailable = true;
    } catch (e) { }

  }

  getUser() {
    if (this.storageAvailable) {
      // @ts-ignore 7015
      let storage = window[AuthService.SESSION_STORAGE];
      let user = JSON.parse(storage.getItem("user"));
      if (user != null) {
        this.reinstateUser(user)
          .then(id => {
            if (id == user.userId) {
              this.setUser(user)
            }
          });
      }
    }
  }

}
