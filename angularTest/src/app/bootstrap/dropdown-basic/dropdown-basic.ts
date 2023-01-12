import { Component } from '@angular/core';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'ngbd-dropdown-basic',
  standalone: true,
  imports: [NgbDropdownModule],
  templateUrl: './dropdown-basic.html',
})
export class NgbdDropdownBasic {
  value: string = '選擇性別';
  value_int: number = -1;
  class: string = 'btn btn-outline-secondary';

  male() {
    this.value = '男';
    this.value_int = 1;
    this.class = 'btn btn-outline-primary';
  }

  female() {
    this.value = '女';
    this.value_int = 0;
    this.class = 'btn btn-outline-danger';
  }

  getError() {
    let t: number = 0;
    let blinker = setInterval(() => {
      t++;
      if (t % 2 == 0) {
        this.class = 'btn btn-warning';
      } else {
        this.class = 'btn btn-outline-secondary';
      }
      if (t == 7) {
        clearInterval(blinker);
        this.class = 'btn btn-outline-secondary';
        this.value = '選擇性別';
        this.value_int = -1;
      }
    }, 200);
  }
}
