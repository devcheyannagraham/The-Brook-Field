import {inject} from '@angular/core';
import {CanActivateFn, Router, RedirectCommand} from '@angular/router';
import {AuthService} from './Services/auth.service';
import {ToasterService} from './Services/toaster.service';

export const adminGuard: CanActivateFn = (route, state) => {

  let authService = inject(AuthService);
  let router = inject(Router);
  let toaster = inject(ToasterService)


  return authService.getUser()
  // @ts-ignore
    .then(() => {
      if (authService.user()) {
        return authService.getUserRole()
          .then(isAdmin => {
            if (isAdmin) return true;
            else {
              toaster.message.set({class: "error", message: "Unauthorized"});
              return false;
            }
          });
      } else {
        toaster.message.set({class: "error", message: "Not Signed In"});
        return false;
        // return new RedirectCommand(router.parseUrl(""),
        //   {skipLocationChange: true});
      }
    });
}

