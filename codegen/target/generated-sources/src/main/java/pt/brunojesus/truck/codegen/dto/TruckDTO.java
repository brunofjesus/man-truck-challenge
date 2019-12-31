package pt.brunojesus.truck.codegen.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pt.brunojesus.truck.codegen.dto.ApplicationDTO;
import pt.brunojesus.truck.codegen.dto.ClassificationDTO;
import pt.brunojesus.truck.codegen.dto.ColorDTO;
import pt.brunojesus.truck.codegen.dto.FuelTypeDTO;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TruckDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-31T17:52:56.721002Z[Europe/Lisbon]")
public class TruckDTO   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("model")
  private String model = null;

  @JsonProperty("horsepower")
  private Float horsepower = null;

  @JsonProperty("displacement")
  private Float displacement = null;

  @JsonProperty("fuelType")
  private FuelTypeDTO fuelType = null;

  @JsonProperty("classification")
  private ClassificationDTO classification = null;

  @JsonProperty("applications")
  @Valid
  private List<ApplicationDTO> applications = null;

  @JsonProperty("colors")
  @Valid
  private List<ColorDTO> colors = null;

  @JsonProperty("modifiedAt")
  private LocalDate modifiedAt = null;

  public TruckDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TruckDTO model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public TruckDTO horsepower(Float horsepower) {
    this.horsepower = horsepower;
    return this;
  }

  /**
   * Get horsepower
   * @return horsepower
  **/
  @ApiModelProperty(value = "")
  
    public Float getHorsepower() {
    return horsepower;
  }

  public void setHorsepower(Float horsepower) {
    this.horsepower = horsepower;
  }

  public TruckDTO displacement(Float displacement) {
    this.displacement = displacement;
    return this;
  }

  /**
   * Get displacement
   * @return displacement
  **/
  @ApiModelProperty(value = "")
  
    public Float getDisplacement() {
    return displacement;
  }

  public void setDisplacement(Float displacement) {
    this.displacement = displacement;
  }

  public TruckDTO fuelType(FuelTypeDTO fuelType) {
    this.fuelType = fuelType;
    return this;
  }

  /**
   * Get fuelType
   * @return fuelType
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public FuelTypeDTO getFuelType() {
    return fuelType;
  }

  public void setFuelType(FuelTypeDTO fuelType) {
    this.fuelType = fuelType;
  }

  public TruckDTO classification(ClassificationDTO classification) {
    this.classification = classification;
    return this;
  }

  /**
   * Get classification
   * @return classification
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ClassificationDTO getClassification() {
    return classification;
  }

  public void setClassification(ClassificationDTO classification) {
    this.classification = classification;
  }

  public TruckDTO applications(List<ApplicationDTO> applications) {
    this.applications = applications;
    return this;
  }

  public TruckDTO addApplicationsItem(ApplicationDTO applicationsItem) {
    if (this.applications == null) {
      this.applications = new ArrayList<>();
    }
    this.applications.add(applicationsItem);
    return this;
  }

  /**
   * Get applications
   * @return applications
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ApplicationDTO> getApplications() {
    return applications;
  }

  public void setApplications(List<ApplicationDTO> applications) {
    this.applications = applications;
  }

  public TruckDTO colors(List<ColorDTO> colors) {
    this.colors = colors;
    return this;
  }

  public TruckDTO addColorsItem(ColorDTO colorsItem) {
    if (this.colors == null) {
      this.colors = new ArrayList<>();
    }
    this.colors.add(colorsItem);
    return this;
  }

  /**
   * Get colors
   * @return colors
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ColorDTO> getColors() {
    return colors;
  }

  public void setColors(List<ColorDTO> colors) {
    this.colors = colors;
  }

  public TruckDTO modifiedAt(LocalDate modifiedAt) {
    this.modifiedAt = modifiedAt;
    return this;
  }

  /**
   * Get modifiedAt
   * @return modifiedAt
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public LocalDate getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(LocalDate modifiedAt) {
    this.modifiedAt = modifiedAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TruckDTO truckDTO = (TruckDTO) o;
    return Objects.equals(this.id, truckDTO.id) &&
        Objects.equals(this.model, truckDTO.model) &&
        Objects.equals(this.horsepower, truckDTO.horsepower) &&
        Objects.equals(this.displacement, truckDTO.displacement) &&
        Objects.equals(this.fuelType, truckDTO.fuelType) &&
        Objects.equals(this.classification, truckDTO.classification) &&
        Objects.equals(this.applications, truckDTO.applications) &&
        Objects.equals(this.colors, truckDTO.colors) &&
        Objects.equals(this.modifiedAt, truckDTO.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, model, horsepower, displacement, fuelType, classification, applications, colors, modifiedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TruckDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    horsepower: ").append(toIndentedString(horsepower)).append("\n");
    sb.append("    displacement: ").append(toIndentedString(displacement)).append("\n");
    sb.append("    fuelType: ").append(toIndentedString(fuelType)).append("\n");
    sb.append("    classification: ").append(toIndentedString(classification)).append("\n");
    sb.append("    applications: ").append(toIndentedString(applications)).append("\n");
    sb.append("    colors: ").append(toIndentedString(colors)).append("\n");
    sb.append("    modifiedAt: ").append(toIndentedString(modifiedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
