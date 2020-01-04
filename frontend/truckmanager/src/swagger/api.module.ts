import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { ApplicationsService } from './api/applications.service';
import { ClassificationsService } from './api/classifications.service';
import { ColorsService } from './api/colors.service';
import { FuelTypesService } from './api/fuelTypes.service';
import { TruckPropertiesService } from './api/truckProperties.service';
import { TrucksService } from './api/trucks.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    ApplicationsService,
    ClassificationsService,
    ColorsService,
    FuelTypesService,
    TruckPropertiesService,
    TrucksService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
