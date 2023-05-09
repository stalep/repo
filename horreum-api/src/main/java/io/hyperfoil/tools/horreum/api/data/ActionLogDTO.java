package io.hyperfoil.tools.horreum.api.data;

public class ActionLogDTO extends PersistentLogDTO {
    public int testId;
    public String event;
    public String type;

    public ActionLogDTO() {
        super(0, (String)null);
    }

    public ActionLogDTO(int level, int testId, String event, String type, String message) {
        super(level, message);
        this.testId = testId;
        this.event = event;
        this.type = type;
    }
}
