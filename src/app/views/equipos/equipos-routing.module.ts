import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EquiposComponent } from './equipos.component';

const routes: Routes = [
  {
    path: '',
    component: EquiposComponent,
    data: {
      title: 'Equipos'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EquiposRoutingModule {}
