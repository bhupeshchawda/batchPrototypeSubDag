/**
 * Put your copyright and license info here.
 */
package com.example.multipleDags;

import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.lib.io.ConsoleOutputOperator;
import com.datatorrent.lib.stream.StreamMerger;

@ApplicationAnnotation(name="Application2")
public class Application2 implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    RandomNumberGenerator1 randomGenerator1 = dag.addOperator("randomGenerator1", RandomNumberGenerator1.class);
    randomGenerator1.setNumTuples(500);
    RandomNumberGenerator2 randomGenerator2 = dag.addOperator("randomGenerator2", RandomNumberGenerator2.class);
    randomGenerator2.setNumTuples(500);
    StreamMerger<Double> merger = dag.addOperator("merger", StreamMerger.class);
    ConsoleOutputOperator cons = dag.addOperator("console2", new ConsoleOutputOperator());
    dag.addStream("randomToMerge1", randomGenerator1.out, merger.data1).setLocality(Locality.CONTAINER_LOCAL);
    dag.addStream("randomToMerge2", randomGenerator2.out, merger.data2).setLocality(Locality.CONTAINER_LOCAL);
    dag.addStream("mergeToConsole", merger.out, cons.input).setLocality(Locality.CONTAINER_LOCAL);

  }
}
