import {Inject, Injectable} from '@angular/core';
import {DOCUMENT} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  activeTheme: string = 'dark';

  private readonly LINK_ID: string = 'app-theme';

  constructor(@Inject(DOCUMENT) private document: Document) {}

  setTheme(theme: string) {
    let themeLink = this.document.getElementById('app-theme') as HTMLLinkElement;

    if (themeLink) {
      themeLink.href = theme + '.css'
    }
    this.activeTheme = theme;
  }
}
