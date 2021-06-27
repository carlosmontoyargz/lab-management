import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BitacoraComponent } from './bitacora.component';

const routes: Routes = [
  {
    path: '',
    component: BitacoraComponent,
    data: {
      title: 'Bitacora'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BitacoraRoutingModule {}
