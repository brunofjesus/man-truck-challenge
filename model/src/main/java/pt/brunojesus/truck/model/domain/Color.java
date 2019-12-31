package pt.brunojesus.truck.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name = "color")
public class Color {

	@Id
	@EqualsAndHashCode.Include
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_color")
//	@SequenceGenerator(name = "seq_color", sequenceName = "seq_color", allocationSize = 1)
	@Column(name = "id")
	private int id;

	@NotNull
	@Column(name = "name")
	private String name;
}