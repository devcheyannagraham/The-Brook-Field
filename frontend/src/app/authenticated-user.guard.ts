import { inject, } from '@angular/core';
import { CanActivateFn, RedirectCommand, Router } from '@angular/router';
import { AuthService } from './Services/auth.service';
import {ToasterService} from './Services/toaster.service';

export const authenticatedUserGuard: CanActivateFn = (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);
  let toaster = inject(ToasterService);


  return authService.getUser()
    .then(() => {
      if (authService.user()) return true;

      toaster.message.set({class: "error", message: "Not Signed In"});
      return false;
      // return new RedirectCommand(router.parseUrl(""),
      //   { skipLocationChange: true });
    })
};
