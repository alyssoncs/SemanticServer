package br.ufma.lsdi.semanticserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
	@Id
	@Column(name = "email")
	String email;

	@OneToOne
	@JoinColumn(name = "fk_holder_id", unique = true)
	Holder HolderId;

	@NotNull
	@Column(name = "name")
	String name;

}
