package com.project.todoInfom.andleError;

import java.time.LocalDateTime;

public class modelErroCustom {

	private LocalDateTime timestamp;
	private Integer status;
	private String erro;
	private String path;

	public modelErroCustom() {
		// TODO Auto-generated constructor stub
	}

	public modelErroCustom(LocalDateTime timestamp, Integer status, String erro, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.erro = erro;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
