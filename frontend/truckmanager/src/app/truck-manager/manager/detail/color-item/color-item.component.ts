import { Component, OnInit, Input } from '@angular/core';
import { ColorDTO } from 'src/swagger';

@Component({
  selector: 'app-color-item',
  templateUrl: './color-item.component.html',
  styleUrls: ['./color-item.component.scss']
})
export class ColorItemComponent implements OnInit {

  @Input()
  label: string;

  @Input()
  colors: ColorDTO[];

  constructor() { }

  ngOnInit() {
  }

}
