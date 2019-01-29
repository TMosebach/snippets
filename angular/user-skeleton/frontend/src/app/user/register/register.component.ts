import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registrierForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) { 
    this.registrierForm = 
      this.fb.group({
        kennung: [''],
        password: [''],
        repeatedPassword: [''],
        vorname: [''],
        nachname: ['']
      });
  }

  ngOnInit() {
  }

  onSubmit() {
    const user = this.registrierForm.value;
    this.authService
      .register(user.kennung, user.password, user.vorname, user.nachname)
      .subscribe( result =>
        this.authService.login(user.kennung, user.password)
          .subscribe( result => this.router.navigate(['profil']))
      );
  }
}
