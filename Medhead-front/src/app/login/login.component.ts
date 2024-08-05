import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [FormsModule]
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor() {}

  onSubmit() {
    // Logique de connexion ici
    console.log('Username:', this.email);
    console.log('Password:', this.password);
  }
}