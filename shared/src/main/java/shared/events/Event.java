package shared.events;

import shared.responses.Response;

public abstract class Event {
    public abstract Response visit(EventVisitor eventVisitor);
}
