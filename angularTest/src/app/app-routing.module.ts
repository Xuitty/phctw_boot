import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './not-found/not-found.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { RegisterComponent } from './register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';

const routes: Routes = [
  { path: '', component: IndexComponent, data: { animation: 'indexPage' } },
  {
    path: 'login',
    component: LoginComponent,
    data: { animation: 'loginPage' },
  },
  { path: 'main', component: MainComponent, data: { animation: 'mainPage' } },
  {
    path: 'register',
    component: RegisterComponent,
    data: { animation: 'registerPage' },
  },
  {
    path: '**',
    component: NotFoundComponent,
    pathMatch: 'full',
    data: { animation: '404Page' },
  },
  // { path: '**', redirectTo: '/404' },
];

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
