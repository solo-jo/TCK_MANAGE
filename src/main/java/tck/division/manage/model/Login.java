package tck.division.manage.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

	@NotEmpty @NotBlank
	private String id;
	
	@NotEmpty @NotBlank
	private String password;
	
	
	private String prePassword;
}
