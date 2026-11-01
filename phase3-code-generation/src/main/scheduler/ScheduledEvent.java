package main.scheduler;

import main.ast.nodes.ComponentItem.LoopStatement.StatementOrNull;
import main.ast.nodes.ComponentItem.NetDeclaration.NetDeclAssignment;
import main.ast.nodes.ComponentItem.ParamAssignment;

import java.util.ArrayList;

public class ScheduledEvent {
    private final long time;
    int repeat_initialize_type;
    private final StatementOrNull statement;
    private final NetDeclAssignment declaration;
    private final ParamAssignment parameter_dec;

    public ScheduledEvent(long time, StatementOrNull statement) {
        this.time = time;
        this.statement = statement;
        this.declaration = null;
        this.parameter_dec = null;
        repeat_initialize_type = 0;
    }

    public ScheduledEvent(long time, NetDeclAssignment dec) {
        this.time = time;
        this.declaration = dec;
        this.statement = null;
        this.parameter_dec = null;
        repeat_initialize_type = 1;
    }

    public ScheduledEvent(long time, ParamAssignment par_dec) {
        this.time = time;
        this.parameter_dec = par_dec;
        this.declaration = null;
        this.statement = null;
        repeat_initialize_type = 1;
    }
    

    public long getTime() { return time; }
    public StatementOrNull getStatement() { return statement; }
}
