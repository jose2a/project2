import { Component, OnInit, Input } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-form-employee',
  templateUrl: './form-employee.component.html',
  styleUrls: ['./form-employee.component.css']
})
export class FormEmployeeComponent implements OnInit {

  employee: Employee;
  result: string;
  valErrors: string[];

  someVariable = `Some variables`;

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.employee = new Employee();
  }

  registerEmployee(): void {

    this.employeeService.registerEmployee(this.employee)
    .subscribe((newEmployee: Employee) => {
      console.log(newEmployee);

      this.valErrors = [];
      this.employee = new Employee();

      this.result = `Employee registered`;
  

    }, (errorResp: HttpErrorResponse) => {
        this.result = ``;

        this.valErrors = errorResp.error.errorMessages;
      }
    );
  }

}
