import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';


export const EnvServiceFactory = (): EnvironmentService => {
  const env = new EnvironmentService();

  const browserWindow = window || {};
  const browserWindowEnv  = browserWindow['env'] || {};


  for(const key in browserWindowEnv) {
    if(browserWindowEnv.hasOwnProperty(key)) {
      env[key] = window['env'][key];
    }
  }

  return env;
}

export const EnvServiceProvider = {
  provide: EnvironmentService,
  useFactory: EnvServiceFactory,
  deps: [] as any[]
}