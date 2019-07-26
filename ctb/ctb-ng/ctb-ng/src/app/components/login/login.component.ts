import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';
import { Employee } from 'src/app/models/employee';
import { HttpErrorResponse } from '@angular/common/http';
import { Role } from 'src/app/models/role';
import { Router } from '@angular/router';
@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  employee: Employee;
  results: string;
  valErrors: string[];

  constructor(
    public employeeService: EmployeeService,
    private router: Router
  ) {}

  ngOnInit() {
    this.employee = new Employee();
  }

  loginEmployee(): void {
    this.employeeService.loginEmployee(this.employee).subscribe(
      (employee: Employee) => {
        console.log(employee);

        this.valErrors = [];
        this.employee = new Employee();

        this.results = `Employee logged in`;

        this.redirectEmployee(employee.roles);
      },
      (errorResp: HttpErrorResponse) => {
        this.results = ``;
        console.log("unsuccessful login");

        this.valErrors = errorResp.error.errorMessages;
      }
    );
  }

  redirectEmployee(roles: Role[]) {
    let isAdmin = false;
    let isDriver = false;

    roles.forEach(r => {
      if(r.roleId === 1) {
        isDriver = true;
      } else if (r.roleId === 3) {
        isAdmin = true;
      }
    });
    
    if (isDriver) {
      this.router.navigate(["driver/ride"]);
    } else if (isAdmin) {
      
    } else {
      this.router.navigate(["passenger/ride"]);
    }
  }
}
