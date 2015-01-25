package com.fromnibly.file.system.exceptions;

/**
 * Created by jdavidson on 1/24/15.
 */
public class PathNotFoundException extends FileSystemException
{

  public PathNotFoundException()
  {
    super("path not found");
  }
}
