import { Component } from '@angular/core';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent {
  title = "ctb-ng";

  /**
   *
   */
  constructor(private empServ: EmployeeService) {}

  logout(): void {
    this.empServ.logoutEmployee();
  }
}
