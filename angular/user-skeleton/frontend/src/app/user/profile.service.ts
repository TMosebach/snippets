import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from './profile';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  getProfile(userId: number): Observable<Profile> {
    return this.http.get<Profile>(`http://localhost:3000/api/1.0/profile/${userId}`);
  }
}
