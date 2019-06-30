import { TemplateItem } from './../model/template-item.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.scss']
})
export class ItemsComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ItemsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: TemplateItem[]) { }

  ngOnInit() {
  }

}
