package com.mc.mvc.infra.util.file.dto;

import java.time.LocalDateTime;

import com.mc.mvc.infra.code.Code;
import com.mc.mvc.infra.util.file.FilePath;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilePathDto {
	
	private Long fpIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private LocalDateTime regDate;
	private Boolean isDel;
	private String groupName;

	public String getFullPath() {
		return Code.STORAGE_PATH + groupName + "/" + savePath + renameFileName;
	}
	
	public FilePathDto(FilePath entity) {
		this.fpIdx = entity.getFpIdx();
		this.originFileName = entity.getOriginFileName();
		this.renameFileName = entity.getRenameFileName();
		this.savePath = entity.getSavePath();
		this.regDate = entity.getRegDate();
		this.isDel = entity.getIsDel();
		this.groupName = entity.getGroupName();
	}
	
	
	
}












