import { Component } from '@angular/core';
import { FieldArrayType } from '@ngx-formly/core';

@Component({
  selector: 'formly-repeat-section',
  template: `
    <label>{{ field.key }}:{{ field.fieldGroup.length }}: {{ to.minItems }} : {{ to.maxItems }} </label>
    <div *ngFor="let f of field.fieldGroup; let i = index;" class="row">
      <formly-field class="col" [field]="f" ></formly-field>
      <div *ngIf="!to.minItems || to.minItems != field.fieldGroup.length || to.minItems < field.fieldGroup.length" class="col">
        <button class="btn btn-danger" type="button" (click)="remove(i)">remove</button>
      </div>
    </div>
    <div
      style="margin:30px 0;"
      *ngIf="!to.maxItems || to.maxItems > field.fieldGroup.length">
      <button class="btn btn-primary" type="button" (click)="add()">add</button>
    </div>
  `,
})
export class RepeatTypeComponent extends FieldArrayType {}
