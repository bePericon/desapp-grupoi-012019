import { Component, OnInit } from '@angular/core';
import { EventoService } from '../evento.service';

@Component({
  selector: 'app-eventos',
  templateUrl: './eventos.component.html',
  styleUrls: ['./eventos.component.scss']
})
export class EventosComponent implements OnInit {

  variable: any

  constructor(private eventoSrv: EventoService) { }

  ngOnInit() {
  }

  getDatos() {
    // console.log(this.eventoSrv.getUsuarios());

    this.eventoSrv.getUsuarios().subscribe(res => {
      this.variable = res;
    })


  }

  // showConfig() {
  //   this.eventoSrv.getConfig()
  //     .subscribe((data: Config) => this.config = {
  //         heroesUrl: data['heroesUrl'],
  //         textfile:  data['textfile']
  //     });
  // }

}
