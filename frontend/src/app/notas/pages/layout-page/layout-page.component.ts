import {Component, inject, OnInit} from '@angular/core';
import { ToolbarModule } from 'primeng/toolbar';
import {ThemeService} from "../../../services/theme.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../auth/services/auth.service";

@Component({
  selector: 'app-layout-page',
  templateUrl: './layout-page.component.html',
  styleUrls: ['./layout-page.component.css']
})
export class LayoutPageComponent implements OnInit {
  checked: boolean = true;
  selectedTheme: string = 'dark';
  themeService: ThemeService = inject(ThemeService);
  visible: boolean = false;
  isButtonVisible: boolean = true;

  constructor(private router: Router, private authService: AuthService,) {
  }

  public items = [
    { label: 'Listado', icon: 'pi pi-list', url: './list' },
    { label: 'Nueva Nota', icon: 'pi pi-plus', url: './new-note' },
    { label: 'Archivadas', icon: 'pi pi-inbox', url: './archived' },
  ];

  ngOnInit(): void {
    this.themeService.setTheme(this.selectedTheme);
  }

  onThemeChange(theme: string): void {
    this.selectedTheme = theme;
    this.themeService.setTheme(theme);
  }


  showDialog() {
    this.visible = true;
    this.isButtonVisible = false;
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/auth/login']);
  }
}
