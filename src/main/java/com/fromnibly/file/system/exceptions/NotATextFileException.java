package com.fromnibly.file.system.exceptions;

/**
 * Created by jdavidson on 1/24/15.
 */
public class NotATextFileException extends FileSystemException
{
  public NotATextFileException()
  {
    super("not a file");
  }
}
