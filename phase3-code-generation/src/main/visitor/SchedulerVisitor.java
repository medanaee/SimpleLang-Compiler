package main.visitor;

import main.TypeCheck.Type;
import main.ast.nodes.ComponentItem.LoopStatement.RepeatLoop;
import main.ast.nodes.ComponentItem.NetDeclaration.NetDeclAssignment;
import main.ast.nodes.ComponentItem.NetDeclaration.NetTypeNetDeclaration;
import main.scheduler.ScheduledEvent;
import main.ast.nodes.Expression.*;
import main.ast.nodes.Types.PrimaryLiteral;
import main.ast.nodes.ComponentItem.ParamAssignment;
import main.ast.nodes.ComponentItem.LoopStatement.PeriodDeclaration;
import main.ast.nodes.Identifier;
import java.util.ArrayList;
import java.util.List;



public class SchedulerVisitor extends Visitor<Type> {
    
    ArrayList<ScheduledEvent> eventQueue = new ArrayList<>();
    static int max_time = 0;

    public List<ScheduledEvent> getEventQueue() {
        return eventQueue;
    }

    @Override
    public Type visit(RepeatLoop repNode) {

        Expression repeat_num_obj = repNode.getExpression();
        int repeat_num = 0;

        if (repeat_num_obj instanceof PrimaryLiteral) {
            repeat_num = Integer.parseInt(((PrimaryLiteral) repeat_num_obj).getText());
        }
        else if (repeat_num_obj instanceof HierarchicalPrimary) {
            repeat_num = -1;
        }


        PeriodDeclaration period_duration_obj = repNode.getPeriodDeclaration();
        int period_duration = 0;
        Expression period_duration_exp = period_duration_obj.getExpression();
        period_duration = Integer.parseInt(((PrimaryLiteral) period_duration_exp).getText());
 
        int time = period_duration;
        if (repeat_num >= 0) {
            for (int i = 0; i < repeat_num; i++) {
                ScheduledEvent ev = new ScheduledEvent(time, repNode.getStatementOrNull());
                eventQueue.add(ev);
                max_time = Math.max(max_time, time);
                time += period_duration;
            }
        }
        else {
            while (time <= max_time) {
                ScheduledEvent ev = new ScheduledEvent(time, repNode.getStatementOrNull());
                eventQueue.add(ev);
                time += period_duration;
            }
        }
        return null;
    }

    // @Override
    // public Type visit(NetDeclAssignment DecNode) {
        
    //     eventQueue.add(new ScheduledEvent(0, DecNode));
        
    //     return null;
    // }

    // @Override
    // public Type visit(ParamAssignment paramNode) {
        
    //     eventQueue.add(new ScheduledEvent(0, paramNode));
        
    //     return null;
    // }
}
