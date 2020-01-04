import { TruckDTO } from 'src/swagger';

export interface TruckState {
    truck: TruckDTO;
    list: TruckDTO[];
    loading: boolean;
    error: Error;
}