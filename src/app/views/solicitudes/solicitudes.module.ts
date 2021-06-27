// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { SolicitudesComponent } from './solicitudes.component';

// Components Routing
import { SolicitudesRoutingModule } from './solicitudes-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SolicitudesRoutingModule
  ],
  declarations: [
    SolicitudesComponent,
  ]
})
export class SolicitudesModule { }
