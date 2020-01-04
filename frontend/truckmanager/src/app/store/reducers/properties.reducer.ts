import { PropertiesState } from "../models/properties-state.model";
import { PropertiesAction, PropertiesActionTypes } from '../actions/properties.actions';

const initialState: PropertiesState = {
    applications: [],
    classifications: [],
    colors: [],
    fuelTypes: [],
    loading: false,
    error: undefined
}

export function PropertiesReducer(state: PropertiesState = initialState, action: PropertiesAction) {

    switch (action.type) {
        case PropertiesActionTypes.LOAD_APPLICATIONS:
        case PropertiesActionTypes.LOAD_CLASSIFICATIONS:
        case PropertiesActionTypes.LOAD_COLORS:
        case PropertiesActionTypes.LOAD_FUEL_TYPES:
            return { ...state, loading: true };

        case PropertiesActionTypes.LOAD_APPLICATIONS_SUCCESS:
            return { ...state, loading: false, applications: action.payload };
        case PropertiesActionTypes.LOAD_CLASSIFICATIONS_SUCCESS:
            return { ...state, loading: false, classifications: action.payload };
        case PropertiesActionTypes.LOAD_COLORS_SUCCESS:
            return { ...state, loading: false, colors: action.payload };
        case PropertiesActionTypes.LOAD_FUEL_TYPES_SUCCESS:
            return { ...state, loading: false, fuelTypes: action.payload };

        case PropertiesActionTypes.LOAD_APPLICATIONS_FAILURE:
        case PropertiesActionTypes.LOAD_CLASSIFICATIONS_FAILURE:
        case PropertiesActionTypes.LOAD_COLORS_FAILURE:
        case PropertiesActionTypes.LOAD_FUEL_TYPES_FAILURE:
            return { ...state, error: action.payload };
        
        default:
            return state;
    }
}