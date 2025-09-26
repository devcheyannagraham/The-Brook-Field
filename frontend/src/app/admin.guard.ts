import { inject } from '@angular/core';
import { CanActivateFn, Router, RedirectCommand } from '@angular/router';
import { AuthService } from './Services/auth.service';

export const adminGuard: CanActivateFn = (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);
  console.log("ADMIN GUARD")


  return authService.getUser()
    .then(() => {
      if (authService.user()) {
        console.log("USER", authService.user())
        return authService.getUserRole()
          .then(isAdmin => {
            if (isAdmin) return true;
            else return false;
          });
      }
      return new RedirectCommand(router.parseUrl("/login"),
        { skipLocationChange: true });
    });
}

