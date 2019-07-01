import { EventoData } from './../model/evento-data.model';
import { TemplateItem } from './../model/template-item.model';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.scss']
})
export class ItemsComponent implements OnInit {

  original: TemplateItem[];

  displayedColumns: string[] = ['item.nombreItem','item.costo.monto', 'item.personasPorUnidad', 'cantidad', 'seleccionar'];
  dataSource: MatTableDataSource<TemplateItem>;
  selection: SelectionModel<TemplateItem>;

  constructor(
    public dialogRef: MatDialogRef<ItemsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EventoData) { }

  ngOnInit() {
    this.original = this.data.items.map(t => new TemplateItem(t.id, t.item, t.cantidad));

    if(!this.data.mostrarSeleccionar)
      this.displayedColumns = this.displayedColumns.filter(s => s != 'seleccionar');

    this.dataSource = new MatTableDataSource<TemplateItem>(this.data.items);
    this.selection = new SelectionModel<TemplateItem>(true, []);
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
  }

  aceptar(){
    this.data.items = this.selection.selected;
    // this.dialogRef.close();
  }

}
