import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './Services/auth.service';



export const authInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);

  if(authService.user()){
    const authReq = req.clone({
      setHeaders: {
        "user-uuid": authService.user().uuid.toString()
  }
    });
    return next(authReq);
  }
  return next(req);
};
