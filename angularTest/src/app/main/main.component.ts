import { Component, OnInit, DoCheck } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Student } from '../bean/student';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  constructor(private http: HttpClient, private cookie: CookieService) {}
  log = console.log;
  status: boolean = false;
  student: Student = new Student();
  ngOnInit(): void {
    let cookie_send = this.cookie.get('username');
    this.log('username=' + cookie_send);
    if (cookie_send == null || cookie_send == '') {
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
      this.status = true;
      // this.log(this.student);
    });
  }
  doLogout() {
    this.cookie.deleteAll();
    location.href = '/';
  }

  sno?: string;
  sname?: string;
  smail?: string;
  sid?: string;
  sbday?: string;
  ssex?: string;
  message?: string;
}
