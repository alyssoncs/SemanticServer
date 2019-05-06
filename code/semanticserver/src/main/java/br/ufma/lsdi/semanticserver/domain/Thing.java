package br.ufma.lsdi.semanticserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "thing")
public class Thing implements Serializable {
	//@Column(name="fk_device_uuid")
	@Id
	private String id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "fk_device_uuid")
	private Device device;
}
