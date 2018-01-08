package de.jottyfan.camporganizer.modules.campadmin;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import java.util.Base64;

import de.jottyfan.camporganizer.db.jooq.enums.EnumDocument;
import de.jottyfan.camporganizer.db.jooq.enums.EnumFiletype;

/**
 * 
 * @author jotty
 *
 */
public class DocumentBean {

	private final Integer pk;
	private EnumDocument doctype;
	private String name;
	private String document;
	private EnumFiletype filetype;
	private Part uploadfile;

	public DocumentBean(Integer pk) {
		this.pk = pk;
	}

	public void encodeUpload() throws IOException {
		if (uploadfile != null) {
			InputStream inputStream = uploadfile.getInputStream();
			byte[] bytes = IOUtils.toByteArray(inputStream);
			if (bytes.length > 0) {
				document = Base64.getEncoder().encodeToString(bytes);
			} // not uploaded files should not be changed, so document must be kept as is
		}
	}

	public Integer getPk() {
		return pk;
	}

	public void setDoctype(EnumDocument doctype) {
		this.doctype = doctype;
	}

	public EnumDocument getDoctype() {
		return doctype;
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
