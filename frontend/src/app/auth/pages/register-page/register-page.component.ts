import {Component, inject, OnInit} from '@angular/core';
import {ThemeService} from "../../../services/theme.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {MessageService} from "primeng/api";


@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent  implements OnInit  {

  //temas
  themeService: ThemeService = inject(ThemeService);
  selectedTheme: string = 'dark';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.themeService.setTheme(this.selectedTheme);
  }


  register(registerForm: NgForm) {
    if (registerForm.invalid) {
      return;
    }

    const { email, password } = registerForm.value;

    this.authService.register(email, password).subscribe(
      response => {
        if (response === 'User Added Successfully') {
          alert('Usuario creado correctamente');
          this.router.navigate(['/auth/login']);
        }
      },
      error => {
        console.error('Error al registrar usuario:', error);
      }
    );
  }


  passwordsMatch(password: string, confirmPassword: string): boolean {
    return password === confirmPassword;
  }


}
