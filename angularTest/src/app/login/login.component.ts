import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Student } from '../bean/student';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  message: string = '';
  log = console.log;
  @ViewChild('sno') sno?: ElementRef;
  @ViewChild('spwd') spwd?: ElementRef;
  s: Student = new Student();
  name?: string = this.s.sname;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.log('Init');
  }

  functionTest() {
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
    let url = 'http://127.0.0.1:8080/test';

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
      location.href=('main');
    });
  }

  // test(s: Student) {
  //   this.log(this.s);
  // }

  doLogin() {
    this.log('login');

    //..todo
  }
  goRegister() {
    this.log('register');
    //..todo
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
