package com.fromnibly.file.system.entities;

import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.interfaces.HasChildren;

/**
 * Created by jdavidson on 1/22/15.
 */
public class TextFile<T extends Entity & HasChildren> extends File
{

  private String content = "";

  public TextFile(String name, T parent) throws IllegalFileSystemOperationException
  {
    super(name, parent);
  }

  @Override public String getContent()
  {
    return content;
  }

  @Override public int getSize()
  {
    return content.length();
  }

  @Override public void setContent(String content)
  {
    this.content = content;
  }
}
