import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './components/app/app.component';
import { EmployeeService } from './services/employee.service';
import { FormEmployeeComponent } from './components/form-employee/form-employee.component';
import { LoginComponent } from './components/login/login.component';
import { CarComponent } from './components/car/car.component';
import { ListOfRidesComponent } from './components/list-of-rides/list-of-rides.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    FormEmployeeComponent,
    LoginComponent,
    CarComponent,
    ListOfRidesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },
  EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
