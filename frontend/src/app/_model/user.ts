import {Role} from './role';

export class User{
  constructor() {
  }
  id: string;
  firstName: string;
  lastName: string;
  userLogin: string;
  userPassword: string;
  role: Role;
  status: string;
}
