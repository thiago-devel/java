package br.com.rubyit.resseler.persistence.dao.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@TableGenerator(name = "my_seq", allocationSize = 3, initialValue = 3, valueColumnName = "next_val", table = "DB_SEQUENCES", schema = "dbo", catalog = "Resseler")
	@GeneratedValue(generator = "my_seq", strategy = GenerationType.TABLE)
	@Column(name = "eID")
	protected Long eID;
	@Column(nullable = false)
	@NotNull
	protected Boolean cacheValid;
	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar createdAt;
	@Column(name = "UPDATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar updatedAt;
	@Column(name = "VERSION")
	@Version
	protected int version;

	public Boolean isCacheValid() {
		return cacheValid;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public int getVersion() {
		return version;
	}
	
	public Long geteID() {
		return eID;
	}

	@PrePersist
	public void createdAt() {
		this.createdAt = this.updatedAt = Calendar.getInstance();
		this.cacheValid = true;
	}

	@PreUpdate
	public void updatedAt() {
		this.updatedAt = Calendar.getInstance();
		this.cacheValid = true;
	}

}
