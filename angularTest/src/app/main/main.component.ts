import {
  Component,
  OnInit,
  DoCheck,
  ViewChild,
  ElementRef,
} from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Student } from '../bean/student';
import { lastValueFrom, Subscription } from 'rxjs';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  constructor(private http: HttpClient, private cookie: CookieService) {}

  sno?: string;
  sname?: string;
  smail?: string;
  sid?: string;
  sbday?: string;
  ssex?: string;
  message?: string;

  @ViewChild('resetPasswordOld') resetPasswordOld?: ElementRef;
  @ViewChild('resetPasswordNew') resetPasswordNew?: ElementRef;
  @ViewChild('resetPasswordNewConfirm') resetPasswordNewConfirm?: ElementRef;

  log = console.log;
  action: string = 'main';
  formSwitch: string = '';
  submitSwitch: string = '';
  student: Student = new Student();

  ngOnInit(): void {
    this.action = 'main';
    this.message = '';
    let cookie_send = this.cookie.get('username');
    this.log('username=' + cookie_send);
    if (
      cookie_send == null ||
      cookie_send == '' ||
      cookie_send == 'undefined'
    ) {
      location.href = '/';
      return;
    }
    let body = { cookie: cookie_send };
    let url = 'http://127.0.0.1:8080/main_ajax';
    this.http.post<Student>(url, body).subscribe((data) => {
      this.sno = data.sno;
      this.sname = data.sname;
      this.smail = data.smail;
      this.sid = data.sid;
      this.sbday = data.sbday;
      this.ssex = data.ssex == 0 ? '女' : '男';
    });
  }

  doLogout() {
    this.cookie.deleteAll();
    location.href = '/';
  }

  goResetPassword() {
    this.action = 'resetPassword';
    this.message = '';
  }

  async doResetPassword() {
    this.formSwitch = 'disabled';
    this.submitSwitch = 'none';
    this.message = '處理中請稍候...';
    let resetPasswordNewConfirm =
      this.resetPasswordNewConfirm?.nativeElement.value;
    let resetPasswordNew = this.resetPasswordNew?.nativeElement.value;
    let resetPasswordOld = this.resetPasswordOld?.nativeElement.value;
    if (
      resetPasswordNew == '' ||
      resetPasswordNewConfirm == '' ||
      resetPasswordOld == ''
    ) {
      this.message = '尚有空欄未填';
      this.formSwitch = '';
      this.submitSwitch = '';
      return;
    }

    if (resetPasswordNew != resetPasswordNewConfirm) {
      this.message = '新密碼不一致';
      this.formSwitch = '';
      this.submitSwitch = '';
      return;
    }

    let url = 'http://127.0.0.1:8080/resetPassword_ajax';
    let body = {
      sno: this.sno,
      spwd: resetPasswordOld,
      sid: resetPasswordNew,
    };
    let options = {
      observe: 'response' as 'response',
      responseType: 'text' as 'text',
    };
    let r = await lastValueFrom<HttpResponse<string>>(
      this.http.post(url, body, options)
    );
    this.log(r);
    if (r.body == 'true') {
      let t: number = 5;
      let conter = setInterval(() => {
        t--;
        this.message =
          '變更密碼成功， ' + t + ' 秒後跳轉至首頁，請使用新密碼登入';
        if (t == 0) {
          clearInterval(conter);
          this.cookie.deleteAll();
          location.href = '/login';
        }
      }, 1000);
    } else {
      this.message = '舊密碼錯誤，請重新輸入';
      this.formSwitch = '';
      this.submitSwitch = '';
      return;
    }
  }
}
