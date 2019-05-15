package br.ufma.lsdi.semanticserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "holder")
public class Holder {

	@Id
	@Column(name = "holder_id")
	@SequenceGenerator(name = "holder_holder_id_seq", sequenceName = "holder_holder_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holder_holder_id_seq")
	private Long id;

	//@OneToMany
	//@JoinColumn(name = "fk_holder_id")
	//private List<Device> devices;
}
