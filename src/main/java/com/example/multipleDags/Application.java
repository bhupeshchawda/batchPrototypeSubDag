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

@ApplicationAnnotation(name="Application1")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    RandomNumberGenerator1 randomGenerator1 = dag.addOperator("randomGenerator1", RandomNumberGenerator1.class);
    randomGenerator1.setNumTuples(500);
    ConsoleOutputOperator cons1 = dag.addOperator("console1", new ConsoleOutputOperator());
    dag.addStream("randomData1", randomGenerator1.out, cons1.input).setLocality(Locality.CONTAINER_LOCAL);

    RandomNumberGenerator2 randomGenerator2 = dag.addOperator("randomGenerator2", RandomNumberGenerator2.class);
    randomGenerator2.setNumTuples(500);
    ConsoleOutputOperator cons2 = dag.addOperator("console2", new ConsoleOutputOperator());
    dag.addStream("randomData2", randomGenerator2.out, cons2.input).setLocality(Locality.CONTAINER_LOCAL);

  }
}
