// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { BitacoraComponent } from './bitacora.component';

// Components Routing
import { BitacoraRoutingModule } from './bitacora-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    BitacoraRoutingModule
  ],
  declarations: [
    BitacoraComponent,
  ]
})
export class BitacoraModule { }
