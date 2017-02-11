package com.amb.core;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Getter
@RequiredArgsConstructor
@Embeddable
public class POI extends AbstractEntity{
	
	@Column(nullable = false)
	private String title;

	private String description;
	
	private String map;
}
