// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { EquiposComponent } from './equipos.component';

// Components Routing
import { EquiposRoutingModule } from './equipos-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    EquiposRoutingModule
  ],
  declarations: [
    EquiposComponent,
  ]
})
export class EquiposModule { }
