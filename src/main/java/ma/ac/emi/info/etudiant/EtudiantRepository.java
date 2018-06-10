package ma.ac.emi.info.etudiant;


import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import java.lang.String;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> , QuerydslPredicateExecutor<Etudiant> , PagingAndSortingRepository<Etudiant, Long>, QuerydslBinderCustomizer<QEtudiant> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("update Etudiant e set e.photo = 1 where e.id = :etudiantId")
    public int affecterPhoto(@Param("etudiantId") Long etudiantId);
	
	@Query("SELECT motDePasse FROM Etudiant  WHERE id = :etudiantId")
	public String getPasswordById(@Param("etudiantId") Long etudiantId);
	
	public Etudiant findByEmailProfessionnel(String emailprofessionnel);
	
	public List<Etudiant> findByPromotion(int promotion);
	
	@Query(value = "SELECT max(promotion) FROM etudiants", nativeQuery = true)
	public Integer getMaxPromotion();
	
	@Query(value = "SELECT min(promotion) FROM etudiants", nativeQuery = true)
	public Integer getMinPromotion();
	
	@Override
    default public void customize(
      QuerydslBindings bindings, QEtudiant root) {
        bindings.bind(String.class)
          .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.emailPersonnel);
        bindings.excluding(root.emailProfessionnel);
      }
	
}
