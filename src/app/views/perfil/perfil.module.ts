import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { ModalModule } from 'ngx-bootstrap/modal';

import { PerfilComponent } from './perfil.component';
import { PerfilRoutingModule } from './perfil-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PerfilRoutingModule,
    ModalModule.forRoot()
  ],
  declarations: [ PerfilComponent ]
})
export class PerfilModule { }
