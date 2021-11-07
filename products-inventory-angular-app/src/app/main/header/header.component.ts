import { Component, OnInit } from '@angular/core';
import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isSignedIn: boolean;
  constructor(protected readonly authService: AuthService) { 
    this.isSignedIn = this.authService.isSignedIn;
  }

  signOut(): void {
    this.authService.signOut();
  }

}
