package screens.task_management.assignment_edit_screens;

import entities.Assignment;
import use_cases.task_management.assignment_edit_use_case.AssignmentEditInputBoundary;
import use_cases.task_management.assignment_edit_use_case.AssignmentEditRequestModel;
import use_cases.task_management.assignment_edit_use_case.AssignmentEditResponseModel;

import java.time.LocalDateTime;

public class AssignmentEditController {
    final AssignmentEditInputBoundary input;
    public AssignmentEditController(AssignmentEditInputBoundary input) {
        this.input = input;
    }

    public AssignmentEditResponseModel edit(Assignment assignment, String title, int priority, LocalDateTime dueDate, double weightage) {
        AssignmentEditRequestModel requestModel = new AssignmentEditRequestModel(assignment, title, priority, dueDate, weightage);
        return input.edit(requestModel);
    }

}
