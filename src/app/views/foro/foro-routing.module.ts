import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ForoComponent } from './foro.component';

const routes: Routes = [
  {
    path: '',
    component: ForoComponent,
    data: {
      title: 'Foro'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ForoRoutingModule {}
