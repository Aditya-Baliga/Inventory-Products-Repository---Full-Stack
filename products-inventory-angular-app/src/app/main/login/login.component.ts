import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationDetails, CognitoUser } from 'amazon-cognito-identity-js';
import { MessageService } from 'primeng/api';

import { AuthService } from '../authentication/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  isSignInFailed = false;

  constructor(
    private readonly authService: AuthService,
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
        thisRef.isSignInFailed = false;
        thisRef.authService.awsSession = session;
        thisRef.authService.signInUserSession = thisRef.authService.cognitoUser.signInUserSession;
        thisRef.authService.loadCurrentSession(session);
      },

      onFailure: function (): void {
        console.log('Error adi')
        thisRef.isSignInFailed = true;
        thisRef.messageService.add({ severity: 'error', summary: 'Authentication Failed', detail: `Please retry with the right username and password` });
      },

      newPasswordRequired: function (userAttributes: any) {
        delete userAttributes.email_verified;
        delete userAttributes.phone_number_verified;
        thisRef.authService.cognitoUser.completeNewPasswordChallenge(authenticationData.password, userAttributes, this);
      }
    }

    this.authService.cognitoUser.authenticateUser(authenticationDetails, authenticateUserCallbacks);
  }

}