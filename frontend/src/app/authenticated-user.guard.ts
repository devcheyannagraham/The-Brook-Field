import { inject } from '@angular/core';
import { CanActivateFn, RedirectCommand, Router } from '@angular/router';
import { AuthService } from './Services/auth.service';

export const authenticatedUserGuard: CanActivateFn = (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);
  
  if (authService.user() == null) {
    alert("You must be logged in to view recent orders!");
    return new RedirectCommand(router.parseUrl("/login"),
      { skipLocationChange: true });
  }
  return true;
};
