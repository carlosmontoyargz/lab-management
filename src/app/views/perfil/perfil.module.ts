// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { PerfilComponent } from './perfil.component';

// Components Routing
import { PerfilRoutingModule } from './perfil-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PerfilRoutingModule
  ],
  declarations: [
    PerfilComponent
  ]
})
export class PerfilModule { }
