import { Component } from '@angular/core';
import { TitleSenderService } from '../service/title-sender.service';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
})
export class IndexComponent {
  public title: string;
  private log = console.log;
  constructor(
    private titleSenderService: TitleSenderService,
    private http: HttpClient,
    private cookie: CookieService
  ) {
    this.title = titleSenderService.getMessage();
  }
  goToLogin() {
    if (
      this.cookie.check('username') == true &&
      this.cookie.get('username') != '' &&
      this.cookie.get('username') != null &&
      this.cookie.get('username') != 'undefined'
    ) {
      location.href = '/main';
      return;
    }

    location.href = '/login';
  }
}
