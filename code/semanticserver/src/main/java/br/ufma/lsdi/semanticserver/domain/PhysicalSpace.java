package br.ufma.lsdi.semanticserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "physical_space")
public class PhysicalSpace implements Serializable {
	@Id
	@JsonIgnore
	private Long holderId;

	@ManyToOne
	@JoinColumn(name = "fk_parent_physical_space_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private PhysicalSpace parent;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "description")
	private String description;

	@MapsId
	@OneToOne
	@JoinColumn(name = "fk_holder_id")
	private Holder holder;

	@OneToMany(mappedBy = "parent")
	private List<PhysicalSpace> children;

}
