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

/* Essa classe esta presente dentro desta estrutura de testes somente para
 * permitir que todos os testes de MockDB funcionem corretamente. Favor nao
 * remover essa classe deste local. Para poder realizar os teste com banco real
 * (nao MockDB), eh necessario que o valor de: strategy = GenerationType.AUTO
 * seja alterado para strategy = GenerationType.TABLE */
@MappedSuperclass
abstract public class BaseEntity {

    @Id
    @TableGenerator(name = "my_seq", allocationSize = 3, initialValue = 3, valueColumnName = "next_val", table = "DB_SEQUENCES", catalog = "Resseler")
    @GeneratedValue(generator = "my_seq", strategy = GenerationType.AUTO)
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
