package com.example.batch;

import java.util.List;

import org.apache.apex.malhar.lib.batch.TimeSchedulerStatsListener;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.Context;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.DAGChangeSet;
import com.datatorrent.api.StatsListener;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.stram.plan.logical.mod.DAGChangeSetImpl;
import com.google.common.collect.Lists;

@ApplicationAnnotation(name="BatchApp")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    ControlOperator control = dag.addOperator("Control", ControlOperator.class);
    List<StatsListener> listeners = Lists.newArrayList();
    TimeSchedulerStatsListener listener = new TimeSchedulerStatsListener();
    listeners.add(listener);
    dag.setOperatorAttribute(control, Context.OperatorContext.STATS_LISTENERS, Lists.newArrayList((StatsListener)listener));

    DAGChangeSet change = new DAGChangeSetImpl();
    BatchLineFileInputOperator file = change.addOperator("FileInput", BatchLineFileInputOperator.class);
    file.setDirectory("/tmp/input");
    BatchStringFileOutputOperator out = change.addOperator("Output", BatchStringFileOutputOperator.class);
    out.setFilePath("/tmp/output");
    out.setOutputFileName("outputFile");
    change.addStream("FileToFile", file.output, out.input);
    listener.setDagChange(change);
  }

}
