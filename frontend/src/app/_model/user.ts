import {Role} from './role';

export class User{
  constructor() {
  }
  id: number;
  isEdit?: boolean;
  userName: string;
  userSurname: string;
  userLogin: string;
  userPassword: string;
  userNumber: string;
  userAddress: string;
  userRole: Role;
  userStatus: string;
  recaptchaResponse: string;
}
