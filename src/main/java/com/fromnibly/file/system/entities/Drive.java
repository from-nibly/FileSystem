package com.fromnibly.file.system.entities;

import com.fromnibly.file.system.exceptions.IllegalFileSystemOperationException;
import com.fromnibly.file.system.exceptions.NotATextFileException;
import com.fromnibly.file.system.exceptions.PathAlreadyExistsException;
import com.fromnibly.file.system.exceptions.PathNotFoundException;
import com.fromnibly.file.system.interfaces.HasChildren;
import com.fromnibly.file.system.shared.HasChildrenShared;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jdavidson on 1/22/15.
 */
public class Drive extends Entity implements HasChildren
{

  private final Map<String, Entity> children;

  public Drive(String name) throws IllegalFileSystemOperationException
  {
    super(name);
    children = new HashMap<String, Entity>();
  }

  @Override public String getPath()
  {
    return this.name + ":\\";
  }

  @Override public int getSize()
  {

    int rtn = 0;
    for (Map.Entry<String, Entity> e : children.entrySet())
    {
      rtn += e.getValue().getSize();
    }
    return rtn;
  }

  @Override public Entity traverse(List<String> path)
      throws PathNotFoundException
  {
    return HasChildrenShared.traverse(this, path);
  }

  @Override public void remove(List<String> path)
      throws PathNotFoundException
  {
    HasChildrenShared.removeChild(this, path);
  }

  @Override public Map<String, Entity> getChildren()
  {
    return children;
  }

  @Override public void addChild(Class<? extends Entity> type, String name, List<String> path)
      throws PathNotFoundException, IllegalFileSystemOperationException, PathAlreadyExistsException
  {
    HasChildrenShared.addChild(this, type, name, path);
  }

  @Override public void setContent(List<String> path, String content)
      throws PathNotFoundException,
      NotATextFileException
  {
    HasChildrenShared.setContent(this, path, content);
  }

  @Override public String getContent(List<String> path)
      throws PathNotFoundException, NotATextFileException
  {
    return HasChildrenShared.getContent(this, path);
  }

  @Override public Entity find(List<String> path) throws PathNotFoundException
  {
    return HasChildrenShared.find(this, path);
  }
}
