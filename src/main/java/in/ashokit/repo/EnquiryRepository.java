package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Enquiry;
import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

//	@Query(value="select count(*) from enquiry where cid = :id", nativeQuery = true)
//	public Long getEnquries(Integer id);

	@Query(value = "select count(*) from enquiry where cid = :id and status=:status", nativeQuery = true)
	public Long getEnquries(Integer id, String status);

	List<Enquiry> findByCid(Integer cid);

}
