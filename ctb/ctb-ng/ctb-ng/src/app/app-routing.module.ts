import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CarComponent } from './components/car/car.component';
import { FormEmployeeComponent } from './components/form-employee/form-employee.component';
import { ListOfRidesComponent } from './components/list-of-rides/list-of-rides.component';

const routes: Routes = [
  { path: "", redirectTo: "/dashboard", pathMatch: "full" },
  { path: "dashboard", component: LoginComponent },
  { path: "car", component: CarComponent },
  { path: "register", component: FormEmployeeComponent },
  { path: "ride", component: ListOfRidesComponent }
  // { path: 'detail/:id', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
