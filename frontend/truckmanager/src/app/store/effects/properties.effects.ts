import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { ApplicationsService, ClassificationsService, ColorsService, FuelTypesService } from 'src/swagger';
import { LoadApplicationsAction, PropertiesActionTypes, LoadApplicationsSuccessAction, LoadApplicationsFailureAction, LoadClassificationsAction, LoadClassificationsSuccessAction, LoadClassificationsFailureAction, LoadColorsAction, LoadColorsSuccessAction, LoadColorsFailureAction, LoadFuelTypesAction, LoadFuelTypesFailureAction, LoadFuelTypesSuccessAction } from '../actions/properties.actions';
import { mergeMap, catchError, map } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class PropertiesEffects {

    constructor(
        private actions$: Actions,
        private applicationsService: ApplicationsService,
        private classificationsService: ClassificationsService,
        private colorsService: ColorsService,
        private fuelTypesService: FuelTypesService
    ) { }

    @Effect() loadApplications$ = this.actions$.pipe(
        ofType<LoadApplicationsAction>(PropertiesActionTypes.LOAD_APPLICATIONS),
        mergeMap(
            () => this.applicationsService.listApplications()
                .pipe(
                    map(data => new LoadApplicationsSuccessAction(data)),
                    catchError(e => of(new LoadApplicationsFailureAction(e)))
                )
        )
    )

    @Effect() loadClassifications$ = this.actions$.pipe(
        ofType<LoadClassificationsAction>(PropertiesActionTypes.LOAD_CLASSIFICATIONS),
        mergeMap(
            () => this.classificationsService.listClassifications()
                .pipe(
                    map(data => new LoadClassificationsSuccessAction(data)),
                    catchError(e => of(new LoadClassificationsFailureAction(e)))
                )
        )
    )

    @Effect() loadColors$ = this.actions$.pipe(
        ofType<LoadColorsAction>(PropertiesActionTypes.LOAD_CLASSIFICATIONS),
        mergeMap(
            () => this.colorsService.listColors()
                .pipe(
                    map(data => new LoadColorsSuccessAction(data)),
                    catchError(e => of(new LoadColorsFailureAction(e)))
                )
        )
    )

    @Effect() loadFuelTypes$ = this.actions$.pipe(
        ofType<LoadFuelTypesAction>(PropertiesActionTypes.LOAD_FUEL_TYPES),
        mergeMap(
            () => this.fuelTypesService.listFuelTypes()
                .pipe(
                    map(data => new LoadFuelTypesSuccessAction(data)),
                    catchError(e => of(new LoadFuelTypesFailureAction(e)))
                )
        )
    )
}
