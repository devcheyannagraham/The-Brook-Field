import { DestroyRef, Injectable, signal, inject, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDto } from '../DTOs/User/UserDto';
import { UserRole } from '../Enums/UserRole';
import { Router } from '@angular/router';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { firstValueFrom } from 'rxjs';
import { ToasterService } from './toaster.service';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  destroyRef = inject(DestroyRef);
  baseUrl: string = 'https://localhost:8080/';
  user = signal<UserDto>(null);
  storageAvailable = false;
  static SESSION_STORAGE: string = "sessionStorage";
  static USER_UUID: string = "userUUID";
  isAdmin = signal(false);

  constructor(private http: HttpClient, public router: Router, public toaster: ToasterService) {
    this.checkStorage();
  }

  ngOnInit() {
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
        error: error => this.toaster.message.set({ class: "error", message: error.error })
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
        error: error => this.toaster.message.set({ class: "error", message: error.error })
      });
  }

  async reinstateUser(userUid: string) {
    if (this.user() != null) return;
    return firstValueFrom(this.http.post(`${this.baseUrl}reinstateuser`, userUid, { responseType: 'text', withCredentials: true }))
      .then(email => email)
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));
  }


  getUserRole() {
    if (this.user() == null) return null;
    return firstValueFrom(this.http.post(`${this.baseUrl}isadmin`, this.user() && this.user().userId, { responseType: 'text', withCredentials: true }))
      .then(role => {
        if (role == UserRole.ADMIN) {
          this.isAdmin.set(true);
          return true;
        }
        else this.isAdmin.set(false);
        return false;
      })
      .catch(error => this.toaster.message.set({ class: "error", message: error.error }));

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
        this.toaster.message.set({ class: "info", message: result });
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
    console.log("GETUSER", this.user())
    // skip if user already exists
    if (this.user() != null) return;
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
