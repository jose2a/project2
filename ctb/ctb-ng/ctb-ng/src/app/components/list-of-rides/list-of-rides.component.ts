import { Component, OnInit } from '@angular/core';
import { RideService } from '../services/ride.service';
import { Ride } from 'src/app/models/ride';

@Component({
  selector: 'app-list-of-rides',
  templateUrl: './list-of-rides.component.html',
  styleUrls: ['./list-of-rides.component.css']
})
export class ListOfRidesComponent implements OnInit {

  rides: Ride[];
  getRides(): void {
    this.rideService.getRides()
    .subscribe(rides => this.rides = rides);
  }

  constructor(private rideService: RideService) { }

  ngOnInit() {
    this.getRides();
  }

}
