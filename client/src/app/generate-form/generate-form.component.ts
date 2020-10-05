import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {FormlyFieldConfig, FormlyFormOptions} from "@ngx-formly/core";

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

  constructor() { }

  ngOnInit(): void {
  }

  submit() {
    console.log("submit ",this.model);
  }
}
