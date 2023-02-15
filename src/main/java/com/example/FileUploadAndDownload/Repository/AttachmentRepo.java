package com.example.FileUploadAndDownload.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FileUploadAndDownload.Entity.Attachment;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment,String>{
	List<Attachment> findAll();
}
