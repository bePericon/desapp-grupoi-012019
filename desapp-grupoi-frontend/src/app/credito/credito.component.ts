import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-credito',
  templateUrl: './credito.component.html',
  styleUrls: ['./credito.component.scss']
})
export class CreditoComponent implements OnInit {


  @Input() prueba: string;
  
  estado: string
  restante: number
  cuotas: number
  monto: number

  constructor() { 
    //TODO: deshardcodear
    this.estado="Bien"
    this.restante=123
    this.cuotas=123
    this.monto=123
  
  }

  ngOnInit() {
  }


  pedirCredito(){
    alert("implementar pedir credito")
  }


}
