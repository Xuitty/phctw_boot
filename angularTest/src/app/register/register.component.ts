import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Student } from '../bean/student';
import { CookieService } from 'ngx-cookie-service';
import { lastValueFrom, Observable, tap } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  constructor(private http: HttpClient, private cookie: CookieService) {}
  log = console.log;
  @ViewChild('sno') sno_native?: ElementRef;
  @ViewChild('spwd') spwd_native?: ElementRef;
  @ViewChild('spwdc') spwdc_native?: ElementRef;
  @ViewChild('sname') sname_native?: ElementRef;
  @ViewChild('ssex') ssex_native?: ElementRef;
  // ssex?: number;
  @ViewChild('sbday') sbday_native?: ElementRef;
  @ViewChild('smail') smail_native?: ElementRef;
  @ViewChild('sid') sid_native?: ElementRef;
  @ViewChild('verify') verify?: ElementRef;
  message?: string;
  action: string = 'register';
  verifySno: string = '';
  s: Student = new Student();
  // check_test?: string;
  ssex_receive?: number = 0;

  formSwitch: string = '';
  snoError: string = '';
  spwdError: string = '';
  snameError: string = '';
  sbdayError: string = '';
  smailError: string = '';
  sidError: string = '';
  async doRegister() {
    this.snoError = '';
    this.spwdError = '';
    this.snameError = '';
    this.sbdayError = '';
    this.smailError = '';
    this.sidError = '';
    let s_detect: Student = new Student();
    s_detect.sno = this.sno_native?.nativeElement.value;
    s_detect.spwd = this.spwd_native?.nativeElement.value;
    let spwdc = this.spwdc_native?.nativeElement.value;
    s_detect.sname = this.sname_native?.nativeElement.value;
    s_detect.ssex = this.ssex_native?.nativeElement.value;
    s_detect.sbday = this.sbday_native?.nativeElement.value;
    s_detect.smail = this.smail_native?.nativeElement.value;
    s_detect.sid = this.sid_native?.nativeElement.value;

    // this.log(s_detect);
    let r = await this.detect(s_detect, spwdc);
    this.log(r);
    if (r == 'somethingEmpty') {
      this.message = '有欄位沒填';
      return;
    }
    if (r == 'passwordNotMatch') {
      this.message = '密碼不一致';
      this.spwdError = 'is-invalid';
      return;
    }
    if (r == 'duplicatedSno') {
      this.message = '學號重複';
      this.snoError = 'is-invalid';
      return;
    }
    if (r == 'idError') {
      this.message = '身分證錯誤';
      this.sidError = 'is-invalid';
      return;
    }
    if (r == 'idSexError') {
      this.message = '身分證&性別錯誤';
      this.sidError = 'is-invalid';
      return;
    }
    if (r == 'duplicatedSid') {
      this.message = '身分證重複';
      this.sidError = 'is-invalid';
      return;
    }
    if (r == 'passwordTooShort') {
      this.message = '密碼過短';
      this.spwdError = 'is-invalid';
      return;
    }
    if (r == 'mailFormatError') {
      this.message = '信箱格式錯誤';
      this.smailError = 'is-invalid';
      return;
    }
    if (r == 'BdayError') {
      this.message = '出生日期年份錯誤';
      this.sbdayError = 'is-invalid';
      return;
    }

    this.s.sno = s_detect.sno;
    this.s.spwd = s_detect.spwd;
    this.s.sname = s_detect.sname;
    this.s.ssex = s_detect.ssex;
    this.s.sbday = s_detect.sbday;
    this.s.smail = s_detect.smail;
    this.s.sid = s_detect.sid;
    this.message = '資料處理中，請稍候';
    this.formSwitch = 'disabled';
    r = await this.submit(this.s);
    if (r == 'true') {
      this.action = 'verify';
      this.verifySno = this.s.sno!;
      this.message = '';
    }
  }

  async detect(s_detect: Student, spwdc: string) {
    let result: string = '';
    if (
      s_detect.sno == '' ||
      s_detect.spwd == '' ||
      s_detect.sname == '' ||
      s_detect.ssex == -1 ||
      s_detect.smail == '' ||
      s_detect.sid == ''
    ) {
      return 'somethingEmpty';
    }
    if (s_detect.spwd != spwdc) {
      return 'passwordNotMatch';
    }
    if (s_detect.spwd.length < 6) {
      return 'passwordTooShort';
    }
    if (!s_detect.smail?.includes('@')) {
      return 'mailFormatError';
    }
    if (
      parseInt(s_detect.sbday!.substring(0, 4)) < 1950 ||
      parseInt(s_detect.sbday!.substring(0, 4)) > 2010 ||
      s_detect.sbday == ''
    ) {
      return 'BdayError';
    }
    let url = 'http://127.0.0.1:8080/registerCheck';
    let body = {
      sno: s_detect.sno,
      spwd: s_detect.spwd,
      sname: s_detect.sname,
      ssex: s_detect.ssex,
      sbday: s_detect.sbday,
      smail: s_detect.smail,
      sid: s_detect.sid,
    };
    let options = {
      observe: 'response' as 'response',
      responseType: 'text',
    };
    let r = await lastValueFrom(
      this.http.post(url, body, { observe: 'response', responseType: 'text' })
    );
    return r.body;
  }

  async submit(student: Student) {
    let url = 'http://127.0.0.1:8080/register_ajax';
    let body = {
      sno: student.sno,
      sname: student.sname,
      spwd: student.spwd,
      ssex: student.ssex,
      sbday: student.sbday,
      smail: student.smail,
      sid: student.sid,
    };
    let r = await lastValueFrom(
      this.http.post(url, body, { observe: 'response', responseType: 'text' })
    );
    return r.body;
  }

  // async checkSnoId(url: any, body: any) {
  //   let result: HttpResponse<string>;
  //   //  this.http
  //   //   .post(url, body, { observe: 'response', responseType: 'text' })
  //   //   .subscribe((data) => {
  //   //     result = data;
  //   //     this.log(data.body);
  //   //     return data.body;
  //   //   });
  //   r = await lastValueFrom(
  //     this.http.post(url, body, { observe: 'response', responseType: 'text' })
  //   );
  //   return result;
  // }

  sex_select(ssex: number) {
    this.ssex_receive = ssex;
  }

  async doVerify(verifySno: string) {
    let url = 'http://127.0.0.1:8080/verify_ajax';
    let body = {
      sno: verifySno,
      sid: this.verify?.nativeElement.value,
    };
    let r = await lastValueFrom(
      this.http.post(url, body, { observe: 'response', responseType: 'text' })
    );
    if (r.body != 'false' && r.body != null && r.body != '') {
      if(r.body=='alredyActive'){
        this.message='帳號已啟用，不須再啟用';
        return;
      }
      this.cookie.set('username', r.body, 7);
      location.href = 'main';
    } else {
      this.message = '驗證失敗，請重新驗證或至首頁重寄驗證信';
    }
  }
}
