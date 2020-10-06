import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {FormlyFieldConfig, FormlyFormOptions} from "@ngx-formly/core";
import {GenerateFormService} from "./generate-form.service";
import {catchError, tap} from "rxjs/operators";
import {throwError} from "rxjs";

@Component({
  selector: 'app-generate-form',
  templateUrl: './generate-form.component.html'
})
export class GenerateFormComponent implements OnInit {
  form = new FormGroup({});
  model: any = {};
  options: FormlyFormOptions = {
    formState: {
      awesomeIsForced: false,
    },
  };
  fields: FormlyFieldConfig[] = [];
  error: string;
  private email: string;

  constructor(private generateFormService: GenerateFormService) {
  }

  ngOnInit(): void {
  }

  submit() {
    console.log("submit ", this.model);
    if (this.form.invalid) {
      return;
    }
    this.error = '';
    this.generateFormService.saveFieldsValues(this.model, this.email).pipe(tap(response => console.log('response ', response)),
      catchError(err => {
        if (err.status === 404)
          this.error = 'something is wrong!'
        return throwError(err);
      })).subscribe();
  }

  getForm(email: string) {
    this.email = email;
    console.log('email', this.form);
    if (email === '') {
      this.error = 'you have to enter your email';
      return;
    }
    this.error = '';
    this.generateFormService.getAllFieldsOfUser(email).pipe(tap(fields => this.fields = fields),
      catchError(err => {
        if (err.status === 404)
          this.error = 'user not found'
        return throwError(err);
      })).subscribe();
  }
}
