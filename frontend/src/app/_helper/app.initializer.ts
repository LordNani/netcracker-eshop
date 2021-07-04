import {AuthService} from '../_service/auth/auth.service';

export function appInitializer(authService: AuthService): any {
  return () => new Promise(resolve => {
    authService.setUser()
      .subscribe()
      .add(resolve);
  });
}
