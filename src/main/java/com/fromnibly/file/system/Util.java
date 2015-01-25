package com.fromnibly.file.system;

/**
 * Created by jdavidson on 1/22/15.
 */
public class Util
{

  /**
   * returns true if name is alphanumeric
   *
   * @param name the name in question
   * @return true if name is alphanumeric otherwise false
   */
  public static boolean isFileNameValid(String name)
  {
    return name.matches("[a-zA-Z0-9]*");
  }
}
