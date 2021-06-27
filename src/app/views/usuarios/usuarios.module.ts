// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { UsuariosComponent } from './usuarios.component';

// Components Routing
import { UsuariosRoutingModule } from './usuarios-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    UsuariosRoutingModule
  ],
  declarations: [
    UsuariosComponent,
  ]
})
export class UsuariosModule { }
