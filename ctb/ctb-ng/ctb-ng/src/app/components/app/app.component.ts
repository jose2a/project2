
import { EmployeeService } from 'src/app/services/employee.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { Observable, Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit, OnDestroy{
  router = ActivatedRoute;
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
    let isAdmin = false;
    let isDriver =false;
    let isPassenger = false;
    this.employee$ = this.authServ.getLoggedEmployee().subscribe(emp => {
      console.log("Logged: " + emp);
      this.show = emp != null;
      if (this.show) {
        this.emp = emp;
      console.log(this.show);
      }

      this.emp.roles.forEach(r => {
        if (r.roleId === 1) {
          isDriver = true;
        } else if (r.roleId === 2) {
          isPassenger = true;
        } else if (r.roleId === 3) {
          isAdmin = true;
        }
      });
      // if (isDriver) {
      //   this.router.navigate([“driver/ride”]);
      // } else if (isAdmin) {
      // } else {
      //   this.router.navigate([“passenger/ride”]);
      // }
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
