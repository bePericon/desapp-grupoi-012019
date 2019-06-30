import { MatDialog } from '@angular/material';
import { TemplateItem } from './../model/template-item.model';
import { ItemsComponent } from './../items/items.component';
import { UtilsService } from './../services/utils.service';
import { Component, OnInit, Input } from '@angular/core';
import { Evento } from '../model/evento.model';
import { Usuario } from '../model/usuario.model';
import { InvitadosComponent } from '../invitados/invitados.component';

@Component({
  selector: 'app-card-evento',
  templateUrl: './card-evento.component.html',
  styleUrls: ['./card-evento.component.scss']
})
export class CardEventoComponent implements OnInit {

  @Input() evento: Evento;

  constructor(
    private uService: UtilsService) { }

  ngOnInit() {
  }


  verItems(){
    var items: TemplateItem[] = this.evento.template.templateItems

    const dialogRef = this.uService.getDialog(ItemsComponent, items, "400px");

    dialogRef.afterClosed().subscribe(result => {
      console.log("Se cerro la info!");
    });
  }

  verAsistentes(){
    var asistentes: Usuario[] = this.evento.asistentes;

    const dialogRef = this.uService.getDialog(InvitadosComponent, asistentes, "400px");

    dialogRef.afterClosed().subscribe(result => {
      console.log("Se cerro la info!");
    });

  }
  

}
