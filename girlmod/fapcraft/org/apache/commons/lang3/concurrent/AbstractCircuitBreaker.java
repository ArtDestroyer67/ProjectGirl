package org.apache.commons.lang3.concurrent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractCircuitBreaker<T> implements CircuitBreaker<T> {
   public static final String PROPERTY_NAME = "open";
   protected final AtomicReference<State> state;
   private final PropertyChangeSupport changeSupport;

   public AbstractCircuitBreaker() {
      this.state = new AtomicReference(AbstractCircuitBreaker.State.CLOSED);
      this.changeSupport = new PropertyChangeSupport(this);
   }

   public boolean isOpen() {
      return isOpen((State)this.state.get());
   }

   public boolean isClosed() {
      return !this.isOpen();
   }

   public abstract boolean checkState();

   public abstract boolean incrementAndCheckState(T var1);

   public void close() {
      this.changeState(AbstractCircuitBreaker.State.CLOSED);
   }

   public void open() {
      this.changeState(AbstractCircuitBreaker.State.OPEN);
   }

   protected static boolean isOpen(State state) {
      return state == AbstractCircuitBreaker.State.OPEN;
   }

   protected void changeState(State newState) {
      if (this.state.compareAndSet(newState.oppositeState(), newState)) {
         this.changeSupport.firePropertyChange("open", !isOpen(newState), isOpen(newState));
      }

   }

   public void addChangeListener(PropertyChangeListener listener) {
      this.changeSupport.addPropertyChangeListener(listener);
   }

   public void removeChangeListener(PropertyChangeListener listener) {
      this.changeSupport.removePropertyChangeListener(listener);
   }

   protected static enum State {
      CLOSED {
         public State oppositeState() {
            return OPEN;
         }
      },
      OPEN {
         public State oppositeState() {
            return CLOSED;
         }
      };

      private State() {
      }

      public abstract State oppositeState();
   }
}
