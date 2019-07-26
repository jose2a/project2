import { Component, OnInit } from '@angular/core';
import { Ride } from 'src/app/models/ride';
import { ActivatedRoute } from '@angular/router';
import { RideService } from 'src/app/services/ride.service';

@Component({
  selector: "app-passenger-ride",
  templateUrl: "./passenger-ride.component.html",
  styleUrls: ["./passenger-ride.component.css"]
})
export class PassengerRideComponent implements OnInit {
  rideId: number;
  editMode: boolean;
  isPassenger: boolean;

  ride: Ride;

  constructor(private route: ActivatedRoute, private rideServ: RideService) {}

  ngOnInit() {
    this.rideId = +this.route.snapshot.paramMap.get("rideId");
    console.log(`DRIVER SHOW: ${this.rideId}`);
    this.editMode = false;
    this.isPassenger = true;
  }
}
