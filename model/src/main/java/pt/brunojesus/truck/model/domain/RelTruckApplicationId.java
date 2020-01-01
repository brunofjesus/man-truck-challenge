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

	public static final String PROPERTY_TRUCK_ID = "truckId";
	public static final String PROPERTY_APPLICATION_ID = "applicationId";
	
	@EqualsAndHashCode.Include
	@Column(name = "truck_id")
	private long truckId;
	
	@EqualsAndHashCode.Include
	@Column(name = "application_id")
	private int applicationId;
}
