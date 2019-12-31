package pt.brunojesus.truck.model.domain;

import javax.persistence.Embeddable;
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

@Embeddable
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "rel_truck_color")
public class RelTruckColor {

	@EmbeddedId
	RelTruckColor id;
	
	@ManyToOne
	@MapsId("truck_id")
	@JoinColumn(name = "truck_id")
	private Truck truck;
	
	@ManyToOne
	@MapsId("color_id")
	@JoinColumn(name = "color_id")
	private Color color;
}
