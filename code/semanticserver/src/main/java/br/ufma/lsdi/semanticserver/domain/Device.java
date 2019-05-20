package br.ufma.lsdi.semanticserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device implements Serializable {
	@Id
	@Column(name = "device_uuid")
	private String uuid;

	@ManyToOne
	@JoinColumn(name = "fk_holder_id")
	private Holder holder;

	@Column(name = "description")
	private String description;

	@Column(name = "name")
	@NotNull
	private String name;

	//@OneToOne(mappedBy = "device")
	//private Thing thing;

	//@OneToOne(mappedBy = "device")
	//private Mhub mhub;
}
