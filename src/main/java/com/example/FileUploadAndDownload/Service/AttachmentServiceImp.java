package com.example.FileUploadAndDownload.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadAndDownload.Entity.Attachment;
import com.example.FileUploadAndDownload.Repository.AttachmentRepo;

@Service
public class AttachmentServiceImp implements AttachmentService {
	private AttachmentRepo attachmentRepo;
	
	public AttachmentServiceImp(AttachmentRepo attachmentRepo) {
		super();
		this.attachmentRepo = attachmentRepo;
	}

	@Override
	public Attachment saveAttachment(MultipartFile file) throws Exception {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("...")) {
				throw new Exception("Filename contains valid path sequence" + fileName);
			}
			Attachment attachment=new Attachment(fileName,file.getContentType(),file.getBytes());
			return attachmentRepo.save(attachment);
		}
		catch(Exception e) {
			throw new Exception("Could not save file:"+fileName);
		}
	}

	@Override
	public Attachment getAttachment(String field) throws Exception {
		return attachmentRepo.findById(field)
				.orElseThrow(()->new Exception("File not found with id"+field));
	}

}
