package com.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户实体")
@Data
public class User {
	@ApiModelProperty(name = "用户名")
	private String name;
	
	@ApiModelProperty(name = "用户邮箱")
	private String email;
}
