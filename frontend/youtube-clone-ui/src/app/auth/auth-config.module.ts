import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-dhzzu8rqupszbdhv.eu.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'NcpRv1DZmscoxqWbxpT1MaC8K4qd2ELm',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
