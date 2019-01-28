import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile.service';
import { Profile } from '../profile';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  profile: Profile;

  constructor(private profileService: ProfileService) { }

  ngOnInit() {
    this.profileService.getProfile(1).subscribe(p => this.profile = p);
  }

}
