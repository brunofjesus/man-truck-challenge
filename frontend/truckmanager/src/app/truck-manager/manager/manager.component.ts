import { Component, OnInit } from '@angular/core';
import { AppState } from 'src/app/store/models/app-state.model';
import { Store } from '@ngrx/store';
import { LoadTrucksAction } from 'src/app/store/actions/truck.actions';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss']
})
export class ManagerComponent implements OnInit {

  constructor(
    private store: Store<AppState>,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.store.dispatch(new LoadTrucksAction());
  }

  gridClicked(id: number) {
    this.router.navigate([id], {relativeTo: this.route});
  }
}
