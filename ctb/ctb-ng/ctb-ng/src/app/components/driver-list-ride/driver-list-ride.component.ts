import { Component, OnInit } from '@angular/core';
import { Ride } from 'src/app/models/ride';
import { RideService } from 'src/app/services/ride.service';
import { AuthService } from 'src/app/services/auth.service';
import { Employee } from 'src/app/models/employee';

@Component({
  selector: "app-driver-list-ride",
  templateUrl: "./driver-list-ride.component.html",
  styleUrls: ["./driver-list-ride.component.css"]
})
export class DriverListRideComponent implements OnInit {
  rides: Ride[];
  
  headElements = ['ID', 'Departure Date', 'Departure Time', 'Number Of Seats', 'Amount change', 'Bookings', 'Employee'];

  constructor(private rideServ: RideService, private authServ: AuthService) {}

  ngOnInit() {
    const emp = this.authServ.getEmployeeFromSession();

    this.rideServ.getRidesByEmployee(emp.employeeId).subscribe(rides => (this.rides = rides));
  }
}
