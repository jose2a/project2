import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking';
import { BookingService } from 'src/app/services/booking.service';

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

  constructor(private bookingServ: BookingService) {}

  ngOnInit() {
    this.bookingServ.getBookingByEmployee(117).subscribe(bookingList => {
      this.bookings = bookingList;
    });
  }
}
