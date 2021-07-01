import {AuthService} from '../_service/auth.service';

export function appInitializer(authService: AuthService): any {
  return () => new Promise(resolve => {
    console.log('app initializer');
    authService.setUser()
      .subscribe()
      .add(resolve);
  });
}
