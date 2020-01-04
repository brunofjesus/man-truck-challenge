import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/store/models/app-state.model';
import { Observable, Subscription } from 'rxjs';
import { PropertiesState } from 'src/app/store/models/properties-state.model';
import { LoadApplicationsAction, LoadClassificationsAction, LoadColorsAction, LoadFuelTypesAction } from 'src/app/store/actions/properties.actions';
import { take, filter } from 'rxjs/operators';
import { TruckDTO, ApplicationDTO, ColorDTO } from 'src/swagger';
import { AddTruckAction, TruckActionTypes, LoadTrucksAction, EditTruckAction } from 'src/app/store/actions/truck.actions';
import { Actions, ofType } from '@ngrx/effects';
import { FormModel } from './form.model';

@Component({
  selector: 'app-create-edit',
  templateUrl: './create-edit.component.html',
  styleUrls: ['./create-edit.component.scss']
})
export class CreateEditComponent implements OnInit, OnDestroy {

  properties$: Observable<PropertiesState>;

  saveSubscription: Subscription;

  formModel: FormModel;

  truckIdInEdition: number;
  truckVersionInEdition :Date;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private store: Store<AppState>,
    private actions$: Actions
  ) {
    this.formModel = {
      model: undefined,
      horsepower: undefined,
      displacement: undefined,
      fuelTypeId: undefined,
      classificationId: undefined,
      applications: [],
      colors: []
    }

    this.activatedRoute.params.subscribe(
      (params: Params) => {
        if (params['id']) {
          this.store.select(store => store.truck.truck)
            .pipe(filter((truck) => !!truck), take(1))
            .subscribe((truck: TruckDTO) => {

              this.truckIdInEdition = truck.id;
              this.truckVersionInEdition = truck.modifiedAt;

              this.formModel.model = truck.model;
              this.formModel.horsepower = truck.horsepower;
              this.formModel.displacement = truck.displacement;
              this.formModel.fuelTypeId = truck.fuelType ? truck.fuelType.id : undefined;
              this.formModel.classificationId = truck.classification ? truck.classification.id : undefined;

              let applications = [];
              if (truck.applications) {
                truck.applications.forEach((app) => applications[app.id] = true);
              }
              this.formModel.applications = applications;

              let colors = [];
              if (truck.colors) {
                truck.colors.forEach((col) => colors[col.id] = true);
              }
              this.formModel.colors = colors;
            })
        }
      }
    )
  }

  ngOnInit() {
    this.properties$ = this.store.select(store => store.properties);

    this.store.dispatch(new LoadApplicationsAction());
    this.store.dispatch(new LoadClassificationsAction());
    this.store.dispatch(new LoadColorsAction());
    this.store.dispatch(new LoadFuelTypesAction());

    this.saveSubscription = this.actions$.pipe(
      ofType(TruckActionTypes.ADD_TRUCK_SUCCESS, TruckActionTypes.EDIT_TRUCK_SUCCESS)).subscribe(
        () => {
          this.store.dispatch(new LoadTrucksAction());
          this.router.navigate(["/manager"])
        }
      );
  }

  onSubmit() {
    const applicationsDTO: ApplicationDTO[] = [];

    this.formModel.applications.forEach((val: boolean, idx: number) => {
      if (val) {
        applicationsDTO.push({ id: idx })
      }
    })

    const colorsDTO: ColorDTO[] = [];

    this.formModel.colors.forEach((val: boolean, idx: number) => {
      if (val) {
        colorsDTO.push({ id: idx })
      }
    })

    const truck: TruckDTO = {
      id: this.truckIdInEdition ? this.truckIdInEdition : -1,
      model: this.formModel.model,
      horsepower: this.formModel.horsepower,
      displacement: this.formModel.displacement,
      fuelType: this.formModel.fuelTypeId ? { id: this.formModel.fuelTypeId } : undefined,
      classification: this.formModel.classificationId ? { id: this.formModel.classificationId } : undefined,
      applications: applicationsDTO,
      colors: colorsDTO,
      modifiedAt: this.truckVersionInEdition ? this.truckVersionInEdition : undefined
    }

    this.store.dispatch(truck.id > -1 ? new EditTruckAction(truck) : new AddTruckAction(truck));
  }



  ngOnDestroy() {
    if (this.saveSubscription) {
      this.saveSubscription.unsubscribe();
    }
  }
}
