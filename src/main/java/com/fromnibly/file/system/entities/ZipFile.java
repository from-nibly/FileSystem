package com.fromnibly.file.system.entities;

import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;

/**
 * Created by jdavidson on 1/22/15.
 */
public class ZipFile extends Folder
{
  public ZipFile(String name, Entity parent) throws IllegalFileSystemOperationException
  {
    super(name, parent);
  }

  @Override public int getSize()
  {
    return super.getSize() / 2;
  }

  @Override public String getPath()
  {
    return getParent().getPath() + name;
  }
}
