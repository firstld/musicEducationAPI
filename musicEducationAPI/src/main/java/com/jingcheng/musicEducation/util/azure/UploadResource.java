package com.jingcheng.musicEducation.util.azure;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.stereotype.Component;

import com.jingcheng.musicEducation.util.JsonResult;
import com.jingcheng.musicEducation.util.PasswordUtil;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@Component
@Path("/upload")
public class UploadResource {
  
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + "UTF-8")
	public JsonResult upload(FormDataMultiPart fileUploadForm){
		 Map<String,String> map=new HashMap<>();
		List<FormDataBodyPart> partList=fileUploadForm.getFields("file");
	  //创建连接，连接到微软云 参数“biai”可以修改
      CloudBlobContainer container=StorageUtil.getContainer("biai");
      if(partList!=null){
    	  for(FormDataBodyPart part:partList){
    		  InputStream is=part.getValueAs(InputStream.class);
    		  FormDataContentDisposition detail=part.getFormDataContentDisposition();
    		  try {
    		  String fileOriginName=detail.getFileName();
    		  String fileName=PasswordUtil.md5Hex(String.valueOf(System.currentTimeMillis())+fileOriginName)+"."+FilenameUtils.getExtension(fileOriginName);
    		  //System.out.println("文件格式："+FilenameUtils.getExtension(fileOriginName));
    		  String preName="courseSection/";
    		  String blobName=preName+fileName;
    		    
			  CloudBlockBlob blob=container.getBlockBlobReference(blobName);
			  blob.getProperties().setContentType(detail.getType());
			  blob.upload(is, detail.getSize());
			   
			  map.put("mediaType", part.getMediaType().getType());
			  map.put("url", blob.getUri().toString());
			} catch (Exception e) {			
				e.printStackTrace();
			} 
    		  
    	  }
      }
	  return new JsonResult(map);
	}

}