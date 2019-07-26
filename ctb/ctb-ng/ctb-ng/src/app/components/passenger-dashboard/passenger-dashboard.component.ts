import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Employee } from 'src/app/models/employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-passenger-dashboard',
  templateUrl: './passenger-dashboard.component.html',
  styleUrls: ['./passenger-dashboard.component.css']
})
export class PassengerDashboardComponent implements OnInit {
  emp: Employee;

  constructor(private authServ: AuthService, private router: Router) { }

  ngOnInit() {
    this.emp = this.authServ.getEmployeeFromSession();

    if (this.emp === null) {
      this.router.navigate(["/"]);
    }
  }
}
