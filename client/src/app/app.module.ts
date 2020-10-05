import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {GenerateFormComponent} from './generate-form/generate-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {FormlyModule} from "@ngx-formly/core";
import {FormlyBootstrapModule} from "@ngx-formly/bootstrap";
import {TransactionService} from "./generate-form/generate-form.service";

@NgModule({
  declarations: [
    AppComponent,
    GenerateFormComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormlyModule.forRoot(),
    FormlyBootstrapModule
  ],
  providers: [TransactionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
