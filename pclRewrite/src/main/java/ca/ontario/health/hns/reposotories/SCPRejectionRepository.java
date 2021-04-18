package ca.ontario.health.hns.reposotories;


import ca.ontario.health.hns.domain.SCPRejection;
import org.springframework.data.repository.CrudRepository;


import java.util.List;



    public interface SCPRejectionRepository extends CrudRepository<SCPRejection,String > {
      // public List<SCPRejection> findByAppNum(String appNum);
      @Override
      List<SCPRejection> findAll();
      List<SCPRejection> findByAppNum(String appNum);

    }


