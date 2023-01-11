import { Component } from '@angular/core';
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
  styleUrls: ['./datepicker-popup.css'],
})
export class NgbdDatepickerPopup {
  model?: NgbDateStruct;
}
