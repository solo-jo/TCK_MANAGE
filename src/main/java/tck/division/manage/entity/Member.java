package tck.division.manage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_member")
public class Member {
	
	@Id
	@Column(name="member_no")
	private String id;
	
	@Column
	private String name;
	
	@Column
	private String name_jp;
	
	@Column
	private String password;
	
	@Column
	private String div_code;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date reg_dt; 
	
	@Transient
	private String menu_codes; //메뉴 권한 추가

}
