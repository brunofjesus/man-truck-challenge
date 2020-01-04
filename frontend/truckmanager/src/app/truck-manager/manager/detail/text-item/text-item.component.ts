import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-text-item',
  templateUrl: './text-item.component.html',
  styleUrls: ['./text-item.component.scss']
})
export class TextItemComponent implements OnInit {

  @Input()
  label: string;

  @Input()
  value: string;

  constructor() { }

  ngOnInit() {
  }

}
