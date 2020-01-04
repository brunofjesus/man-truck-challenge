import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiModule, Configuration, ConfigurationParameters, BASE_PATH } from 'src/swagger';
import { environment } from 'src/environments/environment';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { SharedModule } from '../shared/shared.module';


export function apiConfigFactory(): Configuration {
  const params: ConfigurationParameters = {
    // set configuration parameters here.
  }
  return new Configuration(params);
}

@NgModule({
  declarations: [HeaderComponent],
  imports: [
    ApiModule.forRoot(apiConfigFactory),
    HttpClientModule,
    CommonModule,
    SharedModule
  ], providers: [{ provide: BASE_PATH, useValue: environment.apiUrl }],
  exports: [
    HeaderComponent
  ]
})
export class CoreModule { }
