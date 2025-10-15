import {Component, DestroyRef, inject} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../../Services/auth.service';
import {UserDto} from '../../../DTOs/User/UserDto';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'login',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = new FormControl(null, [Validators.email, Validators.required]);
  pwd = new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(100)]);
  pwdConf = new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(100)]);
  actionType: "LOGIN" | "REGISTER";

  constructor(private authService: AuthService, public route: ActivatedRoute, public router: Router) {
  }

  ngOnInit() {
    let actionType = this.route.snapshot.data["actionType"];
    this.actionType = actionType == "login" ? "LOGIN" : "REGISTER";
  }


  submitForm() {
    this.email.markAsTouched();
    this.pwd.markAsTouched();
    if (this.actionType == "LOGIN") {
      if (this.email.valid && this.pwd.valid) this.login();
    } else {
      this.pwdConf.markAsTouched();
      if (this.email.valid && this.pwd.valid && this.pwdConf.valid && (this.pwd.value == this.pwdConf.value)) {
        this.register();
      }
    }
  }

  login() {
    let user = new UserDto(this.email.value, this.pwd.value);
    this.authService.login(user);
  }

  register() {
    let user = new UserDto(this.email.value, this.pwd.value);
    this.authService.register(user);
  }
}
