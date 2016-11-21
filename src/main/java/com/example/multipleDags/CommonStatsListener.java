package com.example.multipleDags;

import com.datatorrent.api.StatsListener;

public class CommonStatsListener implements StatsListener, StatsListener.ContextAwareStatsListener
{
  StatsListenerContext context;
 
  @Override
  public void setContext(StatsListenerContext context)
  {
    this.context = context;
  }

  @Override
  public Response processStats(BatchedOperatorStats stats)
  {
    return null;
  }

  public StatsListenerContext getContext()
  {
    return context;
  }
}
