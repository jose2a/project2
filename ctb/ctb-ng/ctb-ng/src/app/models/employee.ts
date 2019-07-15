import { Role } from './role';

export class Employee {
     employeeId: number;

     username: string;
     password: string;
     firstName: string;
     lastName: string;
     email: string;
     phoneNumber: string;
     driverLicense: string;
     driver: boolean;

     numberOfFlags: number;
     active: boolean;
     block: boolean;

     roles: Role[];
}
