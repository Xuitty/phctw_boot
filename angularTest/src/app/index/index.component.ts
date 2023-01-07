import { Component } from '@angular/core';
import { TitleSenderService } from '../service/title-sender.service';
@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
})
export class IndexComponent {
  public title: string;

  constructor(private titleSenderService: TitleSenderService) {
    this.title = titleSenderService.getMessage();
  }
  goToLogin() {
    location.href = '/login';
  }
}
