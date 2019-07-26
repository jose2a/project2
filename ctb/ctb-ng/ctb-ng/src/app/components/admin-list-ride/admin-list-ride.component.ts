import { Component, OnInit } from '@angular/core';
import { RideService } from 'src/app/services/ride.service';
import { Ride } from 'src/app/models/ride';

@Component({
  selector: 'app-admin-list-ride',
  templateUrl: './admin-list-ride.component.html',
  styleUrls: ['./admin-list-ride.component.css']
})
export class AdminListRideComponent implements OnInit {

  rides: Ride[];

  headElements = ['ID', 'Departure Date', 'Departure Time', 'Number of Seats', 'Amount Changes', 'Booking', 'Employee'];

  constructor(private rideServ: RideService) { }

  ngOnInit() {
    this.rideServ.getRides().subscribe((rides) => this.rides = rides);
  }

}
