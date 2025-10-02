import { Component, DestroyRef, inject } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../Services/auth.service';
import { UserDto } from '../../../DTOs/User/UserDto';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'login',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = new FormControl();
  pwd = new FormControl();
  pwdConf = new FormControl();
  destroyRef = inject(DestroyRef)
  emailError = false;
  pwdError = false;
  pwdConfError = false;
  actionType: "LOGIN" | "REGISTER";

  constructor(private authService: AuthService, public route: ActivatedRoute, public router: Router) { }

  ngOnInit() {
    let actionType = this.route.snapshot.data["actionType"];
    this.actionType = actionType == "login"? "LOGIN": "REGISTER";
  }


  submitForm() {
    if (this.actionType == "LOGIN") {
      this.login();
    }
    else this.register();
  }

  login() {
    if (this.validated()) {
      let user = new UserDto(this.email.value, this.pwd.value);
      this.authService.login(user);
    }
  }

  register() {
    if (this.validated()) {
      let user = new UserDto(this.email.value, this.pwd.value);
      this.authService.register(user);
    }
  }

  validated() {
    let emailValue = this.email.value;
    let pwdValue = this.pwd.value;
    if (emailValue == null || emailValue.trim() == '') {
      this.emailError = true;
    }
    else this.emailError = false;

    if (pwdValue == null || pwdValue.trim() == '') {
      this.pwdError = true;
    }
    else this.pwdError = false;

    if(this.actionType == "REGISTER"){
      if(this.pwdConf.value == null || this.pwdConf.value.trim() == '' || this.pwdConf.value != this.pwd.value)
        this.pwdConfError = true;
      else this.pwdConfError = false;
    }
    return !this.emailError && !this.pwdError && !this.pwdConfError;
  }

}
