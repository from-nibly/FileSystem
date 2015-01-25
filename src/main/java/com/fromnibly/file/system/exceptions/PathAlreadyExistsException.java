package com.fromnibly.file.system.exceptions;

/**
 * Created by jdavidson on 1/24/15.
 */
public class PathAlreadyExistsException extends FileSystemException
{
  public PathAlreadyExistsException()
  {
    super("Path already exists");
  }
}
