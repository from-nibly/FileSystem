package com.fromnibly.file.system.entities;

import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.fromnibly.file.system.interfaces.HasChildren;
import com.fromnibly.file.system.interfaces.HasParent;
import com.fromnibly.file.system.shared.HasParentShared;

import java.util.List;

/**
 * Created by jdavidson on 1/22/15.
 */
public abstract class File<T extends Entity & HasChildren> extends Entity implements HasParent
{

  protected T parent;

  public File(String name, T parent) throws IllegalFileSystemOperationException
  {
    super(name);
    this.parent = parent;
  }

  @Override public T getParent()
  {
    return parent;
  }

  @Override public String getPath()
  {
    return this.parent.getPath() + name;
  }

  /**
   * sets the content of the file
   *
   * @param content the content to overwrite the current content
   */
  public abstract void setContent(String content);

  /**
   * @return the content of the current file
   */
  public abstract String getContent();

  @Override public void remove(List<String> path)
      throws PathNotFoundException
  {
    if (path.size() != 0)
    {
      throw new PathNotFoundException();
    }
    else
    {
      HasParentShared.remove(this);
    }
  }

  @Override public void removeParent()
  {
    parent = null;
  }

  @Override public Entity traverse(List<String> path)
      throws PathNotFoundException
  {
    if (path.size() > 0)
    {
      throw new PathNotFoundException();
    }
    else
    {
      return this;
    }
  }
}
