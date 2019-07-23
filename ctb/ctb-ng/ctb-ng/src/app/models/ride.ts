import { Employee } from './employee';


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

}
