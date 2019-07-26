import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CarComponent } from './components/car/car.component';
import { FormEmployeeComponent } from './components/form-employee/form-employee.component';
import { ListOfRidesComponent } from './components/list-of-rides/list-of-rides.component';
import { AdminComponent } from './components/admin/admin.component';
import { AdminListRideComponent } from './components/admin-list-ride/admin-list-ride.component';
import { DriverListRideComponent } from './components/driver-list-ride/driver-list-ride.component';
import { DriverRideComponent } from './components/driver-ride/driver-ride.component';
import { PassengerListRideComponent } from './components/passenger-list-ride/passenger-list-ride.component';
import { PassengerRideComponent } from './components/passenger-ride/passenger-ride.component';
import { DriverDashboardComponent } from './components/driver-dashboard/driver-dashboard.component';
import { PassengerDashboardComponent } from './components/passenger-dashboard/passenger-dashboard.component';

import { NotFoundComponent } from './components/not-found/not-found.component';
import { ServerErrorComponent } from './components/server-error/server-error.component';
import { DriverShowRideComponent } from './components/driver-show-ride/driver-show-ride.component';
import { NewRideComponent } from './components/new-ride/new-ride.component';
import { PassengerBookingsComponent } from './components/passenger-bookings/passenger-bookings.component';


const routes: Routes = [
  { path: "", redirectTo: "/dashboard", pathMatch: "full" },
  { path: "dashboard", component: LoginComponent },
  { path: "car", component: CarComponent },
  { path: "register", component: FormEmployeeComponent },
  { path: "ride", component: ListOfRidesComponent },

  { path: "admin", component: AdminComponent },
  { path: "admin/ride", component: AdminListRideComponent },

  { path: "driver", component: DriverDashboardComponent},
  { path: "driver/ride", component: DriverListRideComponent },
  { path: "driver/ride/:rideId", component: DriverShowRideComponent },

  { path: "passenger", component: PassengerDashboardComponent},
  { path: "passenger/ride", component: PassengerListRideComponent },
  { path: "passenger/ride/:rideId", component: PassengerRideComponent },
  { path: "passenger/booking", component: PassengerBookingsComponent },

  { path: "notfound", component: NotFoundComponent },
  { path: "servererror", component: ServerErrorComponent },

  { path: "driver/scheduleride", component: NewRideComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
