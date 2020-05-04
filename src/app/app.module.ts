import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { RegisterComponent } from './components/register/register.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {ComponentsModule} from './components/components.module';
import {MostPopularCarsComponent} from './components/most-popular-cars/most-popular-cars.component';
import {LoginComponent} from './components/login/login.component';
import {ProfileComponent} from './components/profile/profile.component';
import {SettingsComponent} from './components/profile/settings/settings.component';
import {PromoBannerComponent} from './components/promo-banner/promo-banner.component';
import {LastFeedsComponent} from './components/last-feeds/last-feeds.component';
import {MainComponent} from './components/main/main.component';
import {CommonModule, DatePipe} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSelectModule} from '@angular/material/select';
import {NgxMatSelectSearchModule} from 'ngx-mat-select-search';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatButtonModule} from '@angular/material/button';
import {TermsComponent} from "./components/terms/terms.component";
import {AddCarComponent} from "./components/add-car/add-car.component";
import {MainResultsComponent} from "./components/main-results/main-results.component";
import {MatIconModule} from "@angular/material/icon";

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    ProfileComponent,
    SettingsComponent,
    MostPopularCarsComponent,
    PromoBannerComponent,
    LastFeedsComponent,
    MainComponent,
    TermsComponent,
    AddCarComponent,
    MainResultsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ComponentsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatSelectModule,
        NgxMatSelectSearchModule,
        MatInputModule,
        MatFormFieldModule,
        MatStepperModule,
        CommonModule,
        FormsModule,
        MatFormFieldModule,
        MatSelectModule,
        MatAutocompleteModule,
        MatButtonModule,
        MatIconModule,
    ],
  providers: [DatePipe],
  exports: [
    MostPopularCarsComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
