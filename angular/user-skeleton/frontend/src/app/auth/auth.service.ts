import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(user: string, password: string): Observable<boolean> {
    return this.http
      .post<{ token: string}>('http://localhost:3000/api/1.0/auth', {user: user, password: password})
      .pipe(
        map(result => {
          localStorage.setItem('access_token', result.token);
          return true;
        })
      );
  }

  public register(kennung: string, password: string, vorname: string, nachname: string): void {
    this.http
      .post<boolean>('http://localhost:3000/api/1.0/register', {kennung: kennung, password: password, vorname: vorname, nachname: nachname})
      .subscribe(result => console.log('registriert.'));
  }

  public logout() {
    localStorage.removeItem('access_token');
  }

  public loggedIn() {
    return localStorage.getItem('access_token');
  }
}
