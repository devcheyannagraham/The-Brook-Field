import { Component, DestroyRef, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../Services/auth.service';
import { UserDto } from '../../../DTOs/User/UserDto';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRole } from '../../../Enums/UserRole';
import { BackComponent } from "../../back/back.component";

@Component({
  selector: 'login',
  imports: [ReactiveFormsModule, FormsModule, BackComponent],
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

      this.authService.authenticateUser(user)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe(result => {
          this.redirectUser(result, user);
        });
    }
  }

  register() {
    if (this.validated()) {
      let user = new UserDto(this.email.value, this.pwd.value);
      this.authService.newUser(user)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe(result => {
          this.redirectUser(result, user);
        });
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


  redirectUser(result: any, user: UserDto) {
    let userId = Number(result);
    if (Number.isNaN(userId)) {
      alert(result);
    }

    else {
      this.authService.user.set(user);
      this.authService.isAdmin()
        .then(role => {
          if (role == UserRole.ADMIN) {
            this.router.navigateByUrl("/admindashboard");
          } else {
            this.router.navigateByUrl("/home");
          }
        });
    }
  }
}
