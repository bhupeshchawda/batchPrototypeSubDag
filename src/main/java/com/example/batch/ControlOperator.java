package com.example.batch;

import com.datatorrent.api.BatchControlOperator;
import com.datatorrent.api.Context;
import com.datatorrent.api.StatsListener;
import com.datatorrent.api.Context.OperatorContext;
import com.datatorrent.common.util.BaseOperator;
import com.google.common.collect.Lists;

public class ControlOperator extends BaseOperator implements BatchControlOperator
{
  @Override
  public void emitTuples()
  {
  }
}
