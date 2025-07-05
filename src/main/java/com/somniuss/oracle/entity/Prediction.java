package com.somniuss.oracle.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "predictions")
public class Prediction implements Serializable {

	private static final long serialVersionUID = 1L;

	public Prediction() {
	}

	public Prediction(String text) {
		this.text = text;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String text;

	@Column(name = "image_name")
	private String imageName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Prediction prediction))
			return false;
		if (this.id == null || prediction.id == null)
			return false;
		return this.id.equals(prediction.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : System.identityHashCode(this);
	}

	@Override
	public String toString() {
		return "Prediction{" + "id=" + id + ", text='" + text + '\'' + ", imageName='" + imageName + '\'' + '}';
	}
}
