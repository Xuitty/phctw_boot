import { Component } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent {
  sno!: string;
  sname!: string;
  smail!: string;
  sid!: string;
  sbday!: string;
  ssex!: number;
  message!: string;
}
