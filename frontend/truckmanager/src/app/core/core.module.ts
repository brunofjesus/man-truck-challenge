import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiModule, Configuration, ConfigurationParameters, BASE_PATH } from 'src/swagger';
import { environment } from 'src/environments/environment';
import { HttpClientModule } from '@angular/common/http';


export function apiConfigFactory(): Configuration {
  const params: ConfigurationParameters = {
    // set configuration parameters here.
  }
  return new Configuration(params);
}

@NgModule({
  declarations: [],
  imports: [
    ApiModule.forRoot(apiConfigFactory),
    HttpClientModule,
    CommonModule
  ], providers: [{ provide: BASE_PATH, useValue: environment.apiUrl }]
})
export class CoreModule { }
