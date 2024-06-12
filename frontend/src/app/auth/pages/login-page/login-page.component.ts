import {Component, inject, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ThemeService} from "../../../services/theme.service";
import {AuthService} from "../../services/auth.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
})

export class LoginPageComponent implements OnInit {

  //temas
  themeService: ThemeService = inject(ThemeService);
  selectedTheme: string = 'dark';

  //atributos
  loginForm: FormGroup;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ){
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
    this.themeService.setTheme(this.selectedTheme);
  }


  onLogin() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password).subscribe({
        next: () => this.router.navigate(['/notes/list']),
        error: (err) => this.errorMessage = 'Invalid login credentials'
      });
    }
  }



}
