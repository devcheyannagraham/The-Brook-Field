import { inject, } from '@angular/core';
import { CanActivateFn, RedirectCommand, Router } from '@angular/router';
import { AuthService } from './Services/auth.service';

export const authenticatedUserGuard: CanActivateFn = (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);


  return authService.getUser()
    .then(() => {
      if (authService.user()) return true;
      return new RedirectCommand(router.parseUrl("/login"),
        { skipLocationChange: true });
    })
};
