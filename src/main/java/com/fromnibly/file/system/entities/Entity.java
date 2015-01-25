package com.fromnibly.file.system.entities;

import com.fromnibly.file.system.Util;
import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.exceptions.PathNotFoundException;

import java.util.List;

/**
 * Created by jdavidson on 1/22/15.
 */
public abstract class Entity
{

  protected final String name;

  public Entity(String name) throws IllegalFileSystemOperationException
  {
    if (!Util.isFileNameValid(name))
    {
      throw new IllegalFileSystemOperationException("name must be alphanumeric");
    }
    this.name = name;
  }

  /**
   * @return the type
   */
  public String getType()
  {
    return this.getClass().getSimpleName();
  }

  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @return the path to this entity
   */
  public abstract String getPath();

  /**
   * @return the size of the entity and any children it may have
   */
  public abstract int getSize();

  /**
   * returns the Entity at the end of the path
   *
   * @param path the path to the destination entity
   * @return the Entity at the end of the path
   * @throws PathNotFoundException if the path does not exist
   */
  public abstract Entity traverse(List<String> path)
      throws PathNotFoundException;

  /**
   * removes the Entity at the path
   *
   * @param path the path to the destination entity
   * @throws PathNotFoundException if the path does not exist
   */
  public abstract void remove(List<String> path)
      throws PathNotFoundException;
}
