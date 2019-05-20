package br.ufma.lsdi.semanticserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "holder")
public class Holder implements Serializable {

	@Id
	@Column(name = "holder_id")
	@SequenceGenerator(name = "holder_holder_id_seq", sequenceName = "holder_holder_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holder_holder_id_seq")
	private Long id;

}
