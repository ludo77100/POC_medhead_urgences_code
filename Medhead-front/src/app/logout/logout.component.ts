import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
  standalone: true,
  imports: [RouterModule, CommonModule]
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    // Logique pour déconnecter l'utilisateur, supprimer le token JWT
    // localStorage.removeItem('token');

    // Rediriger après 10 secondes
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 10000);
  }

}