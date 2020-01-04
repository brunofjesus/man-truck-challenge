import { Injectable } from "@angular/core";
import { TrucksService, TruckDTO } from 'src/swagger';

import { Actions, Effect, ofType } from '@ngrx/effects'
import { mergeMap, catchError, map } from 'rxjs/operators'
import {
    LoadTrucksAction, TruckActionTypes, LoadTrucksSuccessAction, LoadTrucksFailureAction, LoadTruckAction, LoadTruckSuccessAction,
    LoadTruckFailureAction,
    AddTruckAction,
    AddTruckSuccessAction,
    AddTruckFailureAction,
    EditTruckAction,
    EditTruckSuccessAction,
    EditTruckFailureAction,
    DeleteTruckAction,
    DeleteTruckSuccessAction,
    DeleteTruckFailureAction
} from '../actions/truck.actions';
import { of } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class TruckEffects {

    constructor(
        private actions$: Actions,
        private trucksService: TrucksService
    ) { }

    @Effect() loadTrucks$ = this.actions$.pipe(
        ofType<LoadTrucksAction>(TruckActionTypes.LOAD_TRUCKS),
        mergeMap(
            () => this.trucksService.listTrucks()
                .pipe(
                    map(data => new LoadTrucksSuccessAction(data)),
                    catchError(e => of(new LoadTrucksFailureAction(e)))
                )
        )
    )

    @Effect() loadTruck$ = this.actions$.pipe(
        ofType<LoadTruckAction>(TruckActionTypes.LOAD_TRUCK),
        mergeMap(
            (data) => this.trucksService.getTruckById(data.payload)
                .pipe(
                    map((truck: TruckDTO) => new LoadTruckSuccessAction(truck)),
                    catchError(e => of(new LoadTruckFailureAction(e)))
                )
        )
    )

    @Effect() addTruck$ = this.actions$.pipe(
        ofType<AddTruckAction>(TruckActionTypes.ADD_TRUCK),
        mergeMap(
            (data) => this.trucksService.createTruck(data.payload)
                .pipe(
                    map(() => new AddTruckSuccessAction()),
                    catchError(e => of(new AddTruckFailureAction(e)))
                )
        )
    )

    @Effect() editTruck$ = this.actions$.pipe(
        ofType<EditTruckAction>(TruckActionTypes.EDIT_TRUCK),
        mergeMap(
            (data) => this.trucksService.updateTruck(data.payload.id, data.payload)
                .pipe(
                    map((truck: TruckDTO) => new EditTruckSuccessAction()),
                    catchError(e => of(new EditTruckFailureAction(e)))
                )
        )
    )

    @Effect() deleteTruck$ = this.actions$.pipe(
        ofType<DeleteTruckAction>(TruckActionTypes.DELETE_TRUCK),
        mergeMap(
            (data) => this.trucksService.deleteTruck(data.payload)
                .pipe(
                    map(() => new DeleteTruckSuccessAction(data.payload)),
                    catchError(e => of(new DeleteTruckFailureAction(e)))
                )
        )
    )

}