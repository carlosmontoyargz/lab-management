import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BitacoraComponent } from './bitacora.component';
import {BitacoraRoutingModule} from "./bitacora-routing.module";



@NgModule({
  declarations: [
    BitacoraComponent
  ],
  imports: [
    CommonModule,
    BitacoraRoutingModule
  ]
})
export class BitacoraModule { }
