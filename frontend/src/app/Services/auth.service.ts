import { DestroyRef, inject, Injectable, signal } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UserDto } from '../DTOs/User/UserDto';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { firstValueFrom } from 'rxjs';
import { UserRole } from '../Enums/UserRole';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080/';;
  user = signal<UserDto>(null);
  destroyRef = inject(DestroyRef);

  constructor(private http: HttpClient, public router: Router) {
  }

  newUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}newuser`, user, { responseType: 'text', withCredentials: true });
  }


  authenticateUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}authenticateuser`, user, { responseType: 'text', withCredentials: true });
  }


  async userIsAdmin() {
    return await firstValueFrom(this.http.post(`${this.baseUrl}isadmin`, this.user(), { responseType: 'text', withCredentials: true }))
      .then(role => {
        if (role == UserRole.ADMIN) return true;
        else return false;
      })
  }


  logout() {
    this.user.set(null);
    this.http.post(`${this.baseUrl}logout`, null, { responseType: 'text', withCredentials: true })
      .subscribe(result => {
        alert(result)
        this.router.navigateByUrl("/login");
      });
  }

}
