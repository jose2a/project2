import { Employee } from './employee';
import { Route } from './route';
import { Car } from './car';
import { RideStatus } from './rideStatus';

export class Ride {
    rideId: number;

    departureDate: Date;
    departureTime: Date;
    numSeatsAvailable: number;
    amountCharge: number;
    numBookings: number;

    employee: Employee;
    car: Car;
    rideStatus: RideStatus;

    routes: Route[];
}
