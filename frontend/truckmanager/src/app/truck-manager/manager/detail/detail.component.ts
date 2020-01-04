import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/store/models/app-state.model';
import { Observable } from 'rxjs';
import { TruckDTO } from 'src/swagger';
import { ModalDialogComponent } from 'src/app/shared/components/modal-dialog/modal-dialog.component';
import { tap } from 'rxjs/operators';
import { DeleteTruckAction, LoadTruckAction } from 'src/app/store/actions/truck.actions';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {

  truck$: Observable<TruckDTO>;
  truckId: number;

  @ViewChild("deleteDialog", null)
  deleteDialog: ModalDialogComponent;

  constructor(
    private store: Store<AppState>,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.truck$ = this.store.select(store => store.truck.truck);

    this.activatedRoute.params.subscribe((params: Params) => {
      if (params["id"]) {
        this.truckId = +params["id"];
        this.store.dispatch(new LoadTruckAction(this.truckId));
      }
    })
  }

  onClickDelete() {
    this.deleteDialog.isOpen = true;
  }

  onClickEdit(id :string) {
    this.router.navigate(["/manager",id,"edit"]);
  }

  onClickOk() {
    this.navigateToParent();
  }

  onDeleteTruck() {
    this.store.dispatch(new DeleteTruckAction(this.truckId));
    this.navigateToParent();
  }

  navigateToParent() {
    this.router.navigate(["../"], { relativeTo: this.activatedRoute });
  }
}
