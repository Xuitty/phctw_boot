import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Student } from '../bean/student';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  message: string = '';
  // cookie:string ='';
  log = console.log;
  @ViewChild('sno') sno?: ElementRef;
  @ViewChild('spwd') spwd?: ElementRef;
  s: Student = new Student();
  name?: string = this.s.sname;

  constructor(private http: HttpClient, private cookie: CookieService) {}

  ngOnInit(): void {
    this.log('Init');
  }
  onKeyDown($event: Event) {
    this.doLogin();
    this.log('enter triggered');
  }

  doLogin() {
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

    this.http.post<Student>(url, body).subscribe((data) => {
      this.log(data);
      if (data.sid == null) {
        this.message = '帳號/密碼錯誤';
        return;
      } else if (data.active == 0) {
        this.message = '帳號未驗證';
        return;
      }
      // this.message = '歡迎 ' + data.sname;
      let studentCookie = data.cookie;
      this.cookie.set('username', studentCookie!, 7);
      this.log('end');
      location.href = 'main';
    });
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
  goForgetPassword() {
    this.log('forgetPassword');
    //..todo
  }
  goResend() {
    this.log('resend');
    //..todo
  }
}
