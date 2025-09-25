import { DestroyRef, Injectable, signal, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { UserDto } from '../DTOs/User/UserDto';
import { catchError, firstValueFrom } from 'rxjs';
import { UserRole } from '../Enums/UserRole';
import { Router } from '@angular/router';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  destroyRef = inject(DestroyRef);
  baseUrl: string = 'http://localhost:8080/';
  user = signal<UserDto>(null);
  storageAvailable = false;
  static SESSION_STORAGE: string = "sessionStorage";
  static USER_UUID: string = "userUUID";


  constructor(private http: HttpClient, public router: Router) {
    this.checkStorage();
    this.getUser();
  }

  newUser(user: UserDto) {
    this.http.post(`${this.baseUrl}newuser`, user, { responseType: 'text', withCredentials: true })
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: uuid => {
          this.setUser(uuid, user);
        },
        error: error => alert(error.error)
      });
  }


  authenticateUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}authenticateuser`, user, { responseType: 'text', withCredentials: true })
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: uuid => {
          this.setUser(uuid, user);
        },
        error: error => alert(error.error)
      });
  }

  reinstateUser(userUid: string) {
    return firstValueFrom(this.http.post(`${this.baseUrl}reinstateuser`, userUid, { responseType: 'text', withCredentials: true }))
      .then(email => {
        if (email) {
          this.user.set(new UserDto(email, null));
          this.navigateUser();
        }
      });
  }


  async userIsAdmin() {
    if (this.user().userId != null) {

      return await firstValueFrom(this.http.post(`${this.baseUrl}isadmin`, this.user().userId, { responseType: 'text', withCredentials: true }))
        .then(role => {
          if (role == UserRole.ADMIN) return true;
          else return false;
        });
    }
    return false;
  }

  setUser(uuid: string, user: UserDto) {
    user.password = null;
    user.userId = uuid;
    this.user.set(user);
    if (this.storageAvailable) {
      // @ts-ignore 7015
      let storage = window[AuthService.SESSION_STORAGE];
      storage.setItem(AuthService.USER_UUID, uuid);
    }

    this.navigateUser();
  }

  navigateUser() {
    this.userIsAdmin()
      .then(isAdmin => {
        if (isAdmin) this.router.navigateByUrl("/admindashboard");
        else this.router.navigateByUrl("/home");
      });
  }


  logout() {
    this.user.set(null);
    if (this.storageAvailable) {
      // @ts-ignore 7015
      let storage = window[AuthService.SESSION_STORAGE];
      storage.removeItem(AuthService.USER_UUID);
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
      let userUid = storage.getItem(AuthService.USER_UUID);
      if (userUid != null) {
        this.reinstateUser(userUid);
      }
    }
  }

}
