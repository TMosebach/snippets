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
    this.authService
      .register(this.registrierForm.value.kennung, this.registrierForm.value.password, this.registrierForm.value.vorname, this.registrierForm.value.nachname);
    this.router.navigate(['login']);
  }
}
