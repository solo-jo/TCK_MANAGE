package tck.division.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tck.division.manage.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
