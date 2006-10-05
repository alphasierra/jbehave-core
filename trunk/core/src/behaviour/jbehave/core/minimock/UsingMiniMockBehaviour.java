/*
 * Created on 18-Oct-2004
 * 
 * (c) 2003-2004 ThoughtWorks Ltd
 *
 * See license.txt for license details
 */
package jbehave.core.minimock;

import jbehave.core.Ensure;
import jbehave.core.mock.Constraint;
import jbehave.core.mock.Mock;
import jbehave.core.mock.UsingConstraints;


/**
 * @author <a href="mailto:dan.north@thoughtworks.com">Dan North</a>
 * @author <a href="mailto:damian.guy@thoughtworks.com">Damian Guy</a>
 */
public class UsingMiniMockBehaviour extends UsingConstraints {
    
    UsingMiniMock miniMock = new UsingMiniMock();
    
    public interface SomeType {}

    private Constraint containsMocks() {
        return new Constraint() {
            public boolean matches(Object arg) {
                return ((UsingMiniMock)arg).containsMocks();
            }
            public String toString() {
                return "UsingMiniMock instance containing mocks";
            }
        };
    }
    
    public void shouldStoreMock() throws Exception {
        ensureThat(miniMock, not(containsMocks()));
        
        miniMock.mock(SomeType.class);
        
        ensureThat(miniMock, containsMocks());
      
    }
    
    public void shouldCreateConstraintForPrimitiveFloatingPointTypes() throws Exception {
        Constraint matchesFloatValue = miniMock.eq(1.0);
        ensureThat(new Float(1.0), matchesFloatValue);
        ensureThat(new Double(1.0), matchesFloatValue);
    }
    
    public void shouldCreateConstraintForPrimitiveIntegerTypes() throws Exception {
        Constraint matchesIntTypeValue = miniMock.eq(1);
        ensureThat(new Byte((byte)1), matchesIntTypeValue);
        ensureThat(new Short((short)1), matchesIntTypeValue);
        ensureThat(new Integer(1), matchesIntTypeValue);
        ensureThat(new Long((long)1), matchesIntTypeValue);
    }
    
    public void shouldCreateConstraintForPrimitiveCharType() throws Exception {
        Constraint c = miniMock.eq('c');
        Ensure.that("constraint should match Character 'c'", c.matches(new Character('c')));
    }
    
    public void shouldCreateConstraintForPrimitiveLongType() throws Exception {
        Constraint c = miniMock.eq(1l);
        Ensure.that(c.matches(new Long(1)));
    }
    
    public void shouldCreateConstraintForPrimitiveBooleanType() throws Exception {
        Constraint c = miniMock.eq(true);
        Ensure.that(c.matches(Boolean.TRUE)); 
    }
    
    public void shouldCreateConstraintForPrimitiveFloatType() throws Exception {
        float f = 1;
        Constraint c = miniMock.eq(f);
        Ensure.that(c.matches(new Float(1)));
    }
    
    public void shouldCreateConstraintForPrimitiveByteType() throws Exception {
        byte b = 1;
        Constraint c = miniMock.eq(b);
        Ensure.that(c.matches(new Byte(b)));
    }
    
    public void shouldCreateConstraintForPrimitiveShortType() throws Exception {
        short s = 1;
        Constraint c = miniMock.eq(s);
        Ensure.that(c.matches(new Short(s)));
    }
    
    public interface BehaviourInterface1 {
        int getInt();
        long getLong();
        byte getByte();
        short getShort();
        double getDouble();
        float getFloat();
        char getChar();
        boolean getBoolean();
    }
    
    public void shouldCreateCorrectReturnValueForPrimitiveInt() throws Exception {
      Mock mock = miniMock.mock(BehaviourInterface1.class); 
      mock.stubs("getInt").will(miniMock.returnValue(1));
   
      int i = ((BehaviourInterface1)mock).getInt();
      ensureThat(i, eq(1));
    }
    
    public void shouldCreateCorrectReturnValueForPrimitiveLong() throws Exception {
        Mock mock = miniMock.mock(BehaviourInterface1.class); 
        mock.stubs("getLong").will(miniMock.returnValue(1l));
     
        long i = ((BehaviourInterface1)mock).getLong();
        ensureThat(i, eq(1));
    }
    
    public void shouldCreateCorrectReturnValueForPrimitiveShort() throws Exception {
        short s = 2;
        Mock mock = miniMock.mock(BehaviourInterface1.class); 
        mock.stubs("getShort").will(miniMock.returnValue(s));
     
        short i = ((BehaviourInterface1)mock).getShort();
        ensureThat(i, eq(s));
     }
    
    public void shouldCreateCorrectReturnValueForPrimitiveByte() throws Exception {
        byte b = 3;
        Mock mock = miniMock.mock(BehaviourInterface1.class); 
        mock.stubs("getByte").will(miniMock.returnValue(b));
     
        byte i = ((BehaviourInterface1)mock).getByte();
        ensureThat(i, eq(b));
     }
    
    public void shouldCreateCorrectReturnValueForPrimitiveDouble() throws Exception {
        double d = 4;
        Mock mock = miniMock.mock(BehaviourInterface1.class); 
        mock.stubs("getDouble").will(miniMock.returnValue(d));
     
        double i = ((BehaviourInterface1)mock).getDouble();
        ensureThat(i, eq(d, 0));
     }
    
    public void shouldCreateCorrectReturnValueForPrimitiveFloat() throws Exception {
        float f = 4;
        Mock mock = miniMock.mock(BehaviourInterface1.class); 
        mock.stubs("getFloat").will(miniMock.returnValue(f));
     
        float i = ((BehaviourInterface1)mock).getFloat();
        ensureThat(i, eq(f, 0));
     }
    
    public void shouldCreateCorrectReturnValueForPrimitiveChar() throws Exception {
        char c = 4;
        Mock mock = miniMock.mock(BehaviourInterface1.class); 
        mock.stubs("getChar").will(miniMock.returnValue(c));
     
        char i = ((BehaviourInterface1)mock).getChar();
        ensureThat(i, eq(c));
     }
    
    public void shouldCreateCorrectReturnValueForPrimitiveBoolean() throws Exception {
        boolean b = true;
        Mock mock = miniMock.mock(BehaviourInterface1.class); 
        mock.stubs("getBoolean").will(miniMock.returnValue(b));
     
        boolean i = ((BehaviourInterface1)mock).getBoolean();
        ensureThat(i, eq(b));
     }
}
