package de.jottyfan.camporganizer.modules.subscriber;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;

/**
 * 
 * @author jotty
 *
 */
public class PersondocumentBean {

	private final Integer pk;
	private Integer fkPerson;
	private String name;
	private String document;
	private EnumFiletype filetype;
	private Part uploadfile;

	public PersondocumentBean(Integer pk) {
		this.pk = pk;
	}

	public void encodeUpload() throws IOException {
		if (uploadfile != null) {
			InputStream inputStream = uploadfile.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			if (bytes.length > 0) {
				document = Base64.getEncoder().encodeToString(bytes);
			} // not uploaded files should not be changed, so document must be kept as is
		} else {
			throw new IOException("uploadfile is null");
		}
	}

	public Integer getPk() {
		return pk;
	}

	public void setFkPerson(Integer fkPerson) {
		this.fkPerson = fkPerson;
	}

	public Integer getFkPerson() {
		return fkPerson;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocument() {
		return document;
	}

	public void setFiletype(EnumFiletype filetype) {
		this.filetype = filetype;
	}

	public EnumFiletype getFiletype() {
		return filetype;
	}

	public Part getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(Part uploadfile) {
		this.uploadfile = uploadfile;
	}
}
