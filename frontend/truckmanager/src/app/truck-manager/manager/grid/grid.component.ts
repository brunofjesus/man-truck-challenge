import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AppState } from 'src/app/store/models/app-state.model';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { TruckDTO } from 'src/swagger';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  trucks$: Observable<TruckDTO[]>;
  loading$: Observable<boolean>;

  @Output()
  clickEvent: EventEmitter<number> = new EventEmitter<number>();

  constructor(
    private store: Store<AppState>
  ) { }

  ngOnInit() {
    this.trucks$ = this.store.select(store => store.truck.list);
    this.loading$ = this.store.select(store => store.truck.loading).pipe(delay(0));
  }

  click(id :number) {
    this.clickEvent.emit(id);
  }
}
