import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Employee } from 'src/app/models/employee';
import { Router } from '@angular/router';

@Component({
  selector: "app-driver-dashboard",
  templateUrl: "./driver-dashboard.component.html",
  styleUrls: ["./driver-dashboard.component.css"]
})
export class DriverDashboardComponent implements OnInit {
  emp: Employee;

  constructor(private authServ: AuthService, private router: Router) {}

  ngOnInit() {
    this.emp = this.authServ.getEmployeeFromSession();

    if (this.emp === null) {
      this.router.navigate(["/"]);
    }
  }
}
