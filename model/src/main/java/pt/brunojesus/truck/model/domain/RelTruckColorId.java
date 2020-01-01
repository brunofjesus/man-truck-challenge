package pt.brunojesus.truck.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
public class RelTruckColorId implements Serializable {

	private static final long serialVersionUID = -6321150468604007896L;

	@EqualsAndHashCode.Include
	@Column(name = "truck_id")
	private long truckId;
	
	@EqualsAndHashCode.Include
	@Column(name = "color_id")
	private int colorId;
}
