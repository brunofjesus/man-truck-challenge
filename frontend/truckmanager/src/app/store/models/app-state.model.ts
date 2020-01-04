import { TruckState } from './truck-state.model';
import { PropertiesState } from './properties-state.model';

export interface AppState {
    readonly truck: TruckState;
    readonly properties: PropertiesState;
}