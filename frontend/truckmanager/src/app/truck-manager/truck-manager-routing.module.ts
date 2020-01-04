import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManagerComponent } from './manager/manager.component';
import { DetailComponent } from './manager/detail/detail.component';
import { CreateEditComponent } from './manager/create-edit/create-edit.component';


const routes: Routes = [
  {
    path: '',
    component: ManagerComponent,
    children: [
      {
        path: 'create',
        component: CreateEditComponent,
      },
      {
        path: ':id/edit',
        component: CreateEditComponent
      },
      {
        path: ':id',
        component: DetailComponent
      }
    ]
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TruckManagerRoutingModule { }
