package pt.brunojesus.truck.model.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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

	@EmbeddedId
	RelTruckApplicationId id;
	
	@ManyToOne
	@MapsId("truck_id")
	@JoinColumn(name = "truck_id")
	private Truck truck;
	
	@ManyToOne
	@MapsId("application_id")
	@JoinColumn(name = "application_id")
	private Application application;
}
