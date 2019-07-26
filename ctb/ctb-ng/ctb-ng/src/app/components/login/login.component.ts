import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';
import { Employee } from 'src/app/models/employee';
import { HttpErrorResponse } from '@angular/common/http';
// import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  employee: Employee;
  results: string;
  valErrors: string[];
  // AuthService authServ;

  constructor(public employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = new Employee();
  }

  loginEmployee(): void {

    this.employeeService.loginEmployee(this.employee)
    .subscribe((employee: Employee) => {
      console.log(employee);

      this.valErrors = [];
      this.employee = new Employee();

      this.results = `Employee logged in`;
      console.log('user successfully logged in');
      // for (let role of this.employee.roles.){
      //   if(role == 1){
      //     localStorage.setItem('userRole', AuthService.getEmployeeFromSession().employee.userRole);
      //   }
      //   )
      // }


    }, (errorResp: HttpErrorResponse) => {
        this.results = ``;
        console.log('unsuccessful login');

        this.valErrors = errorResp.error.errorMessages;
      }
    );
  }

}
