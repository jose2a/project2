export class Route {
         routeId: number;
         name: string;
         latitude: string;
         longitude: string;
         pickupLocation: boolean;
         destinationLocation: boolean;

         getCoordinates(): any[] {
           return [this.latitude, this.longitude];
         }
       }
