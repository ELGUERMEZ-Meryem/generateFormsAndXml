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

  constructor(private generateFormService: GenerateFormService) {
  }

  ngOnInit(): void {
  }

  submit() {
    console.log("submit ", this.model);
  }

  getForm(email: string) {
    console.log('email', email);
    this.generateFormService.getAllFieldsOfUser(email).pipe(tap(fields => this.fields = fields), catchError(err => throwError(err))).subscribe();
  }
}
