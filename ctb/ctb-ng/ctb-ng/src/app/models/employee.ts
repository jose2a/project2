import { Role } from './role';

export class Employee {
     employeeId = 0;

     username = '';
     password = '';
     firstName = '';
     lastName = '';
     email = '';
     phoneNumber = '';
     driverLicense = '';
     driver = false;

     numberOfFlags: number;
     active: boolean;
     block: boolean;

     roles: Role[];
}
