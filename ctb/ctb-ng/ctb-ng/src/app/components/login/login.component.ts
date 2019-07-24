import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(employeeService: EmployeeService) { }

  ngOnInit() {
  }

}
