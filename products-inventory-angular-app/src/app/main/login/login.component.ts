import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationDetails, CognitoUser } from 'amazon-cognito-identity-js';
import { MessageService } from 'primeng/api';

import { EnvironmentService } from 'src/app/shared/environments/environment.service';
import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly envService: EnvironmentService,
    private readonly messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'userName': new FormControl(null, [Validators.required]),
      'password': new FormControl(null, [Validators.required])
    });
  }

  authenticateUser(authenticationData: any): void {
    const authenticationDetails = new AuthenticationDetails({
      Username: authenticationData.userName,
      Password: authenticationData.password
    });

    this.authService.cognitoUser = new CognitoUser({
      Username: authenticationData.userName,
      Pool: this.authService.cognitoUserPool

    });

    const thisRef = this;
    const authenticateUserCallbacks = {
      onSuccess: function (session: any): void {
        thisRef.authService.awsSession = session;
        thisRef.authService.signInUserSession = thisRef.authService.cognitoUser.signInUserSession;
        thisRef.authService.loadCurrentSession(session);
        thisRef.authService.isPasswordChanged = false;

      },

      onFailure: function (): void {
        thisRef.messageService.add({ severity: 'error', summary: 'Authentication Failed', detail: `Please retry with the right username and password` });
      }
    }

    this.authService.cognitoUser.authenticateUser(authenticationDetails, authenticateUserCallbacks);
  }

}