import { Component, OnInit} from '@angular/core';
import { OidcSecurityService } from 'angular-auth-oidc-client';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

constructor(public oidcSecurityService: OidcSecurityService) {}


  title = 'youtube-clone-ui';

  ngOnInit() {
      this.oidcSecurityService.checkAuth()
      .subscribe(({ isAuthenticated }) => {
            console.log("App is authenticated", isAuthenticated)
      });
    }
}
