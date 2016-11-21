package com.example.batch;

import org.apache.apex.malhar.lib.fs.LineByLineFileInputOperator;

import com.datatorrent.api.Operator.CheckpointNotificationListener;

public class BatchLineFileInputOperator extends LineByLineFileInputOperator implements CheckpointNotificationListener
{
  private boolean shutDown = false;

  @Override
  public void endWindow()
  {
    super.endWindow();

    if ((currentFile == null || offset < 0) && pendingFiles.isEmpty() && unfinishedFiles.isEmpty() && failedFiles.isEmpty()) {
      shutDown = true;
    }
  }

  @Override
  public void checkpointed(long windowId)
  {
  }

  @Override
  public void committed(long windowId)
  {
    if (shutDown) {
      throw new ShutdownException();
    }
  }

  @Override
  public void beforeCheckpoint(long windowId)
  {
  }
}
