package cz.partners.tech.interview.domain;

import cz.partners.tech.interview.domain.enums.ProcessState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class UploadedFile {

    @Id
    private String id;

    private String fileName;
    private String contentType;
    private long size;

    @Field(targetType = FieldType.STRING)
    private ProcessState processState;

    private String fileId;
}
