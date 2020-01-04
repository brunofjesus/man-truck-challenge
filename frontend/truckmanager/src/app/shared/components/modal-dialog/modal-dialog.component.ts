import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modal-dialog',
  templateUrl: './modal-dialog.component.html',
  styleUrls: ['./modal-dialog.component.scss']
})
export class ModalDialogComponent implements OnInit {

  isOpen: boolean = false;
  @Input() title: string = "";
  @Input() text: string = "";
  @Input() cancelButton :boolean = true;
  @Input() labelCancel :string = "Cancel";
  @Input() labelOk :string = "Ok";
  
  @Output() submit: EventEmitter<boolean> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  onClick(isOk: boolean) {
    this.submit.emit(isOk);
  }
}
