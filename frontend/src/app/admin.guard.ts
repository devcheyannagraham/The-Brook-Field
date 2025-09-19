import { DestroyRef, inject } from '@angular/core';
import { CanActivateFn, RedirectCommand, Router } from '@angular/router';
import { AuthService } from './Services/auth.service';
import { UserRole } from './Enums/UserRole';

export const adminGuard: CanActivateFn = (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);

  if (authService.user() == null) {
    alert("Login Required");
    return new RedirectCommand(router.parseUrl("/login"), { skipLocationChange: true });
  }


  return authService.isAdmin()
    .then(role => {
      if (role == UserRole.ADMIN) return true;
      else {
        alert("Insufficient permissions!");
        return new RedirectCommand(router.parseUrl("/home"), { skipLocationChange: true });
      }
    })

};

