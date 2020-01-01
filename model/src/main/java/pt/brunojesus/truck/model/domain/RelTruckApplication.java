package pt.brunojesus.truck.model.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "rel_truck_application")
public class RelTruckApplication {
	
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_TRUCK = "truck";
	public static final String PROPERTY_APPLICATION = "application";

	@EmbeddedId
	@EqualsAndHashCode.Include
	RelTruckApplicationId id;
	
	@ManyToOne
	@MapsId("truck_id")
	@JoinColumn(name = "truck_id")
	@JsonBackReference
	private Truck truck;
	
	@ManyToOne
	@MapsId("application_id")
	@JoinColumn(name = "application_id")
	private Application application;
}
