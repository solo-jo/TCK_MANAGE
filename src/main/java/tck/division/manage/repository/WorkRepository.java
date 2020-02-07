package tck.division.manage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tck.division.manage.entity.WorkTime;
import tck.division.manage.model.SearchParam;

public interface WorkRepository extends JpaRepository<WorkTime, Integer> {

	@Query(value = "select * from tbl_member_work_tmp as a where a.member_no = :#{#param.id} and DATE_FORMAT(a.work_dt, '%Y-%m-%d') between :#{#param.searchDateFrom} and :#{#param.searchDateTo}", nativeQuery = true)
	List<WorkTime> listWorkTime(@Param("param") SearchParam param);
}
