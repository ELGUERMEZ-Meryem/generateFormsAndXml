import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {GenerateFormComponent} from './generate-form/generate-form.component';
import {AbstractControl, ReactiveFormsModule} from "@angular/forms";
import {FormlyModule} from "@ngx-formly/core";
import {FormlyBootstrapModule} from "@ngx-formly/bootstrap";
import {GenerateFormService} from "./generate-form/generate-form.service";
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {DatepickerTypeComponent} from "./generate-form/datepicker-type.component";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {PanelDropdownDevType} from "./generate-form/dropdown-dev.type";
import {FormlyFieldFile} from "./generate-form/file-type.component";
import {FileValueAccessor} from "./generate-form/file-value-accessor";
import {RepeatTypeComponent} from "./generate-form/repeat-section.type";

export function minlengthValidationMessage(err, field) {
  return `Should have at least ${field.templateOptions.minLength} characters`;
}

export function maxlengthValidationMessage(err, field) {
  return `This value should be less than ${field.templateOptions.maxLength} characters`;
}

export function fieldMatchValidator(control: AbstractControl) {
  const { password, passwordConfirm } = control.value;

  // avoid displaying the message error when values are empty
  if (!passwordConfirm || !password) {
    return null;
  }

  if (passwordConfirm === password) {
    return null;
  }

  return { fieldMatch: { message: 'Password Not Matching' } };
}

@NgModule({
  declarations: [
    AppComponent,
    GenerateFormComponent,
    DatepickerTypeComponent,
    PanelDropdownDevType,
    FormlyFieldFile,
    FileValueAccessor,
    RepeatTypeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormlyModule.forRoot({
      validators: [
        { name: 'fieldMatch', validation: fieldMatchValidator },
      ],
      validationMessages: [
        {name: 'required', message: 'This field is required'},
        { name: 'minlength', message: minlengthValidationMessage },
        { name: 'maxlength', message: maxlengthValidationMessage },
      ],
      types: [
        {
          name: 'datepicker',
          component: DatepickerTypeComponent,
          wrappers: ['form-field'],
          defaultOptions: {
            defaultValue: new Date(),
            templateOptions: {
              datepickerOptions: {},
            },
          },
        },
        { name: 'file', component: FormlyFieldFile, wrappers: ['form-field'] },
        { name: 'dropdown-dev', component: PanelDropdownDevType },
        { name: 'repeat', component: RepeatTypeComponent }
      ],
    }),
    FormlyBootstrapModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatDatepickerModule
  ],
  providers: [GenerateFormService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
