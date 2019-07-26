import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking';
import { BookingService } from 'src/app/services/booking.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: "app-passenger-bookings",
  templateUrl: "./passenger-bookings.component.html",
  styleUrls: ["./passenger-bookings.component.css"]
})
export class PassengerBookingsComponent implements OnInit {
  headElements = [
    "Departure",
    "Driver",
    "Car",
    "Status",
    "Pickup Location",
    "Destination Location",
    ""
  ];

  bookings: Booking[];

  constructor(
    private bookingServ: BookingService,
    private authServ: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    const emp = this.authServ.getEmployeeFromSession();

    if (emp === null) {
      this.router.navigate(["/"]);
    }
    
    this.bookingServ
      .getBookingByEmployee(emp.employeeId)
      .subscribe(bookingList => {
        this.bookings = bookingList;
      });
  }
}
