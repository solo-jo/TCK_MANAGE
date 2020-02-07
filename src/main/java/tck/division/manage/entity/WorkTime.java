package tck.division.manage.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_member_work_tmp")
public class WorkTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int no;
	
	@Column
	private String member_no;
	
	@Column
	private String work_dt;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime start_time;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime end_time;
	
	@Column
	private int work_time;
	
	@Column
	private String proj_name;
	
	@Column
	private String work_category;
	
	@Column
	private String work_detail;
	
	@Column
	private String work_desc;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime reg_dt;
	
}
