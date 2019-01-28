import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  user: string;
  password: string;
  error: string;

  constructor(private authService: AuthService, private router: Router) { }

  submit() {
    this.error = null;
    this.authService.login(this.user, this.password)
      .pipe(first())
      .subscribe(
        result => this.router.navigate(['profil']),
        err => this.error = 'Unbekannter User oder Passwort.'
      );
  }

}
