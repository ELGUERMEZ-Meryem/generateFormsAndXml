import { Component } from '@angular/core';
import { FieldType } from '@ngx-formly/core';

@Component({
  selector: 'formly-dropdown-dev',
  template: `
    <div class="card">
      <h3 class="card-header">{{ to.label }}</h3>
      <div class="card-body">
        <formly-field *ngFor="let f of field.fieldGroup" [field]="f"></formly-field>
      </div>
    </div>
  `,
})
export class PanelDropdownDevType extends FieldType {}
