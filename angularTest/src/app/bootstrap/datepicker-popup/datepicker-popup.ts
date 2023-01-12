import { Component, ViewChild } from '@angular/core';
import {
  NgbAlertModule,
  NgbDatepickerModule,
  NgbDateStruct,
} from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'ngbd-datepicker-popup',
  standalone: true,
  imports: [NgbDatepickerModule, NgbAlertModule, FormsModule, JsonPipe],
  templateUrl: './datepicker-popup.html',
})
export class NgbdDatepickerPopup {
  getError() {
    this.sbdayError = 'is-invalid';
    // console.log(this.reg?.sbdayError);
  }
  clearError() {
    this.sbdayError = '';
  }
  model?: NgbDateStruct;
  sbdayError?: string = '';
}
// import { Component } from '@angular/core';

// /** @title Basic datepicker */
// @Component({
//   selector: 'datepicker-overview-example',
//   templateUrl: 'datepicker-overview-example.html',
// })
// export class DatepickerOverviewExample {}
