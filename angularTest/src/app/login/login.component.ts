import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Student } from '../bean/student';
import { CookieService } from 'ngx-cookie-service';
import { lastValueFrom } from 'rxjs/internal/lastValueFrom';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  message: string = '';
  action: string = 'login';
  smailSwitch: string = '';
  verifySwitch: string = '';
  resendButtonSwitch: string = '';
  verifyButtonSwitch: string = '';
  i: number = 0;
  // cookie:string ='';
  log = console.log;
  @ViewChild('sno') sno?: ElementRef;
  @ViewChild('spwd') spwd?: ElementRef;

  @ViewChild('resendSno') resendSno?: ElementRef;
  @ViewChild('resendSmail') resendSmail?: ElementRef;
  @ViewChild('resendVerify') resendVerify?: ElementRef;

  @ViewChild('forgetPasswordSno') forgetPasswordSno?: ElementRef;
  @ViewChild('forgetPasswordSmail') forgetPasswordSmail?: ElementRef;

  s: Student = new Student();
  name?: string = this.s.sname;

  constructor(private http: HttpClient, private cookie: CookieService) {}

  onKeyDown($event: Event) {
    this.doLogin();
    this.log('enter triggered');
  }

  async doLogin() {
    this.paramInit();
    var sno: string = this.sno?.nativeElement.value;
    var spwd: string = this.spwd?.nativeElement.value;
    let body = {
      sno: sno,
      spwd: spwd,
    };
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      responseType: 'json',
    });
    let url = 'http://127.0.0.1:8080/login_ajax';

    let options = { observe: 'response' as 'response', headers: headers };

    // this.http.post<Student>(url, body).subscribe((data) => {
    //   this.log(data);
    //   if (data.sid == null) {
    //     this.message = '帳號/密碼錯誤';
    //     return;
    //   } else if (data.active == 0) {
    //     this.message = '帳號未驗證';
    //     return;
    //   }
    //   // this.message = '歡迎 ' + data.sname;
    //   let studentCookie = data.cookie;
    //   this.cookie.set('username', studentCookie!, 7);
    //   this.log('end');
    //   location.href = 'main';
    // });
    let data_receive: Student;
    let r = await lastValueFrom(this.http.post<Student>(url, body));

    if (r.sid == null) {
      this.message = '帳號/密碼錯誤';
      return;
    } else if (r.active == 0) {
      this.message = '帳號未驗證';
      return;
    }
    // this.message = '歡迎 ' + data.sname;
    let studentCookie = r.cookie;
    this.cookie.set('username', studentCookie!, 7);
    this.log('end');
    location.href = 'main';
  }

  // test(s: Student) {
  //   this.log(this.s);
  // }

  // doLogin() {
  //   this.log('login');

  //   //..todo
  // }
  goRegister() {
    location.href = 'register';
  }
  goResend() {
    this.action = 'resend';
    this.paramInit();
  }

  detectResend() {
    if (this.i == 0) {
      let sno: string = this.resendSno?.nativeElement.value;
      let smail: string = this.resendSmail?.nativeElement.value;
      let verify: string = this.resendVerify?.nativeElement.value;
      if (smail.length == 0 && verify.length == 0) {
        this.paramInit();
        return;
      }
      if (sno.length > 0 && smail.length > 0) {
        this.verifySwitch = 'disabled';
        if (smail.length > 6 && smail.includes('@')) {
          this.resendButtonSwitch = '';
        } else {
          this.resendButtonSwitch = 'none';
        }
      } else if (sno.length > 0 && verify.length > 0) {
        this.smailSwitch = 'disabled';
        if (verify.length == 6) {
          this.verifyButtonSwitch = '';
        } else {
          this.verifyButtonSwitch = 'none';
        }
      }
    }
  }

  async doResend() {
    this.i = 1;
    this.resendButtonSwitch = 'none';
    this.message = '資料處理中請稍候...';
    let resendSno = this.resendSno?.nativeElement.value;
    let resendSmail = this.resendSmail?.nativeElement.value;
    let url = 'http://127.0.0.1:8080/resend_ajax';
    let body = {
      sno: resendSno,
      smail: resendSmail,
    };
    let r = await lastValueFrom(
      this.http.post(url, body, { observe: 'response', responseType: 'text' })
    );
    this.log(r.body);
    if (r.body != null && r.body != '') {
      if (r.body == 'wrongSmail') {
        this.message = '信箱錯誤，請重新確認';
        this.i = 0;
        return;
      } else if (r.body == 'snoNotExist') {
        this.message = '帳號不存在';
        this.i = 0;
        return;
      } else if (r.body == 'alredyActive') {
        this.message = '帳號已啟用，不須再驗證';
        this.i = 0;
        return;
      } else if (r.body == 'true') {
        this.message = '發送成功，請至信箱收信';
        this.i = 0;
        return;
      }
    }
  }

  async doVerify() {
    this.i = 1;
    this.message = '處理中請稍候...';
    this.verifyButtonSwitch = 'none';
    let verifySno = this.resendSno?.nativeElement.value;
    let verify = this.resendVerify?.nativeElement.value;
    let url = 'http://127.0.0.1:8080/verify_ajax';
    let body = {
      sno: verifySno,
      sid: verify,
    };
    let r = await lastValueFrom(
      this.http.post(url, body, { observe: 'response', responseType: 'text' })
    );
    if (r.body != 'false' && r.body != null && r.body != '') {
      if (r.body == 'snoNotExist') {
        this.message = '帳號不存在';
        this.i = 0;
        return;
      }
      if (r.body == 'alredyActive') {
        this.message = '帳號已啟用，不須再啟用';
        this.i = 0;
        return;
      }
      let t: number = 5;
      let counter = setInterval(() => {
        t = t - 1;
        this.message = '驗證成功，' + t + '秒後將跳轉至個人頁面';
        if (t == 0) {
          clearInterval(counter);
          this.cookie.set('username', r.body!, 7);
          location.href = '/main';
        }
      }, 1000);
    } else {
      this.message = '驗證失敗，請重新驗證';
      this.i = 0;
      return;
    }
  }

  paramInit() {
    this.message = '';
    this.smailSwitch = '';
    this.verifySwitch = '';
    this.resendButtonSwitch = 'none';
    this.verifyButtonSwitch = 'none';
  }

  goForgetPassword() {
    this.action = 'forgetPassword';
    this.paramInit();
    this.verifyButtonSwitch = '';
    this.resendButtonSwitch = '';
  }

  async doForgetPassword() {
    this.verifyButtonSwitch = 'none';
    this.resendButtonSwitch = 'none';
    this.smailSwitch = 'disabled';
    this.verifySwitch = 'disabled';
    this.message = '資料處理中...';
    let forgetPasswordSno = this.forgetPasswordSno?.nativeElement.value;
    let forgetPasswordSmail = this.forgetPasswordSmail?.nativeElement.value;
    let url = 'http://127.0.0.1:8080/forgetPassword_ajax';
    let body = {
      sno: forgetPasswordSno,
      smail: forgetPasswordSmail,
    };
    let options = {
      observe: 'response' as 'response',
      responseType: 'text' as 'text',
    };
    let r = await lastValueFrom(this.http.post(url, body, options));
    if (r.body != 'false' && r.body != null && r.body != '') {
      if (r.body == 'snoNotExist') {
        this.message = '帳號不存在';
        this.verifyButtonSwitch = '';
        this.resendButtonSwitch = '';
        this.smailSwitch = '';
        this.verifySwitch = '';
        return;
      }
      if (r.body == 'wrongSmail') {
        this.message = '信箱錯誤，請重新確認';
        this.verifyButtonSwitch = '';
        this.resendButtonSwitch = '';
        this.smailSwitch = '';
        this.verifySwitch = '';
        return;
      }
      if (r.body == 'true') {
        let t: number = 5;
        let counter = setInterval(() => {
          t = t - 1;
          this.message =
            '新密碼已經發送至信箱，' + t + '秒後將跳轉至首頁，請使用新密碼登入';
          if (t == 0) {
            clearInterval(counter);
            this.cookie.deleteAll();
            location.href = '/login;';
          }
        }, 1000);
        // this.message =
        //   '新密碼已經發送至信箱，五秒後將跳轉至首頁，請使用新密碼登入';
        // setTimeout(() => (location.href = '/login'), 3000);
      }
    }
  }
  goLogin() {
    location.href = '/login';
  }
}
