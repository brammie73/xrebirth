package nl.games.xrebirth.generated;

import java.util.HashSet;
import java.util.Set;

/**
 * User: bram
 * Date: 15-1-14
 * Time: 22:21
 */
public class ToStringVisitor implements PropertyVisitor {

    private StringBuffer stringBuffer = new StringBuffer();

    private Set<PropertyVisitable> visited = new HashSet<>();

    public ToStringVisitor() {
    }

    public ToStringVisitor(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }

    public String string() {
        return stringBuffer.toString();
    }

    @Override
    public void visit(PropertyVisitable aVisitable, String name, Object value) {
        this.stringBuffer.append("{");
        this.stringBuffer.append(name);
        this.stringBuffer.append(":");
        this.stringBuffer.append(String.valueOf(value));
        this.stringBuffer.append("}");
    }

    @Override
    public boolean visitStart(PropertyVisitable aVisitable, String name) {
        this.stringBuffer.append("\n");
        this.stringBuffer.append(name);
        this.stringBuffer.append(":");
        if (aVisitable == null) {
            this.stringBuffer.append("null");
        } else {
            this.stringBuffer.append(aVisitable.getClass().getSimpleName());
        }
        this.stringBuffer.append("[");
        return true;
    }

    @Override
    public void visitEnd(PropertyVisitable aVisitable) {
        this.stringBuffer.append("]");
    }
}
