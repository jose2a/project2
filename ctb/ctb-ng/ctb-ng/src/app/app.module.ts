import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './components/app/app.component';
import { EmployeeService } from './services/employee.service';
import { FormEmployeeComponent } from './components/form-employee/form-employee.component';
import { LoginComponent } from './components/login/login.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';

import { CarComponent } from './components/car/car.component';
import { ListOfRidesComponent } from './components/list-of-rides/list-of-rides.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { AppRoutingModule } from './app-routing.module';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AdminComponent } from './components/admin/admin.component';
import { AdminListRideComponent } from './components/admin-list-ride/admin-list-ride.component';
import { DriverListRideComponent } from './components/driver-list-ride/driver-list-ride.component';
import { DriverRideComponent } from './components/driver-ride/driver-ride.component';
import { PassengerListRideComponent } from './components/passenger-list-ride/passenger-list-ride.component';
import { PassengerRideComponent } from './components/passenger-ride/passenger-ride.component';
import { DriverShowRideComponent } from './components/driver-show-ride/driver-show-ride.component';

@NgModule({
  declarations: [
    AppComponent,
    FormEmployeeComponent,
    LoginComponent,


    CarComponent,
    ListOfRidesComponent,
    NotFoundComponent,
    AdminComponent,
    AdminListRideComponent,
    DriverListRideComponent,
    DriverRideComponent,
    PassengerListRideComponent,
    PassengerRideComponent,
    DriverShowRideComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,

    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,

    AppRoutingModule
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
