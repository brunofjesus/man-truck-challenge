package pt.brunojesus.truck.model.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "truck")
public class Truck {
	
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_MODEL = "model";
	public static final String PROPERTY_HORSEPOWER = "horsepower";
	public static final String PROPERTY_DISPLACEMENT = "displacement";
	public static final String PROPERTY_FUEL_TYPE = "fuelType";
	public static final String PROPERTY_CLASSIFICATION = "classification";
	public static final String PROPERTY_TRUCK_REL_TRUCK_APPLICATIONS = "relTruckApplications";
	public static final String PROPERTY_TRUCK_REL_TRUCK_COLORS = "relTruckColors";
	public static final String PROPERTY_TRUCK_MODIFICATION_TIMESTAMP = "modificationTimestamp";

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_truck")
	@SequenceGenerator(name = "seq_truck", sequenceName = "seq_truck", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@NotNull
	@Column(name = "model")
	private String model;
	
	@Column(name = "horsepower")
	private Float horsepower;
	
	@Column(name = "displacement")
	private Float displacement;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fuel_type_id")
	private FuelType fuelType;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classification_id")
	private Classification classification;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "truck", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private List<RelTruckApplication> relTruckApplications;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "truck", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private List<RelTruckColor> relTruckColors;
	
	@Version
	@Column(name = "modified_at")
	private Timestamp modificationTimestamp;
}
