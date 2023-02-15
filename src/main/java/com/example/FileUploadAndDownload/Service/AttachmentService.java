package com.example.FileUploadAndDownload.Service;

import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadAndDownload.Entity.Attachment;

public interface AttachmentService {

	Attachment saveAttachment(MultipartFile file) throws Exception;

	Attachment getAttachment(String field) throws Exception;

}
