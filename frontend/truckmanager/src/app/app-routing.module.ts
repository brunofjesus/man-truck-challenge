import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/manager',
    pathMatch: 'full' 
  },
  {
    path: 'manager',
    loadChildren: () => import('./truck-manager/truck-manager.module').then(mod => mod.TruckManagerModule),
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
