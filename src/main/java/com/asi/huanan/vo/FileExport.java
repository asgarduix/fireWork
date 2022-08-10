package com.asi.huanan.vo;

public class FileExport {
	String fileName;			//檔案名
	String fileBase64Encode;	//Base64編碼的檔案資料
	String remoteFileName;		//遠端檔案名
	String message;				//回傳訊息

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileBase64Encode() {
		return fileBase64Encode;
	}

	public void setFileBase64Encode(String fileBase64Encode) {
		this.fileBase64Encode = fileBase64Encode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemoteFileName() {
		return remoteFileName;
	}

	public void setRemoteFileName(String remoteFileName) {
		this.remoteFileName = remoteFileName;
	}


	
	
}
