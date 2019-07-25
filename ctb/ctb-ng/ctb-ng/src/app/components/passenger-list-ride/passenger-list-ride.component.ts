import { Component, OnInit } from '@angular/core';
import { Ride } from 'src/app/models/ride';
import { RideService } from 'src/app/services/ride.service';

@Component({
  selector: "app-passenger-list-ride",
  templateUrl: "./passenger-list-ride.component.html",
  styleUrls: ["./passenger-list-ride.component.css"]
})
export class PassengerListRideComponent implements OnInit {
  rides: Ride[];

  constructor(private rideServ: RideService) {}

  ngOnInit() {
    this.rideServ
      .getAvailableRides()
      .subscribe(rides => (this.rides = rides));
  }
}
