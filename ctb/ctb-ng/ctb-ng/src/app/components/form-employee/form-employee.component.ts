import { Component, OnInit, Input } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { Observable } from 'rxjs';
import { Router } from "@angular/router";

@Component({
  selector: "app-form-employee",
  templateUrl: "./form-employee.component.html",
  styleUrls: ["./form-employee.component.css"]
})
export class FormEmployeeComponent implements OnInit {
  employee: Employee;
  result: string;
  valErrors: string[];
  employee$: Observable<Employee>;

  constructor(
    private employeeService: EmployeeService,
    private authServ: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.employee$ = this.authServ.getLoggedEmployee();
    this.employee = new Employee();
  }

  registerEmployee(): void {
    this.employeeService.registerEmployee(this.employee).subscribe(
      (newEmployee: Employee) => {
        console.log(newEmployee);

        this.valErrors = [];
        this.employee = new Employee();

        this.result = `Employee registered`;
      },
      (errorResp: HttpErrorResponse) => {
        this.result = ``;

        this.valErrors = errorResp.error.errorMessages;
      }
    );
  }
}
