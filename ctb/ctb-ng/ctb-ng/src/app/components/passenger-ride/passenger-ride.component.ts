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
  ride: Ride;

  constructor(private route: ActivatedRoute, private rideServ: RideService) {}

  ngOnInit() {
    const rideId = +this.route.snapshot.paramMap.get("rideId");
    this.rideServ.getRideById(rideId).subscribe(ride => (this.ride = ride));
  }
}
