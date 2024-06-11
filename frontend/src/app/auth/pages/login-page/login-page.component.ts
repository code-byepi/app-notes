import {Component, inject, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ThemeService} from "../../../services/theme.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
})

export class LoginPageComponent implements OnInit{
  //temas
  themeService: ThemeService = inject(ThemeService);
  selectedTheme: string = 'dark';

  //atributos
  email: string = '';
  password: string = '';
  errorMessage: string = '';


  constructor(
    private authService: AuthService,
    private router: Router
  ){}


  ngOnInit(): void {
    this.themeService.setTheme(this.selectedTheme);
  }

  onLogin(): void {
    this.authService.login(this.email, this.password)
      .subscribe(
        () => {
          // Inicio de sesión exitoso, redirigir a la página principal
          this.router.navigate(['/']);
        },
        error => {
          // Manejo de errores
          this.errorMessage = error.message || 'Error en la autenticación.';
        }
      );
  }


}
