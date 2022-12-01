package use_cases.course_features.course_creation_use_case;

// Use case layer

import java.io.IOException;

public interface CourseCreationDsGateway {
    // checks if the course is already in the course map by its unique id
    boolean existsByCourseID(String courseIdentifier);

    void save(CourseCreationRequestModel requestModel) throws IOException;
}
