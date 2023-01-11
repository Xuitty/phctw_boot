import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
//
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { TitleSenderService } from './service/title-sender.service';
import { MainComponent } from './main/main.component';
import { DataService } from './service/data.service';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    IndexComponent,
    LoginComponent,
    MainComponent,
    RegisterComponent,
  ],
  imports: [BrowserModule, HttpClientModule, AppRoutingModule, FormsModule, NgbModule],
  providers: [TitleSenderService, DataService],
  bootstrap: [AppComponent],
})
export class AppModule {}
//
