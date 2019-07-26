import { Component, OnInit } from '@angular/core';
import { Ride } from 'src/app/models/ride';
import { RideService } from 'src/app/services/ride.service';

@Component({
  selector: "app-driver-list-ride",
  templateUrl: "./driver-list-ride.component.html",
  styleUrls: ["./driver-list-ride.component.css"]
})
export class DriverListRideComponent implements OnInit {
  rides: Ride[];

  headElements = ['ID', 'Departure Date', 'Departure Time', 'Number Of Seats', 'Amount change', 'Bookings', 'Employee'];

  dataSource = this.rides;

  constructor(private rideServ: RideService) {}

  ngOnInit() {
    this.rideServ.getRidesByEmployee(110).subscribe(rides => (this.rides = rides));
  }
}
