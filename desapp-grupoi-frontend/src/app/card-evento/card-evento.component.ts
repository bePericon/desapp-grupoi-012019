import { EventoService } from './../services/evento.service';
import { EventoData } from './../model/evento-data.model';
import { TemplateItem } from './../model/template-item.model';
import { ItemsComponent } from './../items/items.component';
import { UtilsService } from './../services/utils.service';
import { Component, OnInit, Input } from '@angular/core';
import { Evento } from '../model/evento.model';
import { Usuario } from '../model/usuario.model';
import { AsistentesComponent } from '../asistentes/asistentes.component';

@Component({
  selector: 'app-card-evento',
  templateUrl: './card-evento.component.html',
  styleUrls: ['./card-evento.component.scss']
})
export class CardEventoComponent implements OnInit {

  @Input() evento: Evento;

  constructor(
    private uService: UtilsService,
    private eventoService: EventoService) { }

  ngOnInit() {
  }

  mostrarSeleccionarItems(){
    return this.evento.modalidad.tipoModalidad == 'CANASTA';
  }

  verItems(){
    //Corroboramos si ya se eligieron items
    var itemComprado = this.evento.template.modalidad.itemsComprados.find(i => i.usuario.id == this.eventoService.getIdUsuario());
    var mostrarSel: boolean;
    if(itemComprado)
      mostrarSel = false;
    else
      mostrarSel = this.mostrarSeleccionarItems();
    
    var items = this.evento.template.templateItems;

    const dialogRef = this.uService.getDialog(ItemsComponent, new EventoData(mostrarSel, items), "600px");

    dialogRef.afterClosed().subscribe(result => {
      if(result && mostrarSel){
        var itemsElegidos: TemplateItem[] = (result as EventoData).items;
  
        var eventoData = {
          idEvento: this.evento.id,
          templateItems: itemsElegidos
        }

        console.log(eventoData);
        

        this.eventoService.setItemsElegidos(eventoData)
          .subscribe(res => {
            this.evento = res.result as Evento;
            console.log("Termine!");
            console.log(res.result);
            
          });
      }

      // itemsElegidos.forEach(elegido => {
      //   var indice = items.find((element, index, array) => {
      //     if(elegido.id == element.id)
      //       return index;
      //   });

      //   this.eventoService.
      // })
    });
  }

  verAsistentes(){
    var asistentes: Usuario[] = this.evento.asistentes;

    const dialogRef = this.uService.getDialog(AsistentesComponent, asistentes, "400px");

    dialogRef.afterClosed().subscribe(result => {
      console.log("Se cerro la info!");
    });

  }
  

}
