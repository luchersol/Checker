package specialized_checkers.file;

import com.fasterxml.jackson.databind.JsonNode;

import util.AbstractChecker;

public class CheckerJson extends AbstractChecker<JsonNode, CheckerJson> {

    public CheckerJson(JsonNode object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerJson self() {
        return this;
    }
    
    
}
