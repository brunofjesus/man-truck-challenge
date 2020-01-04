import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { Observable, merge } from 'rxjs';
import { AppState } from './store/models/app-state.model';
import { Store } from '@ngrx/store';
import { delay, tap } from 'rxjs/operators';
import { ModalDialogComponent } from './shared/components/modal-dialog/modal-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {

  loading$: Observable<boolean>;
  error$: Observable<Error>;

  @ViewChild("errorDialog", null)
  errorDialog: ModalDialogComponent;

  constructor(
    private store: Store<AppState>
  ) { }

  ngAfterViewInit(): void {
    this.error$ = merge(
      this.store.select(store => store.truck.error).pipe(delay(0)),
      this.store.select(store => store.properties.error).pipe(delay(0))
    ).pipe( tap(c => console.log(c)));

    this.loading$ = this.store.select(store => store.truck.loading).pipe(delay(0));

    this.error$.subscribe((error) => this.errorDialog.isOpen = error && !!error.message);
  }
}
