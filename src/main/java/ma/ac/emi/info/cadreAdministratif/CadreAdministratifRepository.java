package ma.ac.emi.info.cadreAdministratif;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CadreAdministratifRepository extends JpaRepository<CadreAdministratif, Long>{

	@Query("SELECT motDePasse FROM CadreAdministratif  WHERE id = :cadreId")
	public String getPasswordById(@Param("cadreId") Long cadreId);
	
}
