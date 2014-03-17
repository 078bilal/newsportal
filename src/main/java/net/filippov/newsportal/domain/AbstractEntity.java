package net.filippov.newsportal.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -5360830966611104755L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		AbstractEntity entity = (AbstractEntity) obj;
		Long entityId = entity.getId();
		if (entityId == null || getId() == null) {
			return entityEquals(obj);
		}

		return getId().equals(entity.getId());
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		} else {
			return entityHashCode();
		}
	}

	public abstract boolean entityEquals(Object obj);

	public abstract int entityHashCode();
}
