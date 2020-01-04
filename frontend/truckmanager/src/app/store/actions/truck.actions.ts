import { Action } from '@ngrx/store';
import { TruckDTO } from 'src/swagger';

export enum TruckActionTypes {
    LOAD_TRUCKS = '[TRUCK] Load Trucks',
    LOAD_TRUCKS_SUCCESS = '[TRUCK] Load Trucks Success',
    LOAD_TRUCKS_FAILURE = '[TRUCK] Load Trucks Failure',

    LOAD_TRUCK = '[TRUCK] Load Truck',
    LOAD_TRUCK_SUCCESS = '[TRUCK] Load Truck Success',
    LOAD_TRUCK_FAILURE = '[TRUCK] Load Truck Failure',

    ADD_TRUCK = '[TRUCK] Add Truck',
    ADD_TRUCK_SUCCESS = '[TRUCK] Add Truck Success',
    ADD_TRUCK_FAILURE = '[TRUCK] Add Truck Failure',

    EDIT_TRUCK = '[TRUCK] Edit Truck',
    EDIT_TRUCK_SUCCESS = '[TRUCK] Edit Truck Success',
    EDIT_TRUCK_FAILURE = '[TRUCK] Edit Truck Failure',

    DELETE_TRUCK = '[TRUCK] Delete Truck',
    DELETE_TRUCK_SUCCESS = '[TRUCK] Delete Truck Success',
    DELETE_TRUCK_FAILURE = '[TRUCK] Delete Truck Failure',
};

export class LoadTrucksAction implements Action {
    readonly type = TruckActionTypes.LOAD_TRUCKS;
}
export class LoadTrucksSuccessAction implements Action {
    readonly type = TruckActionTypes.LOAD_TRUCKS_SUCCESS;

    constructor(public payload: TruckDTO[]) { }
}
export class LoadTrucksFailureAction implements Action {
    readonly type = TruckActionTypes.LOAD_TRUCKS_FAILURE;

    constructor(public payload: string) { }
}

export class LoadTruckAction implements Action {
    readonly type = TruckActionTypes.LOAD_TRUCK;

    constructor(public payload: number) { }
}
export class LoadTruckSuccessAction implements Action {
    readonly type = TruckActionTypes.LOAD_TRUCK_SUCCESS;

    constructor(public payload: TruckDTO) { }
}
export class LoadTruckFailureAction implements Action {
    readonly type = TruckActionTypes.LOAD_TRUCK_FAILURE;

    constructor(public payload: string) { }
}

export class AddTruckAction implements Action {
    readonly type = TruckActionTypes.ADD_TRUCK;

    constructor(public payload: TruckDTO) { }
}
export class AddTruckSuccessAction implements Action {
    readonly type = TruckActionTypes.ADD_TRUCK_SUCCESS;
}
export class AddTruckFailureAction implements Action {
    readonly type = TruckActionTypes.ADD_TRUCK_FAILURE;

    constructor(public payload: string) { }
}

export class EditTruckAction implements Action {
    readonly type = TruckActionTypes.EDIT_TRUCK;

    constructor(public payload: TruckDTO) { }
}
export class EditTruckSuccessAction implements Action {
    readonly type = TruckActionTypes.EDIT_TRUCK_SUCCESS;
} export class EditTruckFailureAction implements Action {
    readonly type = TruckActionTypes.EDIT_TRUCK_FAILURE;

    constructor(public payload: string) { }
}

export class DeleteTruckAction implements Action {
    readonly type = TruckActionTypes.DELETE_TRUCK;

    constructor(public payload: number) { }
}
export class DeleteTruckSuccessAction implements Action {
    readonly type = TruckActionTypes.DELETE_TRUCK_SUCCESS;

    constructor(public payload: number) { }
}
export class DeleteTruckFailureAction implements Action {
    readonly type = TruckActionTypes.DELETE_TRUCK_FAILURE;

    constructor(public payload: string) { }
}


export type TruckAction =
    LoadTrucksAction |
    LoadTrucksSuccessAction |
    LoadTrucksFailureAction |

    LoadTruckAction |
    LoadTruckSuccessAction |
    LoadTruckFailureAction |

    AddTruckAction |
    AddTruckSuccessAction |
    AddTruckFailureAction |

    EditTruckAction |
    EditTruckSuccessAction |
    EditTruckFailureAction |

    DeleteTruckAction |
    DeleteTruckSuccessAction |
    DeleteTruckFailureAction; 