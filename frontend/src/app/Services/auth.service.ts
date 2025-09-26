import { DestroyRef, Injectable, signal, inject, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDto } from '../DTOs/User/UserDto';
import { UserRole } from '../Enums/UserRole';
import { Router } from '@angular/router';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { firstValueFrom } from 'rxjs';


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
  }
  
  ngOnInit(){
    (async () => await this.getUser())();
  }

  register(user: UserDto) {
    this.http.post(`${this.baseUrl}newuser`, user, { responseType: 'text', withCredentials: true })
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: uuid => {
          this.setUser(uuid, user);
          this.navigateUser();
        },
        error: error => alert("REGISTER:" + error.error)
      });
  }


  login(user: UserDto) {
    this.http.post(`${this.baseUrl}authenticateuser`, user, { responseType: 'text', withCredentials: true })
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: uuid => {
          this.setUser(uuid, user);
          this.navigateUser();
        },
        error: error => alert("LOGIN:ERROR:" + error.error)
      });
  }

  async reinstateUser(userUid: string) {
    return firstValueFrom(this.http.post(`${this.baseUrl}reinstateuser`, userUid, { responseType: 'text', withCredentials: true }))
      .then(email => email)
      .catch(error => alert("REINSTATE:" + error.error));
  }


  getUserRole() {
    return firstValueFrom(this.http.post(`${this.baseUrl}isadmin`, this.user() && this.user().userId || null, { responseType: 'text', withCredentials: true }))
      .then(role => role == UserRole.ADMIN)
      .catch(error => alert("GET USER ROLE:" + error.error))

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
  }

  navigateUser() {
    this.getUserRole().then(isAdmin => {
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
        alert("LOGOUT:" + result)
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

  async getUser() {
    if (this.storageAvailable) {
      // @ts-ignore 7015
      let storage = window[AuthService.SESSION_STORAGE];
      let userUid = storage.getItem(AuthService.USER_UUID);
      if (userUid != null) {
        let email = await this.reinstateUser(userUid);
        if (email) this.setUser(userUid, new UserDto(email, null))
      }
    }
  }

}
