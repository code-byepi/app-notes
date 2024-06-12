// PublicGuard
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanMatch,
  Route,
  Router,
  RouterStateSnapshot,
  UrlSegment,
  UrlTree
} from '@angular/router';
import { map, Observable, tap } from 'rxjs';
import { AuthService } from "../services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class PublicGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return this.authService.checkAuthentication().pipe(
      map(isAuthenticated => {
        if (isAuthenticated) {
          return this.router.createUrlTree(['/']);
        } else {
          return true;
        }
      })
    );
  }

}
