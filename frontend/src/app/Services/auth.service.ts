import { DestroyRef, inject, Injectable, signal } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UserDto } from '../DTOs/User/UserDto';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { firstValueFrom } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080/';;
  user = signal<UserDto>(null);
  destroyRef = inject(DestroyRef);

  constructor(private http: HttpClient) {
  }

  newUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}newuser`, user, {responseType:'text', withCredentials: true});
  }


  authenticateUser(user: UserDto) {
    return this.http.post(`${this.baseUrl}authenticateuser`, user, {responseType:'text', withCredentials: true});
  }


  async isAdmin() {
    return await firstValueFrom(this.http.post(`${this.baseUrl}isadmin`, this.user(), {responseType:'text', withCredentials: true}))
    .then(value => value);
  }


  logout(user: UserDto) {
    return this.http.get(`${this.baseUrl}logout`);
  }
}
