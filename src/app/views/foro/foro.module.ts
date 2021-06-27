// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { ForoComponent } from './foro.component';

// Components Routing
import { ForoRoutingModule } from './foro-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ForoRoutingModule
  ],
  declarations: [
    ForoComponent,
  ]
})
export class ForoModule { }
