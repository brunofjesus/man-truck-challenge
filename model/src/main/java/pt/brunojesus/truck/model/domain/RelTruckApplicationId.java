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
public class RelTruckApplicationId implements Serializable {

	private static final long serialVersionUID = 3392227762986333270L;

	@Column(name = "truck_id")
	private long truckId;
	
	@Column(name = "application_id")
	private int applicationId;
}
