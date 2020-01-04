import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClarityModule } from '@clr/angular';
import { PluckPipe } from './pipes/pluck.pipe';
import { JoinPipe } from './pipes/join.pipe';
import { ModalDialogComponent } from './components/modal-dialog/modal-dialog.component';
import { SplitPipe } from './pipes/split.pipe';



@NgModule({
  declarations: [PluckPipe, JoinPipe, SplitPipe, ModalDialogComponent],
  imports: [
    CommonModule,
    ClarityModule
  ],
  exports: [
    ClarityModule,
    PluckPipe,
    JoinPipe,
    SplitPipe,
    ModalDialogComponent
  ]
})
export class SharedModule { }
