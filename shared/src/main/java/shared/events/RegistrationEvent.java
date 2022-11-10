package shared.events;

import shared.Forms.RegistrationForm;
import shared.responses.Response;

public class RegistrationEvent extends Event {
    private RegistrationForm registrationForm;

    public RegistrationEvent(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.createAccount(this);
    }
}
