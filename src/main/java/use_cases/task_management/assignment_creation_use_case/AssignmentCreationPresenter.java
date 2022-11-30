package use_cases.task_management.assignment_creation_use_case;

public interface AssignmentCreationPresenter {
    AssignmentCreationResponseModel prepareSuccessView(AssignmentCreationResponseModel responseModel);
    AssignmentCreationResponseModel prepareFailView(String error);
}
