import { Component, OnInit, Input } from "@angular/core";
import { Ride } from "src/app/models/ride";
import { RideService } from "src/app/services/ride.service";
declare let L;
import "../../../../node_modules/leaflet-routing-machine/dist/leaflet-routing-machine.js";
import "../../../../node_modules/leaflet-control-geocoder/dist/Control.Geocoder.js";
import { Route } from "../../models/route.js";
import { environment } from "../../../environments/environment";
import { Location } from "@angular/common";
import { CarService } from "src/app/services/car.service";
import { Car } from 'src/app/models/car.js';
import { Employee } from 'src/app/models/employee.js';

@Component({
  selector: "app-new-ride",
  templateUrl: "./new-ride.component.html",
  styleUrls: ["./new-ride.component.css"]
})
export class NewRideComponent implements OnInit {
  map: any;
  routing: any;
  myIcon: any;

  ride: Ride;
  from = null;
  to = null;

  routes: Route[] = [];
  cars: Car[];

  wayp: any[] = [];
  newMap = false;

  @Input() editMode: boolean;

  @Input() id: number;

  constructor(
    private carServ: CarService,
    private rideServ: RideService,
    private location: Location
  ) {}

  ngOnInit() {
    this.ride = new Ride();

    this.carServ
      .getCarsByEmployeeId(110)
      .subscribe(carList => (this.cars = carList));

    this.initCoordinates();
    this.initMap();
    this.renderMap();
  }

  initMap() {
    this.map = L.map("map").setView([51.505, -0.09], 13);

    L.tileLayer(
      `https://a.tiles.mapbox.com/v4/mapbox.streets/{z}/{x}/{y}.png?access_token=${
        environment.mapboxKey
      }`,
      {
        attribution: '<a href="http://osm.org/copyright">Terms & Feedback</a>'
      }
    ).addTo(this.map);

    this.myIcon = L.icon({
      iconUrl: "https://unpkg.com/leaflet@1.5.1/dist/images/marker-icon-2x.png",
      iconSize: [38, 50]
    });

    this.routing = L.Routing.control({
      //  waypoints: this.getWayPoints(this.routes)
      lineOptions: {
        styles: [
          { color: "black", opacity: 0.05, weight: 9 },
          { color: "white", opacity: 0.8, weight: 6 },
          { color: "#398aca", opacity: 1, weight: 4 }
        ]
      },
      showAlternatives: false,
      createMarker: function() {
        return null;
      },
      addWaypoints: true
    }).addTo(this.map);

    const self = this;

    L.Control.geocoder({
      defaultMarkGeocode: false,
      geocoder: L.Control.Geocoder.mapbox(environment.mapboxKey),
      position: "topright"
    })
      .on("markgeocode", function(e) {
        console.log("markgeocode");

        console.log(e.geocode);

        if (self.newMap === true) {
          self.routes = [];
          self.newMap = false;
        }

        const coordinate = e.geocode.center;

        const route = new Route();
        route.name = e.geocode.name;
        route.latitude = coordinate.lat;
        route.longitude = coordinate.lng;

        self.map.setZoom(18);

        L.marker(coordinate, { icon: self.myIcon })
          .bindPopup(e.geocode.name)
          .addTo(self.map)
          .openPopup();

        if (self.from == null) {
          self.routing.spliceWaypoints(0, 1, coordinate);
          self.from = coordinate;

          route.pickupLocation = true;
          route.destinationLocation = false;
        } else if (self.to == null) {
          self.routing.spliceWaypoints(
            self.routing.getWaypoints().length - 1,
            1,
            coordinate
          );
          self.to = e.geocode.center;

          route.pickupLocation = false;
          route.destinationLocation = true;
        } else if (self.from != null && self.to != null) {
          const waypoints = self.routing.getWaypoints();
          route.pickupLocation = false;
          route.destinationLocation = false;
          waypoints.splice(
            self.routing.getWaypoints().length - 2,
            0,
            coordinate
          );
          self.routing.setWaypoints(waypoints);
        }

        self.routes.push(route);
      })
      .addTo(this.map);
  }

  initCoordinates() {
    if (this.routes.length === 2) {
      this.from = this.routes.find(route => {
        return route.pickupLocation === true;
      });

      this.to = this.routes.find(route => {
        return route.destinationLocation === true;
      });
    }
  }

  renderMap(): void {
    this.routing.setWaypoints(this.getWayPoints(this.routes));
  }

  getWayPoints(routes: Route[]): any[] {
    const coordinates = [];

    for (const route of routes) {
      const coor = [route.latitude, route.longitude];

      coordinates.push(coor);

      // L.marker(coor, { icon: this.myIcon })
      //   .bindPopup(route.name)
      //   .addTo(this.map)
      //   .openPopup();
    }

    return coordinates;
  }

  removeRoute(route: Route) {
    const index = this.routes.indexOf(route);
    console.log(index);
    this.routes.splice(index, 1);
    this.renderMap();
  }

  schedule() {
	  this.ride.routes = this.routes;
	  this.ride.employee.employeeId = 110;
    this.rideServ.createRide(this.ride).subscribe(newRide => {
      console.log(newRide);
    });
  }

  goBack() {
    this.location.back();
  }
}
