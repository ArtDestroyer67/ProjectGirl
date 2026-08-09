package org.apache.commons.lang3.concurrent;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class EventCountCircuitBreaker extends AbstractCircuitBreaker<Integer> {
   private static final Map<AbstractCircuitBreaker.State, StateStrategy> STRATEGY_MAP = createStrategyMap();
   private final AtomicReference<CheckIntervalData> checkIntervalData;
   private final int openingThreshold;
   private final long openingInterval;
   private final int closingThreshold;
   private final long closingInterval;

   public EventCountCircuitBreaker(int openingThreshold, long openingInterval, TimeUnit openingUnit, int closingThreshold, long closingInterval, TimeUnit closingUnit) {
      this.checkIntervalData = new AtomicReference(new CheckIntervalData(0, 0L));
      this.openingThreshold = openingThreshold;
      this.openingInterval = openingUnit.toNanos(openingInterval);
      this.closingThreshold = closingThreshold;
      this.closingInterval = closingUnit.toNanos(closingInterval);
   }

   public EventCountCircuitBreaker(int openingThreshold, long checkInterval, TimeUnit checkUnit, int closingThreshold) {
      this(openingThreshold, checkInterval, checkUnit, closingThreshold, checkInterval, checkUnit);
   }

   public EventCountCircuitBreaker(int threshold, long checkInterval, TimeUnit checkUnit) {
      this(threshold, checkInterval, checkUnit, threshold);
   }

   public int getOpeningThreshold() {
      return this.openingThreshold;
   }

   public long getOpeningInterval() {
      return this.openingInterval;
   }

   public int getClosingThreshold() {
      return this.closingThreshold;
   }

   public long getClosingInterval() {
      return this.closingInterval;
   }

   public boolean checkState() {
      return this.performStateCheck(0);
   }

   public boolean incrementAndCheckState(Integer increment) {
      return this.performStateCheck(increment);
   }

   public boolean incrementAndCheckState() {
      return this.incrementAndCheckState(1);
   }

   public void open() {
      super.open();
      this.checkIntervalData.set(new CheckIntervalData(0, this.now()));
   }

   public void close() {
      super.close();
      this.checkIntervalData.set(new CheckIntervalData(0, this.now()));
   }

   private boolean performStateCheck(int increment) {
      CheckIntervalData currentData;
      CheckIntervalData nextData;
      AbstractCircuitBreaker.State currentState;
      do {
         long time = this.now();
         currentState = (AbstractCircuitBreaker.State)this.state.get();
         currentData = (CheckIntervalData)this.checkIntervalData.get();
         nextData = this.nextCheckIntervalData(increment, currentData, currentState, time);
      } while(!this.updateCheckIntervalData(currentData, nextData));

      if (stateStrategy(currentState).isStateTransition(this, currentData, nextData)) {
         currentState = currentState.oppositeState();
         this.changeStateAndStartNewCheckInterval(currentState);
      }

      return !isOpen(currentState);
   }

   private boolean updateCheckIntervalData(CheckIntervalData currentData, CheckIntervalData nextData) {
      return currentData == nextData || this.checkIntervalData.compareAndSet(currentData, nextData);
   }

   private void changeStateAndStartNewCheckInterval(AbstractCircuitBreaker.State newState) {
      this.changeState(newState);
      this.checkIntervalData.set(new CheckIntervalData(0, this.now()));
   }

   private CheckIntervalData nextCheckIntervalData(int increment, CheckIntervalData currentData, AbstractCircuitBreaker.State currentState, long time) {
      CheckIntervalData nextData;
      if (stateStrategy(currentState).isCheckIntervalFinished(this, currentData, time)) {
         nextData = new CheckIntervalData(increment, time);
      } else {
         nextData = currentData.increment(increment);
      }

      return nextData;
   }

   long now() {
      return System.nanoTime();
   }

   private static StateStrategy stateStrategy(AbstractCircuitBreaker.State state) {
      return (StateStrategy)STRATEGY_MAP.get(state);
   }

   private static Map<AbstractCircuitBreaker.State, StateStrategy> createStrategyMap() {
      Map<AbstractCircuitBreaker.State, StateStrategy> map = new EnumMap(AbstractCircuitBreaker.State.class);
      map.put(AbstractCircuitBreaker.State.CLOSED, new StateStrategyClosed());
      map.put(AbstractCircuitBreaker.State.OPEN, new StateStrategyOpen());
      return map;
   }

   private static class CheckIntervalData {
      private final int eventCount;
      private final long checkIntervalStart;

      CheckIntervalData(int count, long intervalStart) {
         this.eventCount = count;
         this.checkIntervalStart = intervalStart;
      }

      public int getEventCount() {
         return this.eventCount;
      }

      public long getCheckIntervalStart() {
         return this.checkIntervalStart;
      }

      public CheckIntervalData increment(int delta) {
         return delta == 0 ? this : new CheckIntervalData(this.getEventCount() + delta, this.getCheckIntervalStart());
      }
   }

   private abstract static class StateStrategy {
      private StateStrategy() {
      }

      public boolean isCheckIntervalFinished(EventCountCircuitBreaker breaker, CheckIntervalData currentData, long now) {
         return now - currentData.getCheckIntervalStart() > this.fetchCheckInterval(breaker);
      }

      public abstract boolean isStateTransition(EventCountCircuitBreaker var1, CheckIntervalData var2, CheckIntervalData var3);

      protected abstract long fetchCheckInterval(EventCountCircuitBreaker var1);
   }

   private static class StateStrategyClosed extends StateStrategy {
      private StateStrategyClosed() {
      }

      public boolean isStateTransition(EventCountCircuitBreaker breaker, CheckIntervalData currentData, CheckIntervalData nextData) {
         return nextData.getEventCount() > breaker.getOpeningThreshold();
      }

      protected long fetchCheckInterval(EventCountCircuitBreaker breaker) {
         return breaker.getOpeningInterval();
      }
   }

   private static class StateStrategyOpen extends StateStrategy {
      private StateStrategyOpen() {
      }

      public boolean isStateTransition(EventCountCircuitBreaker breaker, CheckIntervalData currentData, CheckIntervalData nextData) {
         return nextData.getCheckIntervalStart() != currentData.getCheckIntervalStart() && currentData.getEventCount() < breaker.getClosingThreshold();
      }

      protected long fetchCheckInterval(EventCountCircuitBreaker breaker) {
         return breaker.getClosingInterval();
      }
   }
}
