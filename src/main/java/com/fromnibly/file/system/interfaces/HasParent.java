package com.fromnibly.file.system.interfaces;

import com.fromnibly.file.system.entities.Entity;

/**
 * Created by jdavidson on 1/22/15.
 */
public interface HasParent
{
  /**
   * @param <T> extends Entity & HasChildren
   * @return the parent
   */
  public <T extends Entity & HasChildren> T getParent();

  /**
   * removes the parent link
   */
  public void removeParent();

}
