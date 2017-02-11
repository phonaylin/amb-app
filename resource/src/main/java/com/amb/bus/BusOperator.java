package com.amb.bus;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.amb.core.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
//@RequiredArgsConstructor
@Embeddable
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class BusOperator extends AbstractEntity{

	@Column(nullable = false)
	private final String name;
	
	@OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
	private Set<Bus> buses;
}