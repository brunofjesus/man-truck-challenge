import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManagerComponent } from './manager/manager.component';
import { TruckManagerRoutingModule } from './truck-manager-routing.module';
import { GridComponent } from './manager/grid/grid.component';
import { SharedModule } from '../shared/shared.module';
import { DetailComponent } from './manager/detail/detail.component';
import { TextItemComponent } from './manager/detail/text-item/text-item.component';
import { ColorItemComponent } from './manager/detail/color-item/color-item.component';
import { CreateEditComponent } from './manager/create-edit/create-edit.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [ManagerComponent, GridComponent, DetailComponent, TextItemComponent, ColorItemComponent, CreateEditComponent],
  imports: [
    TruckManagerRoutingModule,
    CommonModule,
    SharedModule,
    FormsModule
  ]
})
export class TruckManagerModule { }
