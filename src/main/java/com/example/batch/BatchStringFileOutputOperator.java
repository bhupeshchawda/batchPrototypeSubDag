package com.example.batch;


import org.apache.apex.malhar.lib.fs.GenericFileOutputOperator;

public class BatchStringFileOutputOperator extends GenericFileOutputOperator.StringFileOutputOperator
{
  @Override
  public void teardown()
  {
    try {
      for (String fileName: getFileNameToTmpName().keySet()) {
        finalizeFile(fileName);
      }
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
    super.teardown();
  }
}
