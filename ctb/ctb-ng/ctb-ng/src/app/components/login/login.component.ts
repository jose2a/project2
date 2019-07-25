import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';
import { Employee } from 'src/app/models/employee';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  employee: Employee;
  results: string;
  valErrors: string[];

  constructor(public employeeService: EmployeeService) { }

  ngOnInit() {
  }

  loginEmployee(): void {

    this.employeeService.loginEmployee(this.employee)
    .subscribe((employee: Employee) => {
      console.log(employee);

      this.valErrors = [];
      this.employee = new Employee();

      this.results = `Employee logged in`;
      console.log('user successfully logged in');

    }, (errorResp: HttpErrorResponse) => {
        this.results = ``;
        console.log('unsuccessful login');

        this.valErrors = errorResp.error.errorMessages;
      }
    );
  }

}
