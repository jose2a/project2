import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: "app-form-employee",
  templateUrl: "./form-employee.component.html",
  styleUrls: ["./form-employee.component.css"]
})
export class FormEmployeeComponent implements OnInit, OnDestroy {
  employee: Employee;
  result: string;
  valErrors: string[];
  employee$: Subscription;

  show = false;

  constructor(
    private employeeService: EmployeeService,
    private authServ: AuthService,
  ) {}

  ngOnInit() {
    this.employee$ = this.authServ.getLoggedEmployee().subscribe(emp => {
      console.log("Logged: " + emp);
      this.show = emp != null;
      console.log(this.show);
    });
    
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

  ngOnDestroy(): void {
    this.employee$.unsubscribe();
    this.show = false;
  }
}
