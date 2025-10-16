package cz.partners.tech.interview.repository;

import cz.partners.tech.interview.domain.UploadedFile;
import cz.partners.tech.interview.domain.enums.ProcessState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UploadedFileRepository extends MongoRepository<UploadedFile, String> {

    Optional<UploadedFile> findByIdAndProcessState(String id, ProcessState processState);
}
