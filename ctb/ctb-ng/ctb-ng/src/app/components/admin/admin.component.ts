import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Employee } from 'src/app/models/employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  emp: Employee;

  constructor(private authServ: AuthService, private router: Router) { }

  ngOnInit() {
    this.emp = this.authServ.getEmployeeFromSession();

    if (this.emp === null) {
      this.router.navigate(["/"]);
    }
  }

}
