package user_register_usecase;

import entities.*;

import java.io.IOException;
import java.time.LocalDateTime;

// Use Case Layer

public class UserRegInteractor implements UserRegInputBoundary {

    final UserRegGateway userGateway;

    final UserRegPresenter userPresenter;

//    private UserFactory userFactory;

    public UserRegInteractor(UserRegGateway gateway, UserRegPresenter userRegPresenter) {
        this.userGateway = gateway;
        this.userPresenter = userRegPresenter;
//        this.userFactory = null;
    }

    @Override
    public UserRegResponse create(UserRegRequest request) throws IOException {
        if (userGateway.existsByName(request.getName())) {
            return userPresenter.prepareFailView("That username is taken!");
        } else if (!request.getPassword().equals(request.getReenterPassword())) {
            return userPresenter.prepareFailView("Passwords entered do not match.");
        } else if (!request.getTypeOfUser().equals("Instructor") && !request.getTypeOfUser().equals("Student")) {
            return userPresenter.prepareFailView("Enter either 'Instructor' or 'Student'.");
        }

        UserFactory userFactory;
        if (request.getTypeOfUser().equals("Instructor")) {
            userFactory = new InstructorUserFactory();
        } else {
            userFactory = new StudentUserFactory();
        }

        User user = userFactory.create(request.getName(), request.getPassword());
        if (!user.checkPassword()) {
            return userPresenter.prepareFailView("Password must be at least 9 characters long");
        }

        LocalDateTime now = LocalDateTime.now();

        UserRegSaveRequest userModel;
        if (user instanceof StudentUser) {
            userModel = new StudentSaveRequest(user.getName(), user.getPass(),
                    (StudentUser) user, now);
        } else if (user instanceof InstructorUser) {
            userModel = new InstructorSaveRequest(user.getName(), user.getPass(),
                    (InstructorUser) user, now);
        } else {
            userModel = new UserRegSaveRequest(user.getName(), user.getPass(), user, now);
        }

        userGateway.save(userModel);

        UserRegResponse accResponseModel = new UserRegResponse(user.getName(), now.toString());
        return userPresenter.prepareSuccessView(accResponseModel);
    }
}
