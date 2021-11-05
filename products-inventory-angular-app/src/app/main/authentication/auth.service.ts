import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CognitoUserPool } from 'amazon-cognito-identity-js';
import { EnvironmentService } from './../../shared/environments/environment.service';

@Injectable()
export class AuthService {
  cognitoUser: any = null;
  authToken!: string;
  // userName: string;
  cognitoUserPool: CognitoUserPool = null;
  awsSession: any;
  sessionTimer: any;
  refreshToken: any;
  currentUrl: any;
  signInUserSession: any =null;

  constructor(
    private readonly router: Router,
    private readonly envService: EnvironmentService
  ) {
    this.authToken = '123';
    // TODO: Commented out temporarily unless connected to user pool
    // this.initializeCognito();
    // this.getCurrentSession();
  }

  getAuthToken(): string {
    return this.authToken;
  }

  initializeCognito(): void {
    const poolData = {
      UserPoolId: this.envService.auth.UserPoolId,
      ClientId: this.envService.auth.ClientId // Your client id here
    };
    this.cognitoUserPool = new CognitoUserPool(poolData);
  }


  checkIDToken(idToken: any): void {
    if (idToken) {
      this.authToken = idToken;
    }
  }

  setSessionTimer(session: any): void {
    // here we prematurely call 2 minutes prior to actual expiry so that user gets the new tokens before current token gets expired
    const expirationTime = session.idToken.payload.exp - Number(String(Date.now()).slice(0, 10)) - 120;

    if (expirationTime > 0) {
      this.sessionTimer = setTimeout(() => {
        this.refreshSession();
      }, expirationTime * 1000);
    } else {
      this.refreshSession();
    }
  }

  loadCurrentSession(session: any, isSessionRefreshed = false): void {

    if (session) {
      const idToken = session.idToken.jwtToken;
      this.checkIDToken(idToken);
      this.refreshToken = session.getRefreshToken();
      // some more code

      if (!isSessionRefreshed) {
        this.navigate();
      }
    }
  }

  navigate(): void {
    const index = this.currentUrl.indexOf('/main');
    if (index >= 0) {
      this.router.navigateByUrl(this.currentUrl.slice(index));
    } else {
      this.router.navigateByUrl('/main/home');
    }
  }

  refreshSession(): void {
    this.cognitoUser.refreshSession(this.refreshToken, (error: any, session: any) => {
      if (error) {
        this.signOut();
      } else if (session) {
        this.awsSession = session;
        this.loadCurrentSession(session, true);
      }
    });
  }

  getCurrentSession(): void {
    this.cognitoUser = this.cognitoUserPool.getCurrentUser();
    if (this.cognitoUser) {
      this.loadExistingSession();
    }

  }

  loadExistingSession(): void {
    const thisRef = this;
    this.cognitoUser.getSession((error: any, session:any) => {
      thisRef.awsSession = session;
      if (error) {
        thisRef.signOut();
      }

      if (session && session.isValid()) {
        thisRef.loadCurrentSession(session);
      }

    });
  }

  canActivate(): boolean {
    // this.cognitoUser = this.cognitoUserPool.getCurrentUser();
    // if (this.cognitoUser && this.awsSession && this.awsSession.isValid()) {
    //   return true;
    // } else {
    //   this.router.navigateByUrl('/login');
    // }
    // return false;

    // This is set to always true for local testingpupose
    // Above code works once we link with userpool - uncomment it that time
    return true;
  }

  signOut(): void {
    clearInterval(this.sessionTimer);
    this.cognitoUser.signInUserSession = this.signInUserSession;
    this.cognitoUser.globalSignout(
      {
        onFailure: (error: any) => console.log('Logout error', error),
        onSuccess: (success: any) => console.log('Logout success', success)
      });
    this.router.navigateByUrl('/login');

  }

}
