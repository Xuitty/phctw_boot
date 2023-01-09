import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Student } from '../bean/student';
import { CookieService } from 'ngx-cookie-service';

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
  ssex_receive?: number = 0;
  message?: string;
  s: Student = new Student();

  doRegister() {
    let s_detect: Student = new Student();
    s_detect.sno = this.sno_native?.nativeElement.value;
    s_detect.spwd = this.spwd_native?.nativeElement.value;
    let spwdc = this.spwdc_native?.nativeElement.value;
    s_detect.sname = this.sname_native?.nativeElement.value;
    s_detect.ssex = this.ssex_native?.nativeElement.value;
    s_detect.sbday = this.sbday_native?.nativeElement.value;
    s_detect.smail = this.smail_native?.nativeElement.value;
    s_detect.sid = this.sid_native?.nativeElement.value;

    this.log(s_detect);
    let r: string = this.detect(s_detect, spwdc);

    this.s.sno = s_detect.sno;
    this.s.spwd = s_detect.spwd;
    this.s.sname = s_detect.sname;
    this.s.ssex = s_detect.ssex;
    this.s.sbday = s_detect.sbday;
    this.s.smail = s_detect.smail;
    this.s.sid = s_detect.sid;
    this.message = 'register';
  }

  detect(s_detect: Student, spwdc: string) {
    let url = 'http://127.0.0.1:8080/registerCheck';
    let body = {
      sno: '123',
    };
    this.http.post<string>(url, body).subscribe((data) => {
      this.log(data);
      return;
    });
    if (s_detect.spwd != spwdc) {
      this.message = '密碼不一致';
      return 'password';
    }
    return 'pass';
  }

  sex_select(ssex: number) {
    this.ssex_receive = ssex;
  }
}
