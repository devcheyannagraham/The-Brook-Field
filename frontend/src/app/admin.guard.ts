import { inject } from '@angular/core';
import { CanActivateFn, RedirectCommand, Router } from '@angular/router';
import { AuthService } from './Services/auth.service';

export const adminGuard: CanActivateFn = async (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);

  if (authService.user() == null) {
    alert("Login Required");
    return new RedirectCommand(router.parseUrl("/login"), { skipLocationChange: true });
  }
  if(authService.isAdmin) return true;


  alert("Insufficient permissions!");
  return new RedirectCommand(router.parseUrl("/home"), { skipLocationChange: true });

};

