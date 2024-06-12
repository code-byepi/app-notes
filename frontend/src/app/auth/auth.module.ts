import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import {DividerModule} from "primeng/divider";
import {ToastModule} from "primeng/toast";
import {CardModule} from "primeng/card";
import {Button, ButtonDirective} from "primeng/button";
import {CheckboxModule} from "primeng/checkbox";
import {StyleClassModule} from 'primeng/styleclass';
import {Ripple} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MessageModule} from "primeng/message";
import {MessageService} from "primeng/api";


@NgModule({
  declarations: [
    LayoutPageComponent,
    LoginPageComponent,
    RegisterPageComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    DividerModule,
    ToastModule,
    CardModule,
    Button,
    StyleClassModule,
    CheckboxModule,
    Ripple,
    ButtonDirective,
    InputTextModule,
    FormsModule,
    ReactiveFormsModule,
    MessageModule

  ]
})
export class AuthModule { }
