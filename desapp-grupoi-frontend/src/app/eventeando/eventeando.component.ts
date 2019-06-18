import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-eventeando',
  templateUrl: './eventeando.component.html',
  styleUrls: ['./eventeando.component.scss']
})
export class EventeandoComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  irACrearEvento(){
    this.router.navigate(['eventeando/crear-evento']);
  }

}
