package ua.gov.cyberpolice.breach.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@Column
	private UUID headId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getHeadId() {
		return headId;
	}

	public void setHeadId(UUID headId) {
		this.headId = headId;
	}

	public boolean isNew() {
		return this.id == null;
	}

}
