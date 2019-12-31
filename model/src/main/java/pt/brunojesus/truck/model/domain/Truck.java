package pt.brunojesus.truck.model.domain;

import java.util.Date;
import java.util.Set;

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

	@Id
	@EqualsAndHashCode.Include
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_truck")
//	@SequenceGenerator(name = "seq_truck", sequenceName = "seq_truck", allocationSize = 1)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "model")
	private String model;
	
	@Column(name = "horsepower")
	private Integer horsepower;
	
	@Column(name = "displacement")
	private Double displacement;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fuel_type_id")
	private FuelType fuelType;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classification_id")
	private Classification classification;
	
	@OneToMany(mappedBy = "truck")
	private Set<RelTruckApplication> relTruckApplications;
	
	@OneToMany(mappedBy = "truck")
	private Set<RelTruckColor> relTruckColor;
	
	@Version
	@Column(name = "modified_at")
	private Date modificationTimestamp;
}
