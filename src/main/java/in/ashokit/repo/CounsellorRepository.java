package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Counsellor;

public interface CounsellorRepository extends JpaRepository<Counsellor, Integer> {

	public Counsellor findByEmailAndPassword(String email, String password);

	public Counsellor findByEmail(String email);

}
