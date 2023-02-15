package com.example.FileUploadAndDownload.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.FileUploadAndDownload.Entity.Attachment;
import com.example.FileUploadAndDownload.Model.ResponseData;
import com.example.FileUploadAndDownload.Repository.AttachmentRepo;
import com.example.FileUploadAndDownload.Service.AttachmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttachmentController {
	@Autowired
	private AttachmentRepo attachmentRepo;
	private AttachmentService attachmentService;

	public AttachmentController(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	@PostMapping("/upload")
	 @CrossOrigin(origins="http://localhost:4200")
	public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
		Attachment attachment=null;
		String downloadURL="";
		attachment=attachmentService.saveAttachment(file);
		downloadURL= ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/")
				.path(attachment.getId())
				.toUriString();
		return new ResponseData(attachment.getFileName(),
				downloadURL,
				file.getContentType(),
				file.getSize());
		}
	// api to get all Students
    @GetMapping("/listAllFiles")
    @CrossOrigin(origins="http://localhost:4200")
    public List<Attachment> getAllFiles(){
        return attachmentRepo.findAll();
    }
    
	@GetMapping("/download/{fileld}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileld) throws Exception{
		Attachment attachment=null;
		attachment=attachmentService.getAttachment(fileld);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(attachment.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment;filename=\""+attachment.getFileName()
						+"\"")
				.body(new ByteArrayResource(attachment.getData()));
	}
	
	}

