package com.example.FileUploadAndDownload.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="AttachmentTable" )
public class Attachment {
 @Id
 @GeneratedValue(generator="uuid")
 @GenericGenerator(name="uuid",strategy ="uuid2")
 private String id;
 @Column(length = 65555)
 private String fileName;
 private String fileType;
 @Lob
 private byte[] data;
public Attachment(String fileName, String fileType, byte[] data) {
	super();
	this.fileName = fileName;
	this.fileType = fileType;
	this.data = data;
}
 
}
