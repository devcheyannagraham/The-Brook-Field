import { Component, DestroyRef, inject } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../Services/auth.service';
import { UserDto } from '../../../DTOs/User/UserDto';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'login',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = new FormControl();
  pwd = new FormControl();
  destroyRef = inject(DestroyRef)
  emailError = false;
  pwdError = false;
  actionType = '';

  constructor(private authService: AuthService, public route: ActivatedRoute, public router: Router) { }

  ngOnInit() {
    this.actionType = this.route.snapshot.data["actionType"];
  }


  submitForm() {
    if (this.actionType == "login") {
      this.login();
    }
    else this.register();
  }

  login() {
    if (this.validated()) {
      let user = new UserDto(this.email.value, this.pwd.value);
      this.authService.authenticateUser(user);
    }
  }

  register() {
    if (this.validated()) {
      let user = new UserDto(this.email.value, this.pwd.value);
      this.authService.newUser(user);
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
    return !this.emailError && !this.pwdError;
  }

}
