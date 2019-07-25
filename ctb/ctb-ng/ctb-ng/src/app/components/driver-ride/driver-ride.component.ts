import { Component, OnInit } from '@angular/core';
import { Ride } from 'src/app/models/ride';
import { RideService } from 'src/app/services/ride.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: "app-driver-ride",
  templateUrl: "./driver-ride.component.html",
  styleUrls: ["./driver-ride.component.css"]
})
export class DriverRideComponent implements OnInit {
  ride: Ride;

  constructor(private route: ActivatedRoute, private rideServ: RideService) {}

  ngOnInit() {
    const rideId = +this.route.snapshot.paramMap.get("rideId");
    this.rideServ.getRideById(rideId).subscribe(ride => this.ride = ride);
  }
}
