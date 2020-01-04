import { Action } from '@ngrx/store';
import { ApplicationDTO, ColorDTO, ClassificationDTO, FuelTypeDTO } from 'src/swagger';

export enum PropertiesActionTypes {

    LOAD_APPLICATIONS = '[PROPERTIES] Load Applications',
    LOAD_APPLICATIONS_SUCCESS = '[PROPERTIES] Load Applications Success',
    LOAD_APPLICATIONS_FAILURE = '[PROPERTIES] Load Applications Failure',

    LOAD_CLASSIFICATIONS = '[PROPERTIES] Load Classifications',
    LOAD_CLASSIFICATIONS_SUCCESS = '[PROPERTIES] Load Classifications Success',
    LOAD_CLASSIFICATIONS_FAILURE = '[PROPERTIES] Load Classifications Failure',

    LOAD_COLORS = '[PROPERTIES] Load Colors',
    LOAD_COLORS_SUCCESS = '[PROPERTIES] Load Colors Success',
    LOAD_COLORS_FAILURE = '[PROPERTIES] Load Colors Failure',

    LOAD_FUEL_TYPES = '[PROPERTIES] Load FuelTypes',
    LOAD_FUEL_TYPES_SUCCESS = '[PROPERTIES] Load FuelTypes Success',
    LOAD_FUEL_TYPES_FAILURE = '[PROPERTIES] Load FuelTypes Failure',
}

export class LoadApplicationsAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_APPLICATIONS;
}
export class LoadApplicationsSuccessAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_APPLICATIONS_SUCCESS;

    constructor(public payload: ApplicationDTO[]) { }
}
export class LoadApplicationsFailureAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_APPLICATIONS_FAILURE;
    
    constructor(public payload: string) { }
}

export class LoadClassificationsAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_CLASSIFICATIONS;
}
export class LoadClassificationsSuccessAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_CLASSIFICATIONS_SUCCESS;

    constructor(public payload: ClassificationDTO[]) { }
}
export class LoadClassificationsFailureAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_CLASSIFICATIONS_FAILURE;
    
    constructor(public payload: string) { }
}

export class LoadColorsAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_COLORS;
}
export class LoadColorsSuccessAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_COLORS_SUCCESS;

    constructor(public payload: ColorDTO[]) { }
}
export class LoadColorsFailureAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_COLORS_FAILURE;
    
    constructor(public payload: string) { }
}

export class LoadFuelTypesAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_FUEL_TYPES;
}

export class LoadFuelTypesSuccessAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_FUEL_TYPES_SUCCESS;

    constructor(public payload: FuelTypeDTO[]) { }
}

export class LoadFuelTypesFailureAction implements Action {
    readonly type = PropertiesActionTypes.LOAD_FUEL_TYPES_FAILURE;
    
    constructor(public payload: string) { }
}


export type PropertiesAction = 
    LoadApplicationsAction |
    LoadApplicationsSuccessAction |
    LoadApplicationsFailureAction |

    LoadClassificationsAction |
    LoadClassificationsSuccessAction |
    LoadClassificationsFailureAction |

    LoadColorsAction |
    LoadColorsSuccessAction |
    LoadColorsFailureAction |

    LoadFuelTypesAction |
    LoadFuelTypesSuccessAction |
    LoadFuelTypesFailureAction