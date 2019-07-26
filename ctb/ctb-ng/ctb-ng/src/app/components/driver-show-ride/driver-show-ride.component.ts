import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: "app-driver-show-ride",
  templateUrl: "./driver-show-ride.component.html",
  styleUrls: ["./driver-show-ride.component.css"]
})
export class DriverShowRideComponent implements OnInit {
  rideId: number;
  editMode: boolean;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.rideId = +this.route.snapshot.paramMap.get("rideId");
    console.log(`DRIVER SHOW: ${this.rideId}`);
    this.editMode = false;
  }

  editRide() {
    this.editMode = true;
  }
}
