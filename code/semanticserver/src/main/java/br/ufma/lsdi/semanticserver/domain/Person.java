package br.ufma.lsdi.semanticserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable {
	@Id
	@Column(name = "email")
	private String email;

	@OneToOne
	@JoinColumn(name = "fk_holder_id", unique = true)
	private Holder holder;

	@NotNull
	@Column(name = "short_name")
	private String shortName;

	@Column(name = "full_name")
	private String FullName;

	@ManyToMany
	@JoinTable(
			name = "person_role",
			joinColumns = @JoinColumn(name = "fk_person_email", referencedColumnName = "email"),
			inverseJoinColumns = @JoinColumn(name = "fk_role_name", referencedColumnName = "role_name")
	)
	private List<Role> roles;

}
