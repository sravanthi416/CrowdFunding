package com.alacriti.crowdFunding.model.vo;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileModelVO {
		private byte[] file;

		@FormParam("file")
		@PartType("application/octet-stream")
		public void setFile(byte[] file) {
		this.file = file;
		}

		public byte[] getFile() {
		return file;
		}
		

}
