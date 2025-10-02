import { Component } from '@angular/core';
import { AuthService } from '../../../Services/auth.service';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserDto } from '../../../DTOs/User/UserDto';
import { Router } from '@angular/router';
import { UserRole } from '../../../Enums/UserRole';

@Component({
  selector: 'user-management',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './user-management.component.html',
  styleUrl: './user-management.component.css'
})
export class UserManagementComponent {
  email = new FormControl();
  emailError = false;
  pwd = new FormControl();
  pwdError = false;
  pwdConf = new FormControl();
  pwdConfError = false;

  adminUsers: any = null;

  constructor(public authService: AuthService, public router: Router) {

  }

  ngOnInit() {
    this.getAdminUsers();
  }

  getAdminUsers() {
    this.authService.getAdminUsers()
      .then(users => {
        if (users) this.adminUsers = users;
      });
  }

  deleteUser(userId: string) {
    this.authService.deleteUser(userId)
      .then(() => {
        this.getAdminUsers();
      });
  }


  submitForm() {
    if (this.validated()) {
      let user = new UserDto(this.email.value, this.pwd.value);
      this.authService.newAdminUser(user)
        .then(() => {
          this.email.reset();
          this.pwd.reset();
          this.pwdConf.reset();
          this.getAdminUsers();
        });
    }
  }


  validated() {
    let emailValue = this.email.value;
    let pwdValue = this.pwd.value;
    let pwdConfValue = this.pwdConf.value;

    if (emailValue == null || emailValue.trim() == '') {
      this.emailError = true;
    }
    else this.emailError = false;

    if (pwdValue == null || pwdValue.trim() == '') {
      this.pwdError = true;
    }
    else this.pwdError = false;

    if (pwdConfValue == null || pwdConfValue.trim() == '' || pwdValue != pwdConfValue) {
      this.pwdConfError = true;
    }
    else this.pwdConfError = false;

    return !this.emailError && !this.pwdError && !this.pwdConfError;
  }

}
