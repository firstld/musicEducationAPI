package com.jingcheng.musicEducation.util.azure;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
/**
 * 微软云储存文件的工具类
 * @author cx
 *
 */
public class StorageUtil {
    /**
     *  containerName:为容器名称，必须为小写
     */
	private static final String storageConnectionString="DefaultEndpointsProtocol=https;AccountName=menublob;AccountKey=0yUIHb4ePP9dfv+kZb/KNRzbG7Pi1GhXMVMBs2vO3TCijQS4Mf76LdOSug61J0SpkgHQB8hLHD6QXFB1FdjXkQ==;EndpointSuffix=core.chinacloudapi.cn";
    public static CloudBlobContainer getContainer (String containerName) {
    	CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container=null;
         
		try {
			
			storageAccount=CloudStorageAccount.parse(storageConnectionString);
			blobClient =storageAccount.createCloudBlobClient();
			container=blobClient.getContainerReference(containerName);
			//创建permissions object
			BlobContainerPermissions permissions=new BlobContainerPermissions();
			//引入公共的access到permission中
			permissions.setPublicAccess(BlobContainerPublicAccessType.BLOB);
			//设置permissions到container中
			container.uploadPermissions(permissions);
			container.createIfNotExists();
			return container;
			
		} catch (Exception e) {
			return null;
		} 	
	}
    
}
