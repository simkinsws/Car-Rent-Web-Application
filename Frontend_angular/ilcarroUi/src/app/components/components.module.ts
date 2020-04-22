import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';
import { TermsComponent } from './terms/terms.component';



@NgModule({
  declarations: [TermsComponent],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class ComponentsModule { }
