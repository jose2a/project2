import { Employee } from './employee';
import { Route } from './route';
import { Car } from './car';
import { RideStatus } from './rideStatus';

export class Ride {
         rideId: number;

         departureDate: Date;
         departureTime: Date;
         numberOfSeatsAvailable: number;
         amountCharge: number;
         numberOfBookings: number;

         employee: Employee;
         car: Car;
         rideStatus: RideStatus;

         routes: Route[];

         constructor() {
             this.employee = new Employee();
             this.car = new Car();
             this.routes = [];
         }
       }
