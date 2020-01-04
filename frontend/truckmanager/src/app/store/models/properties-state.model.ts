import { ApplicationDTO, ClassificationDTO, ColorDTO, FuelTypeDTO } from 'src/swagger';

export interface PropertiesState {
    applications: ApplicationDTO[];
    classifications: ClassificationDTO[];
    colors: ColorDTO[];
    fuelTypes: FuelTypeDTO[];
    loading: boolean;
    error: Error
}