import { EmployeeService } from 'src/app/services/employee.service';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Observable, Subscription, empty } from 'rxjs';
import { Employee } from 'src/app/models/employee';



@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit{
  title = "CTB";

    employee$: Subscription;
    show = false;
    emp: Employee = null;

    isAdmin = false;
    isDriver = false;
    isPassenger = false;


  /**
   *
   */
  constructor(private empServ: EmployeeService, private authServ: AuthService) {}

  ngOnInit() {
    
    this.employee$ = this.authServ.getLoggedEmployee().subscribe(emp => {
      console.log("Logged: " + emp);
      this.show = emp != null;
      console.log(this.show);
      this.emp = emp;

      if (this.emp !== null) {
      if(this.emp.roles.length === 2) {
        this.isDriver = true;
      } else {
        this.emp.roles.forEach(r => {
         if(r.roleId === 1) {
            this.isDriver = true;
          } else if (r.roleId === 2) {
           this.isPassenger = true;
         }else if (r.roleId === 3) {
            this.isAdmin = true;
          }
        
     });
    }

    }
    });
  }


  //  if (isDriver) {
  //     this.router.navigate(["driver/ride"]);
  //  } else if (isAdmin) {
  //    this.router.navigate(["admin/ride"]);
  //  } else if (isPassenger){
  //    this.router.navigate(["passenger/ride"]);

  //  }
  logout(): void {
    // console.log("Logout");
    this.empServ.logoutEmployee().subscribe();
  }

}
