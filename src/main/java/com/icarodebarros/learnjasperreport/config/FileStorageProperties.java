package com.icarodebarros.learnjasperreport.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
    private String downloadDir;
    private String defaultFileName;
    private String defaultFileFormat;

	public String getDownloadDir() {
        return downloadDir;
    }

    public void setDownloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
    }
    
    public String getDefaultFileName() {
    	return defaultFileName;
    }
    
    public void setDefaultFileName(String defaultFileName) {
    	this.defaultFileName = defaultFileName;
    }
    
	public String getDefaultFileFormat() {
		return defaultFileFormat;
	}

	public void setDefaultFileFormat(String defaultFileFormat) {
		this.defaultFileFormat = defaultFileFormat;
	}
    
}
