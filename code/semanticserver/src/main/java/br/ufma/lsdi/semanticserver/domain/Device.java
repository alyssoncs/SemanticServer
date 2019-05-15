package br.ufma.lsdi.semanticserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device {
	@Id
	@Column(name = "device_uuid")
	private String uuid;


	@ManyToOne
	@JoinColumn(name = "fk_holder_id")
	private Holder holder;

	@NotNull
	@Column(name = "description")
	private String description;

	//@OneToOne(mappedBy = "device")
	//private Thing thing;

	//@OneToOne(mappedBy = "device")
	//private Mhub mhub;
}
