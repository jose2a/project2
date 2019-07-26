
import { EmployeeService } from 'src/app/services/employee.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { Observable, Subscription } from 'rxjs';


@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit, OnDestroy{
  title = "CTB";
  employee$: Subscription;
  emp: Employee;
  show: boolean;

  /**
   *
   */
  constructor(
  private empServ: EmployeeService,
  private authServ: AuthService
  ) {}


  ngOnInit() {
    this.employee$ = this.authServ.getLoggedEmployee().subscribe(emp => {
      console.log("Logged: " + emp);
      this.show = emp != null;
      if (this.show) {
        this.emp = emp;
        console.log(this.emp);
      }
      console.log(this.show);
    });
  }

  logout(): void {
    // console.log("Logout");
    this.empServ.logoutEmployee().subscribe();
  }

  ngOnDestroy(): void {
    this.employee$.unsubscribe();
    this.show = false;
  }

}
