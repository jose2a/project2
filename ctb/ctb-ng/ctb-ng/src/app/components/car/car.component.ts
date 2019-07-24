import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: "app-car",
  templateUrl: "./car.component.html",
  styleUrls: ["./car.component.css"]
})
export class CarComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit() {
    this.router.navigateByUrl("/");
  }

  
}
