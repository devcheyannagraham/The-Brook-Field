import { DestroyRef, inject } from '@angular/core';
import { CanActivateFn, RedirectCommand, Router } from '@angular/router';
import { AuthService } from './Services/auth.service';
import { UserRole } from './Enums/UserRole';

export const adminGuard: CanActivateFn = async (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);

  if (authService.user() == null) {
    alert("Login Required");
    return new RedirectCommand(router.parseUrl("/login"), { skipLocationChange: true });
  }

  let result = await authService.userIsAdmin();
  if (result) return true;

  alert("Insufficient permissions!");
  return new RedirectCommand(router.parseUrl("/home"), { skipLocationChange: true });

};

