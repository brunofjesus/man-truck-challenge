import { TruckAction, TruckActionTypes } from '../actions/truck.actions';
import { TruckState } from '../models/truck-state.model';

const initialState: TruckState = {
    truck: undefined,
    list: [],
    loading: false,
    error: undefined
}

export function TruckReducer(state: TruckState = initialState, action: TruckAction) {

    switch (action.type) {
        case TruckActionTypes.LOAD_TRUCKS:
        case TruckActionTypes.LOAD_TRUCK:
        case TruckActionTypes.ADD_TRUCK:
        case TruckActionTypes.EDIT_TRUCK:
        case TruckActionTypes.DELETE_TRUCK:
            return { ...state, loading: true };

        case TruckActionTypes.EDIT_TRUCK_SUCCESS:
        case TruckActionTypes.ADD_TRUCK_SUCCESS:
            return { ...state, loading: false };

        case TruckActionTypes.LOAD_TRUCKS_SUCCESS:
            return { ...state, list: action.payload, loading: false };

        case TruckActionTypes.LOAD_TRUCK_SUCCESS:
            return { ...state, truck: action.payload, loading: false };

        case TruckActionTypes.DELETE_TRUCK_SUCCESS:
            return {
                ...state,
                list: state.list.filter(item => item.id !== action.payload),
                loading: false
            };

        case TruckActionTypes.LOAD_TRUCKS_FAILURE:
        case TruckActionTypes.LOAD_TRUCK_FAILURE:
        case TruckActionTypes.ADD_TRUCK_FAILURE:
        case TruckActionTypes.EDIT_TRUCK_FAILURE:
        case TruckActionTypes.DELETE_TRUCK_FAILURE:
            return { ...state, error: action.payload };

        default:
            return state;
    }
}
