package br.edu.unifei.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@SuppressWarnings("serial")
@Entity
//@Table(name="Screenshots")
public class Screenshot implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Lob
	@Column(name="IMAGE")
	private byte[] bufferedImageByte;
	
	public Screenshot(BufferedImage image) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		baos.flush();
		byte[] bytes = baos.toByteArray();
		this.bufferedImageByte = bytes;
		baos.close();
		
	}
	
	public Screenshot() {
		
	}

	public byte[] getBufferedImageByte() {
		return bufferedImageByte;
	}

	public void setBufferedImageByte(byte[] bufferedImageByte) {
		this.bufferedImageByte = bufferedImageByte;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	@Override
	public String toString() {
		return "SS [id=" + id + ", bytes=" + bufferedImageByte + "]";
	}
	*/
	 
	
}
