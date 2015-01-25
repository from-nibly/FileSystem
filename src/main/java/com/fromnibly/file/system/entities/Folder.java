package com.fromnibly.file.system.entities;

import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.exceptions.NotATextFileException;
import com.fromnibly.file.system.exceptions.PathAlreadyExistsException;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.fromnibly.file.system.interfaces.HasChildren;
import com.fromnibly.file.system.interfaces.HasParent;
import com.fromnibly.file.system.shared.HasChildrenShared;
import com.fromnibly.file.system.shared.HasParentShared;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jdavidson on 1/22/15.
 */
public class Folder<T extends Entity & HasChildren> extends Entity implements HasParent, HasChildren
{
  private final Map<String, Entity> children;
  private T parent;

  public Folder(String name, T parent) throws IllegalFileSystemOperationException
  {
    super(name);
    this.children = new HashMap<String, Entity>();
    this.parent = parent;
  }

  @Override public String getPath()
  {
    return this.parent.getPath() + name + "\\";
  }

  @Override public int getSize()
  {
    int rtn = 0;
    for (Map.Entry<String, Entity> f : children.entrySet())
    {
      rtn += f.getValue().getSize();
    }
    return rtn;
  }

  @Override public Entity traverse(List<String> path)
      throws PathNotFoundException
  {
    return HasChildrenShared.traverse(this, path);
  }

  @Override public void remove(List<String> path) throws PathNotFoundException
  {
    HasChildrenShared.removeChild(this, path);
    if (path.size() == 0)
    {
      HasParentShared.<Folder>remove(this);
    }
  }

  @Override public Map<String, Entity> getChildren()
  {
    return children;
  }

  @Override public void addChild(Class<? extends Entity> type, String name, List<String> path)
      throws IllegalFileSystemOperationException, PathNotFoundException, PathAlreadyExistsException
  {
    HasChildrenShared.addChild(this, type, name, path);
  }

  @Override public void setContent(List<String> path, String content)
      throws PathNotFoundException, NotATextFileException
  {
    HasChildrenShared.setContent(this, path, content);
  }

  @Override public String getContent(List<String> path)
      throws NotATextFileException, PathNotFoundException
  {
    return HasChildrenShared.getContent(this, path);
  }

  @Override public Entity find(List<String> path) throws PathNotFoundException
  {
    return HasChildrenShared.find(this, path);
  }

  @Override public T getParent()
  {
    return parent;
  }

  @Override public void removeParent()
  {
    parent = null;
  }
}
