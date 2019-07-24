import { Employee } from './employee';
import { Ride } from './ride';
import { Route } from './route';
import { Feedback } from './feedback';

export class Booking {
     bookingId: number;

     employee: Employee;

     ride: Ride;

     pickupLocation: Route;

     destinationLocation: Route;

     passengerFeedback: Feedback;
     driverFeedback: Feedback;
}