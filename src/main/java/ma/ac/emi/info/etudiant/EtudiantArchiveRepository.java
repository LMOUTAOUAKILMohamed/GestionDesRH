package ma.ac.emi.info.etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

public interface EtudiantArchiveRepository extends JpaRepository<EtudiantArchive, Long> , QuerydslPredicateExecutor<EtudiantArchive> , PagingAndSortingRepository<EtudiantArchive, Long>, QuerydslBinderCustomizer<QEtudiantArchive> {
	
	@Query(value = "SELECT max(promotion) FROM ARCHIVE_ETUDIANTS", nativeQuery = true)
	public Integer getMaxPromotion();
	
	@Query(value = "SELECT min(promotion) FROM ARCHIVE_ETUDIANTS", nativeQuery = true)
	public Integer getMinPromotion();
	
	@Override
    default public void customize(
      QuerydslBindings bindings, QEtudiantArchive root) {
        bindings.bind(String.class)
          .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.emailPersonnel);
        bindings.excluding(root.emailProfessionnel);
      }

}
