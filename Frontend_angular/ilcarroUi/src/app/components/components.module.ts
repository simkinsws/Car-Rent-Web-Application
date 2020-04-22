import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';
import { PromoBannerComponent } from './promo-banner/promo-banner.component';
import { LastFeedsComponent } from './last-feeds/last-feeds.component';


@NgModule({
  declarations: [PromoBannerComponent, LastFeedsComponent],
  exports: [
    PromoBannerComponent,
    LastFeedsComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class ComponentsModule { }
